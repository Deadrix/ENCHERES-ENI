<%@ include file="/WEB-INF/fragments/ConnectedHeaderAndNavBar.jspf"%>

<h1>
	<fmt:message key="msg_Success" bundle="${base}"></fmt:message>
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
<div class="container">
	<div class="row g-5">
		<div class="col-12 col-md-6">
			<div class="card-header-pills">
				<div class="card-body">
					<iframe
						src="https://www.youtube.com/embed/Go7gn6dugu0?autoplay=1&modestbranding=1&showinfo=0&rel=0&iv_load_policy=3&theme=light"
						width="560" height="315" frameborder="0"></iframe>

				</div>
			</div>
		</div>
	</div>
</div>

<form action="<%=request.getContextPath()%>/Logout" method="post" id="logout">
<button type="submit" class="btn btn-dark btn-block mb-4"
						value="submit">
						<fmt:message key="msg_logout" bundle="${base}" />
					</button></form>

 
 					<form action="<%=request.getContextPath()%>/DisplayUsers"
						method="post">
						<div class="form-outline mb-4">
							<h3>${updateErrorMessage}</h3>
							<input type="text" id="alias" class="form-control" name="alias"
								value=${param.alias} /> <label class="form-label" for="alias"
								name="alias"> <fmt:message key="msg_alias"
									bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="firstName" class="form-control"
								name="firstName" value=${request.firstName} /> <label
								class="form-label" for="firstName" name="firstName"> <fmt:message
									key="msg_firstName" bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="lastName" class="form-control"
								name="lastName" value=${requestScope.lastName} /> <label class="form-label"
								for="lastName" name="lastName"> <fmt:message
									key="msg_lastName" bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="email" class="form-control" name="email"
								value=${email } /> <label class="form-label" for="email"
								name="email"> <fmt:message key="msg_email"
									bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="number" id="telephone" class="form-control"
								name="telephone" value=${tempuUser.telephone } /> <label
								class="form-label" for="telephone" name="telephone"}> <fmt:message
									key="msg_phone" bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="street" class="form-control" name="street"
								value=${street } /> <label class="form-label" for="street"
								name="street"}> <fmt:message key="msg_street"
									bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="zipCode" class="form-control"
								name="zipCode" value=${postalCode } /> <label
								class="form-label" for="zipCode" name="zipCode"}> <fmt:message
									key="msg_postalCode" bundle="${base}" />
							</label>
						</div>
						<div class="form-outline mb-4">
							<input type="text" id="city" class="form-control" name="city"
								value=${city } /> <label class="form-label" for="city"
								name="city"> <fmt:message key="msg_city"
									bundle="${base}" />
							</label>
						</div>
					
					<button type="submit" class="btn btn-dark btn-block mb-4"
							value="submit">
							<fmt:message key="Go Fetch Lycos" bundle="${base}" />
						</button>
					</form>
 
 <%@ include file="/WEB-INF/fragments/Footer.jspf"%>