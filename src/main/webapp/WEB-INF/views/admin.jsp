<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main_template title="Page d'accueil">

<jsp:attribute name="content">

    <div class="row">
        <div class="large-12 columns">
            <h3>Administration</h3>
            <h5>Registered users</h5>
            <ul>
                <c:forEach items="${requestScope.users}" var="user">
                    <li>${user.getUsername()}</li>
                </c:forEach>
            </ul>
        </div>
    </div>

</jsp:attribute>

</t:main_template>