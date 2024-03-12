<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<html>
<head></head>
<body>
   <h1>My custom login page</h1>
   <form:form name='f'>
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input type="submit" value="login" /></td>
         </tr>
      </table>
  </form:form>
</body>
</html>
