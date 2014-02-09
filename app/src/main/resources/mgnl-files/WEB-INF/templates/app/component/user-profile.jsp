<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="user" type="com.sharecare.sample.model.user.User" scope="request"/>

<div id="component" style="word-wrap: break-word;">
    <h3>Details</h3>
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