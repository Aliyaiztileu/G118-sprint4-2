<%@ page import="kzbitlab.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="kzbitlab.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Yera
  Date: 07.10.2023
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

</head>
<%@include file="navbar.jsp" %>
<body>


<h1 class="text-center">Welcome, <%=request.getAttribute("Full_name")%>!</h1>
</body>
</html>
