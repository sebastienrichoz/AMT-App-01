<%@tag description="Main template" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title"%>
<%@attribute name="body" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!-- Gentellela Alela! Free theme : https://github.com/puikinsh/gentelella -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${title}</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/static/css/custom.min.css" rel="stylesheet">

    <style>
    .nav.child_menu {
        display: block;
    }
    </style>

    <jsp:invoke fragment="header"/>
</head>

<body class="nav-md">
    <div class="container body">
        <div class="main_container">
            <div class="col-md-3 left_col">
                <div class="left_col scroll-view">
                    <div class="navbar nav_title" style="border: 0;">
                        <a href="${pageContext.request.contextPath}/" class="site_title"><i class="fa fa-rebel"></i> <span>AMTboard</span></a>
                    </div>

                    <div class="clearfix"></div>
                    <br>

                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                        <div class="menu_section">
                            <ul class="nav child_menu">
                                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                                <c:if test="${requestScope.isAuthenticated == false}">
                                        <li><a href="${pageContext.request.contextPath}/register">Register</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/users">Users</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- /sidebar menu -->
                </div>
            </div>

            <!-- top navigation -->
            <div class="top_nav">
                <div class="nav_menu">
                    <nav>
                        <ul class="nav navbar-nav navbar-right">
                            <c:choose>
                                <c:when test="${requestScope.isAuthenticated == true}">
                                    <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out"></i> Logout</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/login"><i class="fa fa-sign-out"></i> Login</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- /top navigation -->

            <!-- page content -->
            <div class="right_col" role="main">

                <!-- top tiles -->
                <div class="page-title">
                    <div class="title_left">
                        <h3>${title}</h3>
                    </div>
                </div>
                <!-- /top tiles -->

                <div class="clearfix"></div>
                <br>

                <jsp:invoke fragment="body"/>

            </div>
            <!-- /page content -->

            <!-- footer content -->
            <footer>
                <div class="pull-right" style="text-align: right;">
                    HEIG-VD - Damien Rochat & Sébastien Richoz - <a href="https://github.com/sebastienrichoz/AMT-App-01" target="_blank">Github repository</a><br />
                    <span style="font-size:0.8em;">Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a></span>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->
        </div>
    </div>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/static/js/custom.min.js"></script>

    <jsp:invoke fragment="footer"/>

</body>
</html>
