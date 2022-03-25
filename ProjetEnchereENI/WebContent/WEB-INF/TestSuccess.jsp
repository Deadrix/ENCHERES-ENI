<%@ include file="fragments/HeaderAndNavBar.jspf"%>

<h1>
	<fmt:message key="msg_Success" bundle="${base}"></fmt:message>
</h1>
<div class="container">
	<div class="row g-5">
		<div class="col-12 col-md-6">
			<div class="card-header-pills">
				<div class="card-body">
					<a href="<%=request.getContextPath()%>/register"> <img src="Assets/legend.jpg" alt="redirect"
						class="card-img"></a>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="fragments/Footer.jspf"%>