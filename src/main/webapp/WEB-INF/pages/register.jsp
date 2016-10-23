<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template title="Registration">

<jsp:attribute name="body">

<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Register and stay tuned <small><a href="${pageContext.request.contextPath}/login">Already have an account ?</a></small></h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <br>

                <c:if test="${not empty requestScope.errorMessage}">
                    <div class="alert alert-danger">${requestScope.errorMessage}</div>
                    <br>
                </c:if>

                <form class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/register" method="post">

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="firstname">Firstname</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="firstname" name="firstname" value="${pageContext.request.getParameter("firstname")}" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="lastname">Lastname</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="lastname" name="lastname" value="${pageContext.request.getParameter("lastname")}" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="email" name="email" value="${pageContext.request.getParameter("email")}" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="ln_solid"></div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">Username <span class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="text" id="username" name="username" value="${pageContext.request.getParameter("username")}" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">Password <span class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="password" id="password" name="password" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="passwordCtrl">Password verification <span class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="password" id="passwordCtrl" name="passwordCtrl" class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="ln_solid"></div>

                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                            <button type="submit" class="btn btn-success">Register</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

</jsp:attribute>

</t:template>