<%--
  Created by IntelliJ IDEA.
  User: jailt
  Date: 22/02/2023
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="body.jsp"/>

<div id="main-content">

    <div class="page-heading">
    <div class="col-md-6 col-12" style="margin-left: 10px">
        <div class="card">
            <div class="card-header">
                <h4 class="card-title">Informações da Conta</h4>
            </div>
            <div class="card-content">
                <div class="card-body">
                    <form class="form form-horizontal">
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Nº da Conta</label>
                                </div>
                                <div class="col-md-8 form-group">
                                    <input type="text" id="first-name" class="form-control"
                                           name="fname" value="${loggedUserId.accountNumber}" readonly>
                                </div>
                                <div class="col-md-4">
                                    <label>Nº da Agência</label>
                                </div>
                                <div class="col-md-8 form-group">
                                    <input type="text" id="text-id" class="form-control"
                                           name="text-id" value="${loggedUserId.agencyNumber}" readonly>
                                </div>
                                <div class="col-md-4">
                                    <label>Email</label>
                                </div>
                                <div class="col-md-8 form-group">
                                    <input type="email" id="contact-info" class="form-control"
                                           name="email" value="${loggedUserId.email}" readonly>
                                </div>

                                <div class="col-sm-12 d-flex justify-content-end" style="margin-top: 3%">
                                    <span style="margin-right: 52%; margin-top: 2%; color: green">${msg}</span>
                                    <button type="button"
                                            class="btn btn-primary me-1 mb-1" data-bs-toggle="modal"
                                            data-bs-target="#inlineForm">Alterar senha</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</div>

<div class="modal fade text-left" id="inlineForm" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel33" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable"
         role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel33">Alteração de Senha</h4>
                <button type="button" class="close" data-bs-dismiss="modal"
                        aria-label="Close">
                    <i data-feather="x"></i>
                </button>
            </div>
            <form action="<%=request.getContextPath()%>/ServletUser?action=changePassword&id=${id}" method="post">
                <div class="modal-body">
                    <label>Senha antiga: </label>
                    <div class="form-group">
                        <input type="password"
                               class="form-control" name="oldPassword">
                    </div>
                    <label>Nova Senha: </label>
                    <div class="form-group">
                        <input type="password"
                               class="form-control" name="newPassword">
                    </div>
                </div>

                <div class="modal-footer">

                    <button type="button" class="btn btn-light-secondary"
                            data-bs-dismiss="modal">
                        <i class="bx bx-x d-block d-sm-none"></i>
                        <span class="d-none d-sm-block">Cancelar</span>
                    </button>
                    <button type="submit" class="btn btn-success ml-1"
                            >Alterar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>





<jsp:include page="jsimports.jsp"></jsp:include>
</body>
</html>
