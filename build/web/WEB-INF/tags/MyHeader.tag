<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="header for all pages" pageEncoding="UTF-8"%>
<header>
    <a href="/ObjFileUtility/index.jsp">
        <c:choose>
            <c:when test="${cookie.containsKey('theme')}">
                <img src="/ObjFileUtility/images/logo-${cookie.theme.value}.png" alt="header" />
            </c:when>
            <c:otherwise>
                <img src="/ObjFileUtility/images/logo-blue.png" alt="header" />
            </c:otherwise>
        </c:choose>
    </a>
</header>