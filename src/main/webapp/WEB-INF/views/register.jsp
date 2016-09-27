<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main_template title="Création d'un nouveau compte">

<jsp:attribute name="content">

    <div class="row">
        <div class="large-4 large-offset-4 columns">
            <h3>Inscription</h3>
            <c:if test="${not empty errorMessage}">
                <div class="alert callout">
                    <p>${errorMessage}</p>
                </div>
            </c:if>
            <form action="register" method="post">
                <input type="text" name="firstname" placeholder="Prénom" value="${pageContext.request.getParameter("firstname")}">
                <input type="text" name="lastname" placeholder="Nom" value="${pageContext.request.getParameter("lastname")}">
                <input type="text" name="email" placeholder="Email" value="${pageContext.request.getParameter("email")}">
                <input type="text" name="username" placeholder="Nom d'utilisateur" value="${pageContext.request.getParameter("username")}">
                <input type="password" name="password" placeholder="Mot de passe">
                <input type="submit" class="expanded button" value="Inscription">
            </form>
        </div>
    </div>

</jsp:attribute>

</t:main_template>