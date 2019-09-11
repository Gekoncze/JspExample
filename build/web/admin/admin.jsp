<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglibrary.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin</title>
        <my:MyHeadSettings/>
    </head>
    <body>
        <my:MyHeader /><br>
        <main>
            <jsp:useBean id="count" scope="session" class="beans.ValidationCountBean" />
            <h1>Admin</h1>
            Number of validations since startup: ${count.validationCount}<br><br>
            <form action="logout.jsp" method="post">
                <button type="submit">Logout</button>
            </form>
        </main>
        <my:MyFooter/>
    </body>
</html>
