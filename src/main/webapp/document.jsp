<%--
  Created by IntelliJ IDEA.
  User: Alena Pashkevich
  Date: 13.09.2019
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <h1>Поисковая система</h1>
    <%
    if(request.getAttribute("content")!= null){
        out.println(request.getAttribute("content"));
    }%>
</header>
</body>
</html>
