<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglibrary.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <my:MyHeadSettings/>
    </head>
    <body>
        <my:MyHeader /><br>
        <main>
            <jsp:useBean id="model" scope="session" class="beans.ModelBean" />
            <h1>Login</h1>
            <form action="j_security_check" method="post">
                <table>
                    <tr>
                        <td>
                            Username: 
                        </td>
                        <td>
                            <input type="text" name="j_username"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password: 
                        </td>
                        <td>
                            <input type="password" name="j_password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <button type="submit">Login</button>
                        </td>
                    </tr>
                </table>
            </form>
        </main>
        <my:MyFooter/>
    </body>
</html>
