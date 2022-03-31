<%@ include file="/WEB-INF/fragments/ConnectedHeaderAndNavBar.jspf"%>
	<fmt:setBundle
	basename="fr.eni.projetEncheres.internationalisation.messages_encheres" />

<h1>
	<fmt:message key="msg_Success"/>
</h1>
<!--<c:choose>
<c:when test="${session.scope.userID > 0}">
Hoy hoy hoy
</c:when>
<c:when test="${session.scope.userID <= 0}">
<c:redirect url="TestServletAndFunction.jsp">
  <c:param name="id" value="123"/>
</c:redirect>
</c:when>
</c:choose>-->
<!-- <div class="container"> -->
<!-- 	<div class="row g-5"> -->
<!-- 		<div class="col-12 col-md-6"> -->
<!-- 			<div class="card-header-pills"> -->
<!-- 				<div class="card-body"> -->
<!-- 					<iframe -->
<!-- 						src="https://www.youtube.com/embed/Go7gn6dugu0?autoplay=1&modestbranding=1&showinfo=0&rel=0&iv_load_policy=3&theme=light" -->
<!-- 						width="560" height="315" frameborder="0"></iframe> -->

<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->


 	<h3>Search by Alias</h3>
 				<div class="div-container-fluid col-md-4 ">
        <form action="<%=request.getContextPath()%>/DisplayUsers" method="post">
            <div class="form-outline mb-4">
                <h3>${updateErrorMessage}</h3>
                <input type="text" id="alias" class="form-control" name="alias" value=${requestScope.alias}> <label
                    class="form-label" for="alias" name="alias">
                    <fmt:message key="msg_alias" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="firstName" class="form-control" name="firstName" value=${requestScope.firstName}>
                <label class="form-label" for="firstName" name="firstName">
                    <fmt:message key="msg_firstName" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="lastName" class="form-control" name="lastName" value=${requestScope.lastName}>
                <label class="form-label" for="lastName" name="lastName">
                    <fmt:message key="msg_lastName" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="email" class="form-control" name="email" value=${requestScope.email}> <label
                    class="form-label" for="email" name="email">
                    <fmt:message key="msg_email" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="telephone" class="form-control" name="requestScope.telephone"
                    value=${requestScope.telephone} > <label class="form-label" for="telephone" name="telephone" }>
                    <fmt:message key="msg_phone" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="street" class="form-control" name="street" value=${requestScope.street}> <label
                    class="form-label" for="street" name="street" }>
                    <fmt:message key="msg_street" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="zipCode" class="form-control" name="zipCode" value=${requestScope.postalCode}>
                <label class="form-label" for="zipCode" name="zipCode" }>
                    <fmt:message key="msg_postalCode" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="city" class="form-control" name="city" value=${requestScope.city}> <label
                    class="form-label" for="city" name="city">
                    <fmt:message key="msg_city" />
                </label>
            </div>
            <div class="form-outline mb-4">
                <input type="text" id="userID" class="form-control" name="userID" value=${requestScope.userID}> <label
                    class="form-label" for="userID" name="userID">
                    <fmt:message key="msg_userID" />
                </label>
            </div>

            <button type="submit" class="btn btn-dark btn-block mb-4" value="submit">
                <fmt:message key="msg_lycos" />
            </button>
        </form>
    </div>
    
    
    <div class="div-container-fluid col-md-4 ">
     <form action="<%=request.getContextPath()%>/DeleteUser" method="post">
            <div class="form-outline mb-4">
                <input type="text" id="userIDToDelete" class="form-control" name="userIDToDelete" > <label
                    class="form-label" for="userIDToDelete" name="userIDToDelete">
                    <fmt:message key="msg_userIdToDelet" />
                </label>
            </div>

            <button type="submit" class="btn btn-dark btn-block mb-4" value="submit">
                <fmt:message key="msg_delete" />
            </button>
            </form>
            </div>
 
 <%@ include file="/WEB-INF/fragments/Footer.jspf"%>