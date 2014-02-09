package com.sharecare.sample.model.article.templates;

import com.sharecare.sample.model.article.Article;
import com.sharecare.sample.model.article.ArticleRepository;
import com.sharecare.sample.model.generic.AbstractESIParagraphController;
import com.sharecare.sample.model.generic.templates.StandardComponent;
import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.ui.dialog.config.DialogBuilder;
import info.magnolia.ui.form.config.OptionBuilder;
import info.magnolia.ui.framework.config.UiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Template(id = "app:components/article", title = "Article", dialog = "article-dialog")
@StandardComponent
@Controller
@RequestMapping("/article")
public class ArticleComponent extends AbstractESIParagraphController {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleComponent(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @DialogFactory("article-dialog")
    public void genericComponentDialog(UiConfig cfg, DialogBuilder dialog) {
        dialog
                .form()
                .tabs(
                        cfg.forms.tab("Article Details")
                                .fields(
                                        cfg.fields.text("title").label("Title"),
                                        cfg.fields.select("category").label("Category")
                                                .options(
                                                        new OptionBuilder().label("Politics").value("Politics"),
                                                        new OptionBuilder().label("Economics").value("Economics"),
                                                        new OptionBuilder().label("Health").value("Health"),
                                                        new OptionBuilder().label("Entertainment").value("Entertainment"),
                                                        new OptionBuilder().label("Sports").value("Sports")
                                                ),
                                        cfg.fields.richText("body").label("Body")
                                )
                );
    }

    @Override
    protected String getVersion(Model model) {
        Article article = (Article) model.asMap().get("article");
        return String.valueOf(article.getPublished().getTime());
    }

    @Override
    protected Map<String, Object> getParameters(Model model) {
        return Collections.<String, Object>singletonMap("url", ((Article) model.asMap().get("article")).getUrl());
    }

    @Override
    protected void updateModel(Map<String, Object> parameters, Model model) {
        Article article = articleRepository.readArticle((String) parameters.get("url"));
        model.addAttribute("article", article);
    }

    @Override
    protected String getView(Model model) {
        return "app/component/article.jsp";
    }
}
