<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main_template title="Registration page">

    <jsp:attribute name="content">

        <div class="row">
            <div class="large-12 columns">
                <h3>Inscription</h3>
                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="alert callout">
                        <p>${requestScope.errorMessage}</p>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/register" method="post">
                    <input type="text" name="firstname" placeholder="Firstname" value="${pageContext.request.getParameter("firstname")}">
                    <input type="text" name="lastname" placeholder="Lastname" value="${pageContext.request.getParameter("lastname")}">
                    <input type="text" name="email" placeholder="Email" value="${pageContext.request.getParameter("email")}">
                    <input type="text" name="username" placeholder="Username" value="${pageContext.request.getParameter("username")}">
                    <input type="password" name="password" placeholder="Password">
                    <input type="submit" class="expanded button" value="Register">
                </form>
                <p><a href="${pageContext.request.contextPath}/login">Already have an account ?</a></p>
            </div>
        </div>

    </jsp:attribute>

</t:main_template>