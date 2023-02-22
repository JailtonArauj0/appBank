<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="body.jsp"></jsp:include>

        <div id="main-content">

            <div class="page-heading">

                <section id="multiple-column-form">
                    <div class="row match-height">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Informações Pessoais</h4>
                                </div>
                                <div class="card-content">
                                    <div class="card-body">
                                        <form class="form" method="post" action="<%=request.getContextPath()%>/ServletUser?action=formClient&id=${loggedUserId.id}">
                                            <div class="row">
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="first-name-column">Nome</label>
                                                        <input type="text" id="first-name-column" class="form-control"
                                                               placeholder="Nome" name="name" value="${client.firstName}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="full-name-column">Nome Completo</label>
                                                        <input type="text" id="full-name-column" class="form-control"
                                                               placeholder="Nome Completo" name="fullName" value="${client.fullName}">
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="birth-date-column">Data de Nascimento</label>
                                                        <input type="date" id="birth-date-column" class="form-control"
                                                               placeholder="Data de Nascimento" name="birthDate" value="${client.birthDate}">
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="phone-column">Telefone</label>
                                                        <input type="number" id="phone-column" class="form-control"
                                                               placeholder="Telefone" name="phone" value="${client.phone}">
                                                    </div>
                                                </div>

                                                <div class="card-header">
                                                    <h4 class="card-title">Endereço</h4>
                                                </div>

                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="street-column">Rua</label>
                                                        <input type="text" id="street-column" class="form-control"
                                                               placeholder="Rua" name="street" value="${address.street}">
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="number-floating">Número</label>
                                                        <input type="number" id="number-floating" class="form-control"
                                                               name="number" placeholder="Número" value="${address.number}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="district-column">Bairro</label>
                                                        <input type="text" id="district-column" class="form-control"
                                                               name="district" placeholder="Bairro" value="${address.district}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="cep-column">CEP</label>
                                                        <input type="text" id="cep-column" class="form-control"
                                                               name="cep" placeholder="CEP" value="${address.cep}">
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="city-column">Cidade</label>
                                                        <input type="text" id="city-column" class="form-control"
                                                               name="city" placeholder="Cidade" value="${address.city}">
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-12">
                                                    <div class="form-group">
                                                        <label for="uf-column">Estado</label>
                                                        <input type="text" id="uf-column" class="form-control"
                                                               name="state" placeholder="Estado" value="${address.uf}">
                                                    </div>
                                                </div>

                                                <span style="color: green">${msg}</span>

                                                <div style="margin-top: 15px" class="col-12 d-flex justify-content-end">
                                                    <button type="submit"
                                                            class="btn btn-primary me-1 mb-1">Atualizar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

        </div>


<jsp:include page="jsimports.jsp"></jsp:include>
</body>
</html>
