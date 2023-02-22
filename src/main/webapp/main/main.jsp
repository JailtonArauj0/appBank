<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
</head>

<body>
<jsp:include page="body.jsp"/>


<div id="main-content">

    <div class="page-heading">
        <div class="page-title">
            <div class="row">
                <div class="col-12 col-md-6 order-md-1 order-last">
                    <h3>Olá ${client.firstName},</h3>
                </div>

            </div>
        </div>
        <section class="section">

            <div style="font-size: large; padding-top: 2%">
                <p>Saldo atual:</p>
                <p style="margin-top: 1%" class="auth-subtitle mb-5">R$ 1000,00</p>
                <i class="fa-solid fa-house"></i>
            </div>

        </section>
    </div>
</div>
</div>
</div>



<jsp:include page="jsimports.jsp"></jsp:include>
</body>

</html>