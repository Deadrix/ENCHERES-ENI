<%@ include file="fragments/HeaderAndNavBar.jspf"%>


<c:if test="${ user == null }">
	<h3>
		<fmt:message key="msg_WhatIsThisShit" />
	</h3>
</c:if>

<c:if test="${ user != null }">
	<c:if
		test="${currentUser != null && currentUser.userId == user.userId }">
		<h1>
			<fmt:message key="msg_displayUser">
				<fmt:param value="${user.alias}"></fmt:param>
			</fmt:message>
		</h1>
	</c:if>

	<c:if
		test="${currentUser == null || currentUser.userId != user.userId }">
		<h1>
			<fmt:message key="msg_displayUser">
				<fmt:param value="${user.alias}"></fmt:param>
			</fmt:message>
		</h1>
	</c:if>

	<div>
		<table>
			<tr>
				<td><fmt:message key="msg_lastName" /></td>
				<td>${user.lastName}</td>
			</tr>
			<tr>
				<td><fmt:message key="msg_firstName" /></td>
				<td>${user.firstName}</td>
			</tr>
			<tr>
				<td><fmt:message key="msg_email" /></td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td><fmt:message key="msg_telephone" /></td>
				<td>${user.telephone}</td>
			</tr>
			<tr>
				<td><fmt:message key="msg_address" /></td>
				<td>${user.street}</td>
			</tr>
			<tr>
				<td><fmt:message key="msg_postalCode" /></td>
				<td>${user.postalCode}</td>
			</tr>
			<tr>
				<td><fmt:message key="msg_city" /></td>
				<td>${user.city}</td>
			</tr>
		</table>
	</div>

	<div>
		<h3>
			<fmt:message key="msg_bidsByUser">
				<fmt:param value="${user.alias}"></fmt:param>
			</fmt:message>
			<c:forEach var="auction" items="${user.getAuctions}">
				<li>${auction.toString()}</li>
			</c:forEach>
		</h3>
	</div>
	<div>
		<h3>
			<fmt:message key="msg_soldByUser">
				<fmt:param value="${user.alias}"></fmt:param>
			</fmt:message>
			<c:forEach var="soldItem" items="${user.getSoldArticle}">
				<li>${soldItem.toString()}</li>
			</c:forEach>
		</h3>
	</div>
	<div>
		<h3>
			<fmt:message key="msg_boughtByUser">
				<fmt:param value="${user.alias}"></fmt:param>
			</fmt:message>
			<c:forEach var="boughtItem" items="${user.getBoughtArticle}">
				<li>${boughtItem.toString()}</li>
			</c:forEach>
		</h3>
	</div>
</c:if>




<%@ include file="fragments/Footer.jspf"%>