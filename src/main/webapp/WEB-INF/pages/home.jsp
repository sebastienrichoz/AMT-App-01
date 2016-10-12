<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:main_template title="Homepage">

    <jsp:attribute name="content">

        <div class="row">
            <div class="large-12 columns">
                <h3>Homepage</h3>
                <p><a href="${pageContext.request.contextPath}/register">Create an account</a> or <a href="${pageContext.request.contextPath}/login">login</a></p>
            </div>
        </div>

    </jsp:attribute>

</t:main_template>