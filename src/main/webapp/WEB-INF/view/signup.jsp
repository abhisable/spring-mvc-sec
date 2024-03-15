<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" isELIgnored = "false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
  <h1>signup here!</h1>
  <form:form action="singup-processing" method="POST" modelAttribute="signupdto">
  username: <form:input path="username"/>
  <br/>
  password: <form:input path="password"/>
  <br/>
  <input type="submit" value="signup">

  </form:form>
</body>
</html>