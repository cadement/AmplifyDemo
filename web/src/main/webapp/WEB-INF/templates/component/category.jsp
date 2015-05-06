<%@taglib prefix="cmsfn" uri="http://magnolia-cms.com/taglib/templating-components/cmsfn" %>
<%@taglib prefix="sc" uri="http://sharecare.com/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="component" style="word-wrap: break-word;">
    <c:set var="title" value="${empty category.title ? '{title}' : category.title}"/>
    <script type="application/javascript">document.title = '${title}';</script>
    <h1>${title}</h1>
</div>