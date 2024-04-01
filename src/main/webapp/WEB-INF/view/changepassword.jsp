
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" isELIgnored = "false" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div align="center">
        <h1>Reset Password</h1>
        <c:if test="${param.notMatched != null}" ><i style="color: red;">new password and confirm password doens't exist ! try again</i></c:if>

        <c:if test="${param.invalidPassword != null}" ><i style="color: red;">wrong password entered ! try again</i></c:if>
    <form:form action="savePassword" method="POST" modelAttribute="password-change">
          <label>old password: </label><form:input path="oldPassword"/><br>
          <label>new password: </label><form:input  path="newPassword"/><br>
          <label>confirm password: </label><form:input path="confirmPassword"/><br>
          <input type="submit" value="change">
    </form:form>
</div>
</body>
</html>