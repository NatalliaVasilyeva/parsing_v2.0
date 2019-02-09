<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <title>result</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" >
    <style type="text/css">
        body {background-color: red}
        p {margin-left: 20px}
        table{ align-self: center;
            margin: auto;
            text-align: center;
            border-collapse: separate;
            border: 5px solid #ECE9E0;
            border-spacing: 5px;
            border-radius: 20px;
            border-color: #656665;
            background: #fdfdfd;
            font-size: 14px;
            font-family: "Arial";
            width: auto;}
        caption{ font-style: italic;
            text-align: center;
            color: #547901;
            font-size: 20px;}
        td{border: 1px solid #ddd;
            padding: 10px;
            border-radius: 3px;
            background: #F5D7BF;}
    </style>
</head>
<body>
<h1 align="center">Hey!!! See what can do my the best parser!:)</h1>
<p></p>
<table>
    <caption>"Result of parsing"</caption>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date of planting</th>
        <th>Origin</th>
        <th>Soil</th>
        <th>Multiplying</th>
        <th>Stem color</th>
        <th>Leaf color</th>
        <th>Average size</th>
        <th>Temperature</th>
        <th>Photophilous</th>
        <th>Watering</th>
    </tr>
    <c:forEach items="${flowers}" var="flower">
        <tr>
            <td>${flower.id}</td>
            <td>${flower.name}</td>
            <td>${flower.plantingDate}</td>
            <td>${flower.origin}</td>
            <td>${flower.soilType}</td>
            <td>${flower.multiplying}</td>
            <td>${flower.visualParameters.stemColor}</td>
            <td>${flower.visualParameters.leafColor}</td>
            <td>${flower.visualParameters.averageSize}</td>
            <td>${flower.growingTips.temperature}</td>
            <td>${flower.growingTips.photophilous}</td>
            <td>${flower.growingTips.watering}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
