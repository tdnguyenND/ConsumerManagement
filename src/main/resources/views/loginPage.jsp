
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html >
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Login</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Wellcome </h1>
        </div>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign in</h3>
                </div>
                <div class="panel-body">
                    <spring:url var="testFormURL" value="/login" />
                    <form:form action="${testFormURL}" method="post" class="form form-horizontal"  modelAttribute="account">
                        <c:if test="${error != null}">
                            <p>${error}</p>
                        </c:if>
                        <div class="input-group input-sm">
                            <label for="username" class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </label>

                            <form:input path="username"  type="text" class="form-control" id="username" name="username" placeholder="Enter Username" />

                        </div>
                        <div class="input-group input-sm">
                            <label for="password" class="input-group-addon" >
                                <i class="fa fa-lock"></i>
                            </label>
                            <form:input path="password" type="password" class="form-control" id="password" name ="password" placeholder = "Enter pass" />

                        </div>
                        <div class = "form-actions">
                            <input type="submit" class="btn btn-block btn-primary" value="Login">
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>