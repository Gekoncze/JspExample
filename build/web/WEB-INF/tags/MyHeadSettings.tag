<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="sets common head settings for all pages" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<c:choose>
    <c:when test="${cookie.containsKey('theme')}">
        <link rel="stylesheet" href="/ObjFileUtility/styles-${cookie.theme.value}.css">
    </c:when>
    <c:otherwise>
        <link rel="stylesheet" href="/ObjFileUtility/styles-blue.css">
    </c:otherwise>
</c:choose>