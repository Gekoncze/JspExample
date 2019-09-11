<%-- 
    Document   : logout
    Created on : 14.5.2017, 11:04:27
    Author     : Me
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
            session.invalidate();
            response.sendRedirect("/ObjFileUtility/index.jsp");
        %>
    </body>
</html>
