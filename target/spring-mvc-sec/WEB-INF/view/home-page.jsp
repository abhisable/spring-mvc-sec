<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8" isELIgnored = "false" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <title>hello-world page</title>
    <body>
        <sec:authorize access="hasAuthority('ROLE_Coder')">
        <a href="/spring-mvc-sec/coder">go to coder page</a>
       </sec:authorize>
        <br>
        <sec:authorize access="hasAuthority('ROLE_Trainer')">
        <a href="/spring-mvc-sec/trainer">got to trainer page</a>
        </sec:authorize>
        <br/>

        <form:form action="logout" method="POST">
           <input type="submit" value="logout">
        </form:form>
    </body>
</html>