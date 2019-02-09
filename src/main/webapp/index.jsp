<%--
  Created by IntelliJ IDEA.
  User: Natali
  Date: 24.01.2019
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main page</title>
    <style type="text/css">
        body {
            background-color: coral;
        }

        p {
            margin-left: 30px;
            font-size: 30px;
        }

        h1 {
            color: cyan;
        }
    </style>
</head>

<body>
<h1 align="center">Hello friend!! Make your choice, please</h1>
<p></p>

<form action="parse" enctype="multipart/form-data" method="post">
    <p align="center"><b>Choose the parser</b></p>
    <p align="center">
        <label align="center">
            <select name="parser" size="1">
                <option value="dom">DOM</option>
                <option value="sax">SAX</option>
                <option value="stax">StAX</option>
            </select>
        </label>
    </p>

    <p align="center"><b>Choose the file</b></p>
    <p align="center"><input type="file" name="file">
        <input type="submit" value="Send"></p>



</form>

</body>
</html>

