<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="user" type="com.sharecare.sample.model.User" scope="request"/>

<div id="component" style="word-wrap: break-word;">
    <h3><a href="/user/${user.url}">${user.name}</a></h3>
    Last Login:
    <c:choose>
        <c:when test="${empty user.lastLogin}">Never</c:when>
        <c:otherwise><fmt:formatDate value="${user.lastLogin}"/></c:otherwise>
    </c:choose>
</div>