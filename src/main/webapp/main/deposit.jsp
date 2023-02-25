<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="body.jsp"></jsp:include>

<div id="main-content">

    <div class="page-heading">
        <div class="page-title">
            <div class="row">
                <div class="col-12 col-md-6 order-md-1 order-last">
                    <h3>Área de Depósito</h3>
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
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#inlineForm">
                    Depositar
                </button>


                <div class="modal fade text-left" id="inlineForm" tabindex="-1"
                     role="dialog" aria-labelledby="myModalLabel33" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable"
                         role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="myModalLabel33">Depositar</h4>
                                <button type="button" class="close" data-bs-dismiss="modal"
                                        aria-label="Close">
                                    <i data-feather="x"></i>
                                </button>
                            </div>
                            <form action="<%=request.getContextPath()%>/ServletUser?action=deposit&id=${loggedUserId.id}" method="post">
                                <div class="modal-body">
                                    <label>Valor que deseja depositar: </label>
                                    <div class="form-group">
                                        <input type="text" placeholder="Valor"
                                               class="form-control" id="value" name="depositValue">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-light-secondary"
                                            data-bs-dismiss="modal">
                                        <i class="bx bx-x d-block d-sm-none"></i>
                                        <span class="d-none d-sm-block">Cancelar</span>
                                    </button>
                                    <button type="submit" class="btn btn-success ml-1"
                                    >
                                        <i class="bx bx-check d-block d-sm-none"></i>
                                        <span class="d-none d-sm-block">Depositar</span>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
</div>
</div>


<script>

    $(document).ready(function() {
        $('#value').maskMoney({
            prefix: 'R$ ',
            decimal: ',',
            thousands: '.'
        });
    });


</script>

<jsp:include page="jsimports.jsp"></jsp:include>
</body>
</html>
