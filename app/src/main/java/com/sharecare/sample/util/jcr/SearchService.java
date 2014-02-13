package com.sharecare.sample.util.jcr;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.query.QueryManager;
import java.util.List;

public interface SearchService {

    List<Node> findNodes(Workspace workspace, Search search) throws RepositoryException;

    Long findCountOfNodes(Workspace workspace, Search search) throws RepositoryException;

    QueryManager getQueryManager(Workspace workspace) throws RepositoryException;
}
