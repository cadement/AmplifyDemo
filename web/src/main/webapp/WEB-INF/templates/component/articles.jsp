<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sc" uri="http://sharecare.com/taglib" %>

<c:choose>
    <c:when test="${not empty category.id}">
        <c:set var="byCategory" value="${true}"/>
        <sc:articlesByCategory var="articles" categoryId="${category.id}"/>
    </c:when>
    <c:otherwise>
        <c:set var="byCategory" value="${false}"/>
        <sc:articles var="articles"/>
    </c:otherwise>
</c:choose>

<div id="component" style="word-wrap: break-word;">
    <c:if test="${not empty content.title}"><h3>${content.title}</h3></c:if>
    <ul>
        <c:forEach var="article" items="${articles}">
            <li>
                <c:if test="${not byCategory}">
                    <sc:category var="category" categoryId="${article.category}"/>
                    <a href="/categories/${category.url}">${category.title}</a> -
                </c:if>
                <a href="/articles/${article.url}">${article.title}</a>
            </li>
        </c:forEach>
    </ul>
</div>