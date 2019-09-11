<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="my" uri="/WEB-INF/tlds/mytaglibrary.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Error</title>
        <my:MyHeadSettings/>
    </head>
    <body>
        <my:MyHeader /><br>
        <main>
            <h1>${pageContext.errorData.statusCode}
                <c:choose>
                    <c:when test="${pageContext.errorData.statusCode == 401}">&nbsp;Unauthorized</c:when>
                    <c:when test="${pageContext.errorData.statusCode == 404}">&nbsp;Not Found</c:when>
                    <c:when test="${pageContext.errorData.statusCode == 403}">&nbsp;Forbidden</c:when>
                    <c:when test="${pageContext.errorData.statusCode == 500}">&nbsp;Internal Server Error</c:when>
                    <c:otherwise>&nbsp;Error</c:otherwise>
                </c:choose>
            </h1>
            <c:if test="${not empty pageContext.exception}">
                <h2>${pageContext.exception}</h2>
                <c:forEach var = "trace" items = "${pageContext.exception.stackTrace}">
                    ${trace}<br>
                 </c:forEach>
            </c:if>
        </main>
        <my:MyFooter/>
    </body>
</html>
