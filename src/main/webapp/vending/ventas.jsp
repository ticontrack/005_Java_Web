<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vending</title>
</head>
<body>
<h1>Máquina de Refrescos</h1>

Hoy es <%=   LocalDate.now()    %>

<hr/>
 
 	<h2> Elija un refresco e introduzca el precio</h2>
    

<form action="vender.do" method="post">

	<c:forEach items="${ requestScope.refrescos }" var="refresco" begin="0" end="0" > 
    <input type="radio" name="tipo"  value="${ refresco.tipo.nombre }" checked="checked"/>
		${ refresco.tipo.nombre } precio: ${ refresco.precio } stock:  #${ refresco.stock }
	</c:forEach>
	
	<br />	
	<c:forEach items="${ requestScope.refrescos }" var="refresco" begin="1"> 
		
		<input type="radio" name="tipo"  value="${ refresco.tipo.nombre }"/>
		${ refresco.tipo.nombre } precio: ${ refresco.precio } stock:  #${ refresco.stock }
		
	<br />	
	</c:forEach>
	
	<br />	
	Introduce importe en céntimos: <input type="number" min="20" max="200" name="importe"/>

    <input type="submit" value="COMPRAR" />


</form>

<hr />
     Display:  ${ requestScope.msgOk } 
     Cambios  ${ requestScope.cambios } 
     Error; ${ requestScope.msgError } 

<hr />



</body>
</html>