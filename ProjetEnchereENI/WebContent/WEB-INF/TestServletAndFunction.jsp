<%@ include file="fragments/HeaderAndNavBar.jspf"%>
<div align="center">
	<h2>Login</h2>
</div>

<form action="<%=request.getContextPath()%>/Login" method="post">
	<table>
	<thead>${loginErrorMessage}</thead>
		<tr>
			<td>email</td>
			<td><input type="text" name="email" value="${cookie.HHAconnection.value}"></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="password" placeholder="8 char min"></td>
		</tr>
		<tr>
			<td><input type="submit" value="login"></td>
		</tr>

	</table>
</form>


<div align="center">
	<h2>User Registration Form</h2>
	<form action="<%=request.getContextPath()%>/register" method="post">
		<table>
		<thead>${registerErrorMessage}</thead>
			<tr>
				<td>Pseudo</td>
				<td><input type="text" name="alias" /></td>
			</tr>
			<tr>
				<td>Nom</td>
				<td><input type="text" name="lastName" /></td>
			</tr>
			<tr>
				<td>Prenom</td>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Telephone</td>
				<td><input type="text" name="telephone" /></td>
			</tr>
			<tr>
				<td>rue</td>
				<td><input type="text" name="street" /></td>
			</tr>
			<tr>
				<td>Code Postal</td>
				<td><input type="text" name="postalCode" /></td>
			</tr>
			<tr>
				<td>Ville</td>
				<td><input type="text" name="city" /></td>
			</tr>
			<tr>
				<td>Mot de Passe</td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>
		<input type="submit" value="submit" />

	</form>
</div>

<form action="<%=request.getContextPath()%>/ServletDeleteUser"
	method="post">
	<table>
		<tr>
			<td>UserId</td>
			<td><input type="text" name="userId" /></td>
		</tr>
		<tr>
			<td>DELETE USER</td>
			<td><input type="submit" value="submit"></td>
		</tr>
	</table>
</form>
<%@ include file="fragments/Footer.jspf"%>