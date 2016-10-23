<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template title="Registered users">

<jsp:attribute name="body">

<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>So many users</h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table id="datatable" class="table table-striped table-bordered">
                    <thead>
                        <th>Firstnam</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th>Username</th>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

</jsp:attribute>

<jsp:attribute name="footer">

<!-- Datatables -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>

<script>
$(document).ready(function() {
    $('#datatable').DataTable( {
        ajax: {
            url: "${pageContext.request.contextPath}/api/users",
            type: "GET",
            dataSrc: ""
        },
        columns: [
            { data: "firstname" },
            { data: "lastname" },
            { data: "email" },
            { data: "username" }
        ]
    });
});
</script>

</jsp:attribute>

</t:template>