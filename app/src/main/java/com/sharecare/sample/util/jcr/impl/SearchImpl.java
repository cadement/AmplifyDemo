package com.sharecare.sample.util.jcr.impl;

import com.sharecare.sample.util.jcr.Search;
import com.sharecare.sample.util.jcr.NodeType;

import java.util.List;

class SearchImpl implements Search {

    private final NodeType            nodeType;
    private final List<NodeCriterion> nodeCriteria;
    private final List<NodeOrderBy>   nodeOrderBys;

    public SearchImpl(NodeType nodeType, List<NodeCriterion> nodeCriteria, List<NodeOrderBy> nodeOrderBys) {
        this.nodeType = nodeType;
        this.nodeCriteria = nodeCriteria;
        this.nodeOrderBys = nodeOrderBys;
    }

    @Override
    public String getQuery() {
        StringBuilder query = new StringBuilder()
                .append("SELECT * ")
                .append("FROM [").append(nodeType.id()).append("]");

        if (nodeCriteria.size() > 0) {
            query.append(" WHERE ");

            boolean first = true;
            for (NodeCriterion nodeCriterion : nodeCriteria) {
                if (!first) query.append(" AND ");
                query.append(nodeCriterion.getQuery());
                first = false;
            }
        }

        if (nodeOrderBys.size() > 0) {
            query.append(" ORDER BY ");

            boolean first = true;
            for (NodeOrderBy nodeOrderBy : nodeOrderBys) {
                if (!first) query.append(" AND ");
                query.append(nodeOrderBy.getQuery());
                first = false;
            }
        }

        return query.toString();
    }
}
