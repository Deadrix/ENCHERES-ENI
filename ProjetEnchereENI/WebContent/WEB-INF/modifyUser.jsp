<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetEncheres.model.bo.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="pageTitle_modifyUser"></fmt:message></title>
</head>
	<body>	
	
		<form action="ServletModifyUser" method="POST">
		
			<input type="hidden" name="userId" value="${user.id}"/>
			
			<label for="pseudo"><fmt:message key="msg_pseudo"></fmt:message></label><input type="text" id="pseudo" name="txtAlias" value="${user.alias}" required/>
			<br>
			<label for="lastName"><fmt:message key="msg_lastName"></fmt:message></label><input type="text" id="lastName" name="txtLastName" value="${user.lastName}" required/>
			<br>
			<label for="firstName"><fmt:message key="msg_firstName"></fmt:message></label><input type="text" id="firstName" name="txtFirstName" value="${user.firstName}" required/>
			<br>
			<label for="email"><fmt:message key="msg_email"></fmt:message></label><input type="text" id="email" name="txtEmail" value="${user.email}" required/>
			<br>
			<label for="phone"><fmt:message key="msg_phone"></fmt:message></label><input type="tel" id="phone" name="txtPhone" value="${user.telephone}" pattern="[0-9]{10}" required/>
			<br>
			<label for="street"><fmt:message key="msg_street"></fmt:message></label><input type="text" id="street" name="txtStreet" value="${user.street}" required/>
			<br>
			<label for="postalCode"><fmt:message key="msg_postalCode"></fmt:message></label><input type="text" id="postalCode" name="txtpostalCode" value="${user.postalCode}" required/>
			<br>
			<label for="city"><fmt:message key="msg_city"></fmt:message></label><input type="text" id="city" name="txtcity" value="${user.city}" required/>
			<br>		
			<input type="submit" name="valider" value="<fmt:message key="msg_confirmModification"></fmt:message>"/>
			
		</form> 
		
		<form action="ServletDeleteUser" method="POST">
		
			<input type="submit" name="delete" value="<fmt:message key="msg_deleteAccount"></fmt:message>" onclick="return confirm('<fmt:message key="msg_confirmDeleteAccount"></fmt:message>');"/>
		
		</form>	
	
	</body>
	
</html>