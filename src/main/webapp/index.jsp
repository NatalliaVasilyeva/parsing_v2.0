<%--
  Created by IntelliJ IDEA.
  User: Natali
  Date: 24.01.2019
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Main page</title>
</head>

<body>
<h1>The page to choose the file to parse and type of parsing</h1>
<p></p>

<form action="parse" enctype="multipart/form-data" method="post">
    <p><b>Choose the file</b></p>
    <p><input type="file" name="file">
        <input type="submit" value="Отправить"></p>


<p><b>Choose the parser</b>
    <select  name="parser" size="1">
        <option value="dom">DOM</option>
        <option value="sax">SAX</option>
        <option value="stax">StAX</option>
    </select>
</p>
</form>

</body>
</html>

