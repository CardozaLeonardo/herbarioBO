<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.example.entity.*, com.example.dao.*"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wep App</title>
</head>
<%

DT_user dtu = new DT_user();
User[] users = dtu.getUsers();

%>

<style>
h1{
  color: white;
  text-align: center;
  margin-top: 200px;
  margin-bottom: 30px;
}

body{
background: #333;
}

li{
  color: white;
}
</style>
<body>
    <h1>HOLA, ¿CÓMO ESTÁS?</h1>
    <ul>
     <%for(User us: users) {%>
     <li><%=us.getId() %></li>
     <li><%=us.getUsername() %></li>
     <li>----------------------------</li>
     <%} %>
    </ul>
    
    <ul>
      <c:forEach var="usr" items="<%=users%>">
         <li>----------------------------</li>
         <li>${usr.password}</li>
      </c:forEach>
    </ul>
</body>
</html>