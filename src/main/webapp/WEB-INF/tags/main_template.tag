<%@tag description="Main template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="content" fragment="true" %>

<!doctype html>
<html class="no-js" lang="en">
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/foundation.min.css" />
    <title>${title}</title>
</head>
<body>
    <header>
        <div class="top-bar">
            <div class="top-bar-left">
                <ul class="menu">
                    <li class="menu-text">Les cours'get</li>
                </ul>
            </div>
            <div class="top-bar-right">
                <ul class="menu">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Login</a></li>
                    <li><a href="#">Admin</a></li>
                </ul>
            </div>
        </div>
    </header>
    <br>
    <jsp:invoke fragment="content"/>
</body>
</html>