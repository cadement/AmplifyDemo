package com.sharecare.jcr;

import com.sharecare.jcr.search.JCRSearch;
import info.magnolia.context.LifeTimeJCRSessionUtil;
import org.springframework.stereotype.Service;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    public List<Node> findNodes(Workspace workspace, JCRSearch search) throws RepositoryException {
        Query query = getQueryManager(workspace).createQuery(search.getQuery(), Query.JCR_SQL2);
        QueryResult result = query.execute();
        NodeIterator iterator = result.getNodes();

        List<Node> nodes = new ArrayList<Node>((int) iterator.getSize());
        while (iterator.hasNext()) {
            nodes.add(iterator.nextNode());
        }
        return nodes;
    }

    public Long findCountOfNodes(Workspace workspace, JCRSearch search) throws RepositoryException {
        Query query = getQueryManager(workspace).createQuery(search.getQuery(), Query.JCR_SQL2);
        QueryResult result = query.execute();
        NodeIterator iterator = result.getNodes();
        return iterator.getSize();
    }

    public QueryManager getQueryManager(Workspace workspace) throws RepositoryException {
        return LifeTimeJCRSessionUtil
                .getSession(workspace.getWorkspaceName())
                .getWorkspace()
                .getQueryManager();
    }
}
