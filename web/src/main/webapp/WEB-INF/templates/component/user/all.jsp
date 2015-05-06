<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<s:authentication property="principal.admin" var="isAdmin"/>

<jsp:useBean id="users" type="java.util.List<com.sharecare.sample.model.user.User>" scope="request"/>

<div id="component" style="word-wrap: break-word;">
    <c:if test="${not empty content.title}"><h3>${content.title}</h3></c:if>
    <ul>
        <c:forEach var="user" items="${users}">
            <li>
                <a href="/users/${user.url}">${user.name}</a> in ${user.address}
                <c:if test="${isAdmin}">&nbsp;(<a href="/users/${user.url}?edit">Edit</a>)</c:if>
            </li>
        </c:forEach>
        <li>(<a href="/users?create">New User</a>)</li>
    </ul>
</div>