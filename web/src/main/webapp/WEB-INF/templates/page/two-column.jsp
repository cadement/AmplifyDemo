<!DOCTYPE html>

<%@taglib prefix="cms" uri="http://magnolia-cms.com/taglib/templating-components/cms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="breadcrumbs" type="java.util.Map<java.lang.String, java.lang.String>" scope="request"/>
<jsp:useBean id="title" type="java.lang.String" scope="request"/>

<html>

<head>
    <title>${title}</title>

    <link rel="stylesheet" media="screen" href="/static/default.css"/>

    <style type="text/css" media="screen, print, projection">
        #main {
            float: left;
            width: 680px;
            background: white;;
        }

        #sidebar {
            float: right;
            width: 280px;
            background: lightgrey;
        }
    </style>
</head>

<body>

<div id="wrap">
    <cms:area name="header"/>

    <div id="main" class="column">
        <c:if test="${not empty breadcrumbs}">
            <div id="breadcrumbs">
                <c:forEach items="${breadcrumbs}" var="breadcrumb">
                    &gt; <a href="${breadcrumb.key}">${breadcrumb.value}</a>
                </c:forEach>
            </div>
        </c:if>

        <h1>${title}</h1>

        <cms:area name="main-column"/>
    </div>

    <div id="sidebar" class="column">
        <cms:area name="side-column"/>
    </div>

    <div id="footer"></div>
</div>

</body>
</html>