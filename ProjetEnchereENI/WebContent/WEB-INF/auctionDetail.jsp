


<c:choose>
	<c:when test="${isBefore==1 && auctionWinnerId==noUser}">
		<h2>
			<fmt:message key="msg_remporte"></fmt:message>
		</h2>
	</c:when>
	<c:when test="${isBefore==1 && seller.userId==noUser}">
		<h2>${auctionWinnerName}
			<fmt:message key="msg_remporte2"></fmt:message>
		</h2>
	</c:when>
	<c:when test="${isBefore==0}">
		<h1>
			<fmt:message key="msg_titre_details"></fmt:message>
		</h1>
	</c:when>
</c:choose>

<br>



<h3>${articleSold.articleName}</h3>
<br>
<label id="labelauctiondetail"><fmt:message
		key="msg_description"></fmt:message></label>
<textarea> ${articleSold.description}</textarea>
<br>

<c:if test="${isBefore==0}">
	<label id="labelauctiondetail"><fmt:message key="msg_categorie"></fmt:message>
		: </label>
	<textarea> ${category.libelle}</textarea>
	<br>
</c:if>
<label id="labelauctiondetail"><fmt:message key="msg_best"></fmt:message>
	: </label>
<textarea> ${highestAuction.itemPrice}</textarea>
<br>
<label id="labelauctiondetail"><fmt:message
		key="msg_prix_initial"></fmt:message> : </label>
<textarea> ${articleSold.initialPrice}</textarea>
<br>

<c:if test="${isBefore==0 ||isBefore==1 && seller.userId==noUser}">
	<label id="labelauctiondetail"><fmt:message
			key="msg_endAuction"></fmt:message> : </label>
	<textarea> <fmt:formatDate type="both"
			value="${highestAuction.auctionDate}" /></textarea>
	<br>

</c:if>
<label id="labelauctiondetail"><fmt:message key="msg_retrait"></fmt:message>
	: </label>
<textarea> ${pickUpLocation.rueRetrait} </textarea>
<br>
<label id="labelauctiondetail"></label>
<textarea> ${pickUpLocation.codePostalRetrait} -
${pickUpLocation.villeRetrait} </textarea>
<br>
<label id="labelauctiondetail"><fmt:message key="msg_vendeur"></fmt:message>
	: </label>
<textarea> ${seller.alias} </textarea>
<br>
<c:if test="${isBefore==1 && auctionWinnerId==noUser}">
	<!-- <liTel :>${user.telephone}</li> -->
</c:if>


<%-- <c:if test="${isBefore==0}"> --%>
<!-- 	<form method="post" action="AuctionDetails"> -->
<!-- 		<label id="labelauctiondetail" -->
<%-- 			${status eq  'ban'  ? 'hidden="true"' : '' || noUser eq  no_vendeur  ? 'hidden="true"' : ''}><fmt:message --%>
<%-- 				key="msg_offre"></fmt:message> : </label> <input type="number" name="prix" --%>
<%-- 			${status eq  'ban'  ? 'hidden="true"' : '' || noUser eq  no_vendeur  ? 'hidden="true"' : ''}> --%>
<!-- 		<input type="submit" -->
<%-- 			value="<fmt:message key="msg_encherir"></fmt:message>" --%>
<%-- 			${status eq  'ban'  ? 'hidden="true"' : '' ||  noUser eq  no_vendeur  ? 'hidden="true"' : ''}> --%>
<!-- 	</form> -->
<%-- </c:if> --%>


<%-- <c:if test="${isBefore==1 && aquereur==noUser}"> --%>
<%-- 	<a href="AuctionList" class="btn btn-info" role="button"><fmt:message --%>
<%-- 			key="msg_retour"></fmt:message></a> --%>
<%-- </c:if> --%>

<%-- <c:if test="${isBefore==1 && user.no_utilisateur==noUser}"> --%>
<!-- 	<form method="post" action="AuctionDetails"> -->
<!-- 		<input type="submit" class="btn btn-info" -->
<%-- 			value="<fmt:message key="msg_retrait_ok"></fmt:message>" --%>
<!-- 			name="retrait"> -->
<!-- 	</form> -->
<%-- </c:if> --%>