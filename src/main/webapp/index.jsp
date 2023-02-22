<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - AppBank</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/main/assets/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/main/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/main/assets/css/app.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/main/assets/css/pages/auth.css">
</head>

<body>

<div id="auth">

    <div class="row h-100">
        <div class="col-lg-5 col-12">
            <div id="auth-left">
                <div class="auth-logo">
                    <a href="index.jsp"><img src="<%=request.getContextPath()%>/main/assets/images/logo/logo.png" alt="Logo"></a>
                </div>
                <h1 class="auth-title">Entrar</h1>
                <p class="auth-subtitle mb-5">Entre com os dados utilizados durante a criação da conta.</p>

                <form action="<%=request.getContextPath()%>/ServletLogin" method="post">
<%--                    <input type="hidden" value="<%= request.getParameter("url") %>" name="url">--%>
                    <div class="form-group position-relative has-icon-left mb-4">
                        <input type="email" class="form-control form-control-xl" placeholder="Email" name="email" id="email" required>
                        <div class="form-control-icon">
                            <i class="bi bi-person"></i>
                        </div>
                    </div>
                    <div class="form-group position-relative has-icon-left mb-4">
                        <input type="password" class="form-control form-control-xl" placeholder="Senha" name="password" id="password" required>
                        <div class="form-control-icon">
                            <i class="bi bi-shield-lock"></i>
                        </div>
                    </div>

                    <span>${msg}</span>

                    <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5">Entrar</button>
                </form>
                <div class="text-center mt-5 text-lg fs-4">
                    <p class="text-gray-600">Não possui conta? <a href="<%=request.getContextPath()%>/main/auth-register.jsp"
                                                                  class="font-bold">Registrar-se</a>.</p>
                </div>
            </div>
        </div>
        <div class="col-lg-7 d-none d-lg-block">
            <div id="auth-right">

            </div>
        </div>
    </div>

</div>

</body>
</html>
