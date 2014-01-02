package com.sharecare.web;

import com.sharecare.jcr.NodeType;
import com.sharecare.jcr.SearchBuilder;
import com.sharecare.jcr.SearchService;
import com.sharecare.jcr.Workspace;
import com.sharecare.jcr.search.NodeOrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class ArticleController {

    private final SearchBuilder searchBuilder;
    private final SearchService searchService;

    @Autowired
    public ArticleController(SearchBuilder searchBuilder, SearchService searchService) {
        this.searchBuilder = searchBuilder;
        this.searchService = searchService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllArticles() {
        return "/demo-project/news-and-events/news-overview.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "newest")
    public String getNewestArticle() throws RepositoryException {
        List<Node> nodes = searchService.findNodes(
                Workspace.WEBSITE,
                searchBuilder
                        .nodeType(NodeType.PAGE)
                        .addIsChildOf("/demo-project/news-and-events/news-overview")
                        .addOrderBy("date", NodeOrderBy.Ordering.DESCENDING)
                        .build()
        );
        if (nodes.isEmpty()) {
            throw new RuntimeException("Page not found");
        } else {
            return "redirect:/news/" + nodes.get(0).getProperty("uri").getString();
        }
    }

    @RequestMapping(value = "/{article-title}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("article-title") String articleTitle) throws RepositoryException {
        List<Node> nodes = searchService.findNodes(
                Workspace.WEBSITE,
                searchBuilder
                        .nodeType(NodeType.PAGE)
                        .addIsChildOf("/demo-project/news-and-events/news-overview")
                        .addPropertyEquals("uri", articleTitle)
                        .build()
        );
        if (nodes.isEmpty()) {
            throw new RuntimeException("Page not found");
        } else {
            return nodes.get(0).getPath() + ".html";
        }
    }
}
