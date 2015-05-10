<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Customer Page</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>Customers</h1>
<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="80">First Name</th>
        <th width="80">Last Name</th>
        <th width="12">OpenID</th>
        <th width="60">Email</th>
        <th width="60">Edition</th>
    </tr>
    <c:forEach items="${listCustomers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.openId}</td>
            <td>${customer.email}</td>
            <td>${customer.edition}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
