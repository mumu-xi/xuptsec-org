<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="login" method="post">
    <form:errors path="*"></form:errors>
   <%-- <form:errors path="username"></form:errors>--%>
    <br />
    <br />
</form:form>
</body>
</html>