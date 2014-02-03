<!DOCTYPE html>

<%@taglib prefix="cms" uri="http://magnolia-cms.com/taglib/templating-components/cms" %>

<html>

<head>
    <title>${content.title}</title>

    <style type="text/css" media="screen, print, projection">
        body, html {
            margin: 0;
            padding: 0;
            color: black;
            background: skyblue;
        }

        #wrap {
            width: 90%;
            margin: 0 auto;
            background: white;
        }

        #header {
            background: white;
            padding:5px 10px;
        }

        #main {
            float: left;
            width: 63%;
            padding:10px;
        }

        #sidebar {
            float: right;
            width: 30%;
            padding:10px;
        }

        #footer {
            clear:both;
            padding:5px 10px;
            background: white;
        }

        #footer p {
            margin:0;
        }

        * html #footer {
            height:1px;
        }

        #area {
            padding: 2px;
            border: 2px solid black;
            border-radius: 25px;
        }

        #component {
            padding: 2px;
            border: 2px solid black;
            border-radius: 25px;
        }

        h1, h2, h3 {
            padding: 2px;
        }
    </style>
</head>

<body>

<div id="wrap">
    <div id="header">
        <cms:init/>
        <h1>${content.title}</h1>
    </div>

    <div id="main">
        <cms:area name="main-column"/>
    </div>

    <div id="sidebar">
        <cms:area name="side-column"/>
    </div>

    <div id="footer"></div>
</div>

</body>
</html>