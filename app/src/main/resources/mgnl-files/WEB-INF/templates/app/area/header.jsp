<%@taglib prefix="cms" uri="http://magnolia-cms.com/taglib/templating-components/cms" %>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:authentication property="principal.name" var="username"/>
<s:authentication property="principal.url" var="url"/>

<cms:init/>

<div id="header">
    <h2><a href="/">Sharecare Demo Application</a></h2>
    <h4><a href="/users/${url}">${username}</a></h4>
</div>
