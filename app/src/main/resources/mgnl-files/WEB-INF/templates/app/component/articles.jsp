<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="articles" type="java.util.List<com.sharecare.article.model.Article>" scope="request"/>

<div id="component" style="word-wrap: break-word;">
    <c:if test="${not empty content.title}"><h3>${content.title}</h3></c:if>
    <ul>
        <c:forEach var="article" items="${articles}">
            <li>${article.category} - <a href="/articles/${article.url}">${article.title}</a></li>
        </c:forEach>
    </ul>
</div>