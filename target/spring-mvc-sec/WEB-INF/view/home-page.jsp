<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<html>
    <title>hello-world page</title>
    <body>
        <a href="/spring-mvc-sec/coder">go to coder page</a>
        <br>
        <a href="/spring-mvc-sec/trainer">got to trainer page</a>
        <br/>

        <form:form action="logout" method="POST">
           <input type="submit" value="logout">
        </form:form>
    </body>
</html>