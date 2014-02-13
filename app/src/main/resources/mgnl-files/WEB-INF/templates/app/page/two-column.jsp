<!DOCTYPE html>

<%@taglib prefix="cms" uri="http://magnolia-cms.com/taglib/templating-components/cms" %>

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