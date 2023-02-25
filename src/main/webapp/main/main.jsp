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
            <div class="card" style="margin-top: 15px; width: 15%">
                <div class="card-header">
                    <h4 class="card-title">Saldo atual:</h4>
                </div>
                <div class="card-body">
                    <h4 class="card-title">R$ ${balance.balance}</h4>
                </div>
            </div>
        </section>
    </div>
</div>
</div>
</div>



<jsp:include page="jsimports.jsp"></jsp:include>
</body>

</html>