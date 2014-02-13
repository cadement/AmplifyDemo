<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<s:authentication property="principal.admin" var="isAdmin"/>

<jsp:useBean id="user" type="com.sharecare.sample.model.user.User" scope="request"/>

<div id="component" style="word-wrap: break-word;">
    <h3>Details</h3>

    <form action="${user.url}" method="POST">
        <table class="properties">
            <thead>
            <tr>
                <td>Property</td>
                <td>Value</td>
            </tr>
            </thead>
            <tr>
                <td><label>Name</label></td>
                <td><input name="name" value="${user.name}"/></td>
            </tr>
            <tr>
                <td><label>Password</label></td>
                <td><input name="password" type="password" value="${user.password}"/></td>
            </tr>
            <tr>
                <td><label>Email</label></td>
                <td><input name="email" value="${user.email}"/></td>
            </tr>
            <tr>
                <td><label>Location</label></td>
                <td><input name="address" value="${user.address}"/></td>
            </tr>
            <tr>
                <td><label>Admin</label></td>
                <td><input name="admin" type="checkbox"<c:if test="${not isAdmin}"> disabled</c:if><c:if test="${user.admin}"> checked</c:if>/></td>
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
        <input type="submit" value="Submit"/>
        <input type="button" value="Cancel" onclick="window.location.replace('${user.url}')"/>
    </form>
</div>