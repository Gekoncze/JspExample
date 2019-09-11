<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglibrary.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Obj and mtl validation result</title>
        <my:MyHeadSettings/>
    </head>
    <body>
        <my:MyHeader/><br>
        <main>
            <jsp:useBean id="model" scope="session" class="beans.ModelBean" />
            <h1>Obj and mtl validation result</h1>
            <h2>Summary:</h2>
            <p>
                Number of vertices: ${model.vertexCount}<br>
                Number of texture coordinates: ${model.textureCoordinateCount}<br>
                Number of normals: ${model.normalCount}<br>
                Number of faces: ${model.faceCount}<br>
                Min x: ${model.minX}<br>
                Max x: ${model.maxX}<br>
                Min y: ${model.minY}<br>
                Max y: ${model.maxY}<br>
                Min z: ${model.minZ}<br>
                Max z: ${model.maxZ}
            </p>
            <c:if test="${model.errorLog.size() > 0}">
                <h2>Errors:</h2>
            </c:if>
            <c:if test="${model.errorLog.size() <= 0}">
                <h2>No errors found.</h2>
            </c:if>
            <c:forEach items="${model.errorLog}" var="error">
                ${error}<br>
            </c:forEach>
            <my:MyCode>${model.objCode}</my:MyCode>
            <h2>Preview</h2>
            <my:MyPreview/>
        </main>
        <my:MyFooter/>
    </body>
</html>
