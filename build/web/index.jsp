<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglibrary.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Obj validator</title>
        <my:MyHeadSettings/>
    </head>
    <body>
        <my:MyHeader /><br>
        <main>
            <jsp:useBean id="model" scope="session" class="beans.ModelBean" />
            <h1>Obj validator</h1>
            <p>
                You can validate obj files here. It cannot handle large files, so it should be used only for testings small files.<br>
                Validation of objects using advanced primitives like parameter space vertices, nurbs, polygons, etc. is not supported.<br>
                Validation of mtl file is not supported. Mtl related elements and groups are skipped.<br>
                More information about obj files can be found on <a href="https://en.wikipedia.org/wiki/Wavefront_.obj_file">Wikipedia</a>.
            </p>
            <form action="ValidationController" method="post">
                <table>
                    <tr>
                        <td>
                            <textarea wrap="off" placeholder="Insert OBJ file content here" cols="75" rows="25" name="objcode"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <button type="submit">Validate</button>
                        </td>
                    </tr>
                </table>
            </form>
        </main>
        <my:MyFooter/>
    </body>
</html>
