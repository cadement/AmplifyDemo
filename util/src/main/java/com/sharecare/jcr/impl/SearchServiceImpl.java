package com.sharecare.jcr.impl;

import com.sharecare.jcr.Search;
import com.sharecare.jcr.SearchService;
import com.sharecare.jcr.Workspace;
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
class SearchServiceImpl implements SearchService {

    @Override public List<Node> findNodes(Workspace workspace, Search search) throws RepositoryException {
        Query query = getQueryManager(workspace).createQuery(search.getQuery(), Query.JCR_SQL2);
        QueryResult result = query.execute();
        NodeIterator iterator = result.getNodes();

        List<Node> nodes = new ArrayList<Node>((int) iterator.getSize());
        while (iterator.hasNext()) {
            nodes.add(iterator.nextNode());
        }
        return nodes;
    }

    @Override public Long findCountOfNodes(Workspace workspace, Search search) throws RepositoryException {
        Query query = getQueryManager(workspace).createQuery(search.getQuery(), Query.JCR_SQL2);
        QueryResult result = query.execute();
        NodeIterator iterator = result.getNodes();
        return iterator.getSize();
    }

    @Override public QueryManager getQueryManager(Workspace workspace) throws RepositoryException {
        return LifeTimeJCRSessionUtil
                .getSession(workspace.getWorkspaceName())
                .getWorkspace()
                .getQueryManager();
    }
}
