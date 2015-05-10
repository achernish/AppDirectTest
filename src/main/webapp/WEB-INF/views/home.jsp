<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome to the AppDirect Test!</h1>
<ul>
    <c:choose>
        <c:when test="${isLoggedIn==true}">
            <li><a href="exit">Logout</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="login">Login</a></li>
        </c:otherwise>
    </c:choose>
    <li><a href="customers">Customers</a></li>
</ul>
<table>
    <tr>
        <td><strong>OAuth consumer key:</strong></td>
        <td><c:out value="${oauthConsumerKey}"/></td>
    </tr>
    <tr>
        <td><strong>OAuth consumer secret:</strong></td>
        <td><c:out value="${oauthConsumerSecret}"/></td>
    </tr>
    <tr>
        <td><strong>Login Information:</strong></td>
        <td>
            <table>
                <tr>
                    <td><strong>First Name:</strong></td>
                    <td><c:out value="${firstName}"/></td>
                </tr>
                <tr>
                    <td><strong>Last Name:</strong></td>
                    <td><c:out value="${lastName}"/></td>
                </tr>
                <tr>
                    <td><strong>OpenID:</strong></td>
                    <td><c:out value="${openId}"/></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>