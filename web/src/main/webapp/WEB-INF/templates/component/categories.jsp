<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sc" uri="http://sharecare.com/taglib" %>

<sc:categories var="categories"/>

<div id="component" style="word-wrap: break-word;">
    <c:if test="${not empty content.title}"><h3>${content.title}</h3></c:if>
    <ul>
        <c:forEach var="category" items="${categories}">
            <li>
                <a href="/categories/${category.url}">${category.title}</a>
            </li>
        </c:forEach>
    </ul>
</div>