<%@include file="templates/head.jsp"%>

<html>

    <title>Login</title>

<body>

    <%@include file="templates/header.jsp"%>
    <%@include file="templates/navmenu.jsp"%>

    <main>
        <FORM ACTION="j_security_check" METHOD="POST">
            <TABLE>
                <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">
                <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
                <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
            </TABLE>
        </FORM>
    </main>

</body>
</html>
