<%@ page import="kzbitlab.servlet.LoginServlet" %>
<%@ page import="kzbitlab.database.DBManager" %><%--
  Created by IntelliJ IDEA.
  User: Yera
  Date: 07.10.2023
  Time: 14:59
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
<body>
<div>
    <%@include file="navbar.jsp" %>
</div>
<div>
    <form action="/login" method="get">
        <br>

        <p class="text-center">Login <input type="text" name="login"></p>
        <p class="text-center">Password <input type="text" name="password"></p>


        <h1 class="text-center">Error:<%=request.getAttribute("FAIL")%>
        </h1>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary mx-auto">Login</button>
        </div>


    </form>
</div>
</body>
</html>
