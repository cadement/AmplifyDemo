package com.sharecare.sample.model.article;

import com.sharecare.sample.auth.spring.SpringAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/articles")
public class ArticleController {

    private final ArticleRepository     articleRepository;
    private final ReadArticleRepository readArticleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ReadArticleRepository readArticleRepository) {
        this.articleRepository = articleRepository;
        this.readArticleRepository = readArticleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getArticles() {
        ModelAndView modelAndView = new ModelAndView("/articles.html");
        modelAndView.addObject("title", "Articles");
        modelAndView.addObject("breadcrumbs", Collections.<String, String>singletonMap("/", "Home"));
        return modelAndView;
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public ModelAndView getUserProfile(
            @PathVariable("url") String url,
            SpringAuthentication authorization
    ) {
        Article article = articleRepository.readArticle(url);

        try {
            ReadArticle readArticle = readArticleRepository.readReadArticle(authorization.getPrincipal(), article);
            readArticle.setReadOn(new Timestamp(System.currentTimeMillis()));
            readArticleRepository.updateReadArticle(readArticle);
        } catch (DataAccessException ignore) {
            readArticleRepository.createReadArticle(
                    new ReadArticle(
                            authorization.getPrincipal().getUrl(),
                            article.getUrl(),
                            new Timestamp(System.currentTimeMillis())
                    )
            );
        }

        ModelAndView modelAndView = new ModelAndView("/articles/" + article.getUrl() + ".html");
        modelAndView.addObject("title", article.getTitle());
        modelAndView.addObject("article", article);

        Map<String, String> breadcrumbs = new LinkedHashMap<String, String>();
        breadcrumbs.put("/", "Home");
        breadcrumbs.put("/articles", "Articles");
        modelAndView.addObject("breadcrumbs", breadcrumbs);

        return modelAndView;
    }
}
