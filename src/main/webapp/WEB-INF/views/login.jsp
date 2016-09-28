<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main_template title="Connexion">

<jsp:attribute name="content">

    <div class="row">
        <div class="large-4 large-offset-4 columns">
            <h3>Connexion</h3>
            <c:if test="${not empty errorMessage}">
                <div class="alert callout">
                    <p>${errorMessage}</p>
                </div>
            </c:if>
            <p><a href="${pageContext.request.contextPath}/register">Création d'un nouveau compte</a></p>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" name="username" placeholder="Nom d'utilisateur" value="${pageContext.request.getParameter("username")}">
                <input type="password" name="password" placeholder="Mot de passe">
                <input type="submit" class="expanded button" value="Connexion">
            </form>
        </div>
    </div>

</jsp:attribute>

</t:main_template>