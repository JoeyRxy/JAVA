<%@ page import="java.sql.*" %>
<%@ page import="mine.learn.jspbean.LogInDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Check</title>
</head>

<body>
    <%
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        boolean check = false;
        try {
            check = LogInDAO.check(uname,upwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <%=(check)?("Success!"):("User doesn't exist OR Password is WRONG!")%>
</body>

</html>