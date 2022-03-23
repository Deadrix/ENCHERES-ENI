<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetEncheres.model.bo.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier Utilisateur</title>
</head>
	<body>	
	
		<form action="ServletModifyUser" method="POST">
		
			<input type="hidden" name="userId" value="${user.id}"/>
			<label for="pseudo">Pseudo: </label><input type="text" id="pseudo" name="txtAlias" value="${user.alias}"/>
			<br>
			<label for="lastName">Nom: </label><input type="text" id="lastName"" name="txtLastName" value="${user.lastName}"/>
			<br>
			<label for="firstName">Prenom: </label><input type="text" id="firstName" name="txtFirstName" value="${user.firstName}"/>
			<br>
			<label for="email">Email: </label><input type="text" id="email" name="txtEmail" value="${user.email}"/>
			<br>
			<label for="phone">Numéro de téléphone: </label><input type="tel" id="phone" name="txtPhone" value="${user.telephone}" pattern="[0-9]{10}" required>
			<br>
			<label for="street">Adresse: </label><input type="text" id="street" name="txtStreet" value="${user.street}"/>
			<br>
			<label for="postalCode">Code Postal: </label><input type="text" id="postalCode" name="txtpostalCode" value="${user.postalCode}"/>
			<br>
			<label for="city">Ville: </label><input type="text" id="city"" name="txtcity" value="${user.city}"/>
			<br>
		
			<input type="submit" name="valider" value="Enregistrer Modification"/>
			
		</form> 
		
		<form action="ServletDeleteUser" method="POST">
		
			<input type="submit" name="delete" value="Supprimer le compte"/>
		
		</form>	
	
	</body>
	
</html>