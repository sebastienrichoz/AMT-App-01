<%@ page pageEncoding="utf-8" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template title="Administration">

    <jsp:attribute name="content">

        <div class="row">
            <div class="large-12 columns">
                <h3>Administration</h3>
                <h5>Registered users</h5>
                <div id="users"></div>
            </div>
        </div>

    </jsp:attribute>

    <jsp:attribute name="styles">
        <style type="text/css">
            .loading {
                background-image: url('${pageContext.request.contextPath}/static/img/loading.gif');
                width: 68px;
                height: 68px;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">

            (function($) {

                var $users = $('#users');

                $.ajax({
                    url: '${pageContext.request.contextPath}/api/users',
                    type: 'GET',
                    beforeSend: function() {
                        $users.append('<div class="loading">');
                    },
                    complete: function() {
                        $users.find('.loading').remove();
                    },
                    success: function(users) {
                        var content = '<table class="hover">';
                        content += '<thead><tr><th>Firstname</th><th>Lastname</th><th>Email</th><th>Username</th></tr></thead>';
                        content += '<tbody>';
                        if (users) {
                            users.forEach(function(user) {
                                content += '<tr><td>' + user.firstname + '</td><td>' + user.lastname + '</td><td>' + user.email + '</td><td>' + user.username + '</td></tr>';
                            });
                        }
                        else {
                            content += '<tr><td colspan="4">No user</td></tr>';
                        }
                        content += '</tbody>';
                        content += '</table>';
                        $users.append(content)
                    }
                });
            })(jQuery);

        </script>
    </jsp:attribute>

</t:template>