<!DOCTYPE html>

<% response.setStatus(503); %>
<jsp:useBean id="exception" type="java.lang.Exception" scope="request"/>

<html>

<head>
    <title>Error Page</title>

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
            width: 95%;
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

        h1, h2, h3 {
            padding: 2px;
        }
    </style>

</head>

<body>
<div id="wrap">
    <div id="header">
        <h1>An Error Occurred!</h1>
    </div>

    <div id="main">
        <em>${exception.message}</em>
    </div>

    <div id="footer"></div>
</div>
</body>

</html>