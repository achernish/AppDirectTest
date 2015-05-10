<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>

<div id="openId">
    <c:url value="/openid" var="loginUrl"/>
    <div><c:out value="${error}"/></div>
    <form action="${loginUrl}" method="POST">
        <div>
            <label for="openid_identifier">OpenID:</label>
            <input name="openid_identifier" value="https://www.appdirect.com/openid/id" size="50" maxlength="100"
                   type="text"/>
            <img src="http://openid.net/wordpress-content/themes/openid/images/logo_openid.png" alt="OpenID"/>
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>
</div>

</body>
</html>
