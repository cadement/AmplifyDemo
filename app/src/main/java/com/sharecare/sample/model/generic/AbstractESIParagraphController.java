package com.sharecare.sample.model.generic;

import info.magnolia.cms.core.Content;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractESIParagraphController {

    private Boolean esiEnabled = true;

    /**
     * This method is used to govern caching of the ESI document.  There are two obvious usages:
     * <ul>
     * <li>Change-based Caching: return the vector of the object that caching should be bound to (thus, whenever that object is changed the cache will be invalidated</li>
     * <li>Time-based Caching: return a "rounded off" timestamp (thus, whenever the rounding threshold is crossed the number will change and the cache will be invalidated</li>
     * </ul>
     * But feel free to dream up your own uses :)
     *
     * @param model The current request Model
     * @return an cache version String
     */
    protected abstract String getVersion(Model model);

    /**
     * This method parses the Model of the current request and returns a map of identifying parameters to append to the ESI request.
     *
     * @param model The current request Model
     * @return Map of ESI request parameters
     */
    protected abstract Map<String, Object> getParameters(Model model);

    /**
     * This method uses the map of incoming request parameters to populate the given Model with any required objects.
     *
     * @param parameters Incoming request parameters
     * @param model      The current request Model
     */
    protected abstract void updateModel(Map<String, Object> parameters, Model model);

    /**
     * This method returns the view to render based on the Model.
     *
     * @param model The current request Model
     * @return The view to render
     */
    protected abstract String getView(Model model);

    /**
     * This controller handler is executed when an incoming ESI request is detected (by the presence of an "esi" parameter).  It triggers the abstract getView method to forward
     * the request to the paragraph rendering engine of the CMS.
     *
     * @param model   The current request Model
     * @param request The current request
     * @return The view to render
     */
    @RequestMapping(params = "esi")
    public String generateParagraph(Model model, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String parameter;
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            parameter = (String) parameterNames.nextElement();
            parameters.put(parameter, request.getParameter(parameter));
        }
        updateModel(parameters, model);
        return getView(model);
    }

    /**
     * This controller handler is executing when a paragraph is invoked during page render.  It assembles and renders the appropriate ESI tag.
     *
     * @param paragraph The JCR repository node of this paragraph instance
     * @param model     The current request Model
     * @param request   The current request
     * @param response  The current response
     * @return The ESI tag
     */
    @RequestMapping()
    public String handleRequest(Content paragraph, Model model, HttpServletRequest request, HttpServletResponse response) {
        String key;

        Enumeration iterator = request.getAttributeNames();
        while (iterator.hasMoreElements()) {
            key = (String) iterator.nextElement();
            model.addAttribute(key, request.getAttribute(key));
        }

        if (esiEnabled) {
            StringBuffer out = new StringBuffer()
                    .append(ESIController.PATH)
                    .append("/")
                    .append(getVersion(model))
                    .append(paragraph.getHandle())
                    .append("?esi");

            Map<String, Object> params = getParameters(model);
            for (String param : params.keySet()) {
                out
                        .append("&")
                        .append(param)
                        .append("=")
                        .append(params.get(param));
            }

            model.addAttribute("esiURI", out.toString());

            return "app/component/esi.jsp";
        } else {
            return getView(model);
        }
    }

}
