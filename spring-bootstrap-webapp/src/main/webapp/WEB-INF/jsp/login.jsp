<%@include file="/WEB-INF/jsp/prefix.jsp" %>

<div class="hero-unit">

    <h2>Login to Spring Bootstrap</h2>

    <c:if test="${not empty param.error}">
        Login error.
        Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </c:if>


    <div class="form">
        <form method="post" action="j_spring_security_check">

            <div class="row">
                <label for="j_username">Username:</label>
                <input type="text" name="j_username"/>
            </div>

            <div class="row">
                <label for="j_password">Password:</label>
                <input type="password" name="j_password"/>
            </div>


            <div class="row">
                <input class="btn primary small" type="submit" value="Log in"/>
            </div>

            <div class="row">
                Remember Me:
                <input type="checkbox" name="_spring_security_remember_me"/>
            </div>


        </form>


        <a href="{% url auth_password_reset %}"><p>Reset
            password </p></a>

        <p>Don't have an account yet? <a href="/accounts/register">Register now</a>.</p>


    </div>
</div>

<%@include file="/WEB-INF/jsp/suffix.jsp" %>
