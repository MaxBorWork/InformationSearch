<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Alena Pashkevich
  Date: 13.09.2019
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Поисковая система ОАО "Борисевич И Пашкевич. Ты не спрячешься."</title>
    <style>

    </style>
</head>
<body>
<form action="" method="POST">
    <%
       String reqToSearch =(String) request.getAttribute("reqToSearch");
        if (reqToSearch != null && !reqToSearch.equals("")) {
            out.println("<input type=\"text\" size=\"100\" width=\"20\" style=\"display: inline-block;\" name=\"search\""
                    + "value = \""+reqToSearch+"\">");
        } else {
            out.println("<input type=\"text\" size=\"100\" width=\"20\" style=\"display: inline-block;\" name=\"search\">");
        }

    %>
    <button type="submit" value="Поиск" style="display: inline-block;"  name="searchButton" >Поиск</button>
</form>
<%
    List<String> results = (List<String>) request.getAttribute("resultList");

    if (results != null && !results.isEmpty()) {
        out.println("<ul>");
        for (String result : results) {
            out.println("<li class=\"chapters\">");
            out.println(result);
            //TODO(ссылочкі на док еслі нужно будет)
           // out.println("<a href =\"/aipos/chapter?part="+partIndex +"&chapter="+chapterNames.indexOf(chapter)+"\">" + chapter+"</a>");

            out.println("</li>");
        }
        out.println("</ul>");

    }
%>

</body>
</html>
