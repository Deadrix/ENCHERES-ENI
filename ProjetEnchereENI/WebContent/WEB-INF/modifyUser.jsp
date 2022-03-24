<%@ include file="fragments/HeaderAndNavBar.jspf" %>
    <form action="ServletModifyUser" method="POST">



        <input type="hidden" name="userId" value="${user.id}" /> <label for="pseudo">
            <fmt:message key="msg_pseudo" bundle="${base}"/>

        </label><input type="text" id="pseudo" name="txtAlias" value="${user.alias}" required />
        <br> <label for="lastName">
            <fmt:message key="msg_lastName" bundle="${base}"/>
        </label><input type="text" id="lastName" name="txtLastName" value="${user.lastName}" required /> <br> <label
            for="firstName">
            <fmt:message key="msg_firstName" bundle="${base}"/>
        </label><input type="text" id="firstName" name="txtFirstName" value="${user.firstName}" required /> <br> <label
            for="email">
            <fmt:message key="msg_email" bundle="${base}"/>
        </label><input type="text" id="email" name="txtEmail" value="${user.email}" required /> <br>
        <label for="phone">
            <fmt:message key="msg_phone" bundle="${base}"/>
        </label><input type="tel" id="phone" name="txtPhone" value="${user.telephone}" pattern="[0-9]{10}" required />
        <br> <label for="street">
            <fmt:message key="msg_street" bundle="${base}"/>
        </label><input type="text" id="street" name="txtStreet" value="${user.street}" required /> <br> <label
            for="postalCode">
            <fmt:message key="msg_postalCode" bundle="${base}"/>
        </label><input type="text" id="postalCode" name="txtpostalCode" value="${user.postalCode}" required /> <br>
        <label for="city">
            <fmt:message key="msg_city" bundle="${base}"/>
        </label><input type="text" id="city" name="txtcity" value="${user.city}" required />
        <br> <input type="submit" name="valider" value="<fmt:message key="msg_confirmModification" bundle="${base}"/>" />

    </form>

    <form action="ServletDeleteUser" method="POST">
        <input type="submit" name="delete" value="<fmt:message key="msg_deleteAccount" bundle="${base}"/>"
        onclick="return confirm('<fmt:message key="msg_confirmDeleteAccount" bundle="${base}"/>');" />
    </form>
    <%@ include file="fragments/Footer.jspf" %>