<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main_template title="Login">

    <jsp:attribute name="content">

        <div class="row">
            <div class="large-12 columns">
                <h3>Login</h3>
                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="alert callout">
                        <p>${requestScope.errorMessage}</p>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <input type="text" name="username" placeholder="Username" value="${pageContext.request.getParameter("username")}">
                    <input type="password" name="password" placeholder="Password">
                    <input type="submit" class="expanded button" value="Login">
                </form>
                <p><a href="${pageContext.request.contextPath}/register">Don't have an account yet ?</a></p>
            </div>
        </div>

    </jsp:attribute>

</t:main_template>