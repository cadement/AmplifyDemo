<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<s:authentication property="principal.url" var="url"/>
<s:authentication property="principal.admin" var="isAdmin"/>

<jsp:useBean id="user" type="com.sharecare.sample.model.user.User" scope="request"/>

<div id="component" style="word-wrap: break-word;">
    <h3>Details
        <c:if test="${isAdmin || (url eq user.url)}">&nbsp;(<a href="${user.url}?edit">Edit</a>)</c:if>
    </h3>
    <table class="properties">
        <thead>
        <tr>
            <td>Property</td>
            <td>Value</td>
        </tr>
        </thead>
        <tr>
            <td><label>Email</label></td>
            <td><a href="mailto:${user.email}">${user.email}</a></td>
        </tr>
        <tr>
            <td><label>Location</label></td>
            <td>${user.address}</td>
        </tr>
        <tr>
            <td><label>Admin</label></td>
            <td><input type="checkbox" disabled<c:if test="${user.admin}"> checked</c:if>/></td>
        </tr>
        <tr>
            <td><label>Last Updated</label></td>
            <td>
                <c:choose>
                    <c:when test="${empty user.lastUpdated}">Never</c:when>
                    <c:otherwise><fmt:formatDate value="${user.lastUpdated}"/></c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td><label>Last Login</label></td>
            <td>
                <c:choose>
                    <c:when test="${empty user.lastLogin}">Never</c:when>
                    <c:otherwise><fmt:formatDate value="${user.lastLogin}"/></c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</div>