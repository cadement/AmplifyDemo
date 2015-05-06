<%@taglib prefix="cmsfn" uri="http://magnolia-cms.com/taglib/templating-components/cmsfn" %>
<%@taglib prefix="sc" uri="http://sharecare.com/taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="component" style="word-wrap: break-word;">
    <c:set var="title" value="${empty article.title ? '{title}' : article.title}"/>
    <script type="application/javascript">document.title = '${title}';</script>
    <h1>${title}</h1>

    <c:choose>
        <c:when test="${not empty article.category}">
            <sc:category var="category" categoryId="${article.category}"/>
            <c:set var="categoryUrl" value="${category.url}"/>
            <c:set var="categoryTitle" value="${category.title}"/>
        </c:when>
        <c:otherwise>
            <c:set var="categoryUrl" value="category"/>
            <c:set var="categoryTitle" value="{category}"/>
        </c:otherwise>
    </c:choose>
    <h4>in <a href="/categories/${categoryUrl}">${categoryTitle}</a></h4>

    <div>${empty article.body ? '{Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.}' : article.body}</div>
</div>