package com.appbank.servlet;

import com.appbank.dao.DaoAddress;
import com.appbank.dao.DaoBankOperation;
import com.appbank.dao.DaoClient;
import com.appbank.dao.DaoRegister;
import com.appbank.models.ModelAddress;
import com.appbank.models.ModelClient;
import com.appbank.models.ModelRegister;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "ServletUser", value = "/ServletUser")
public class ServletUser extends HttpServlet {
    DaoClient daoClient = new DaoClient();
    DaoAddress daoAddress = new DaoAddress();
    DaoRegister daoRegister = new DaoRegister();
    DaoBankOperation daoBankOperation = new DaoBankOperation();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            Long id = (Long) request.getSession().getAttribute("id");

            if (action.equalsIgnoreCase("formUser")) {
                ModelClient modelClient = daoClient.queryUserById(id);
                ModelAddress modelAddress = daoAddress.queryUserById(id);

                request.setAttribute("client", modelClient);
                request.setAttribute("address", modelAddress);

                request.getRequestDispatcher("/main/formClient.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("balance")) {
                String page = request.getParameter("page");
                ModelRegister modelRegister = daoBankOperation.balanceById(id);
                request.getSession().setAttribute("balance", modelRegister);

                if (page.equalsIgnoreCase("main")) {
                    request.getRequestDispatcher("/main/main.jsp").forward(request, response);

                } else if (page.equalsIgnoreCase("withdraw")) {
                    request.getRequestDispatcher("/main/withdraw.jsp").forward(request, response);

                }else if (page.equalsIgnoreCase("transfer")) {
                    request.getRequestDispatcher("/main/transfer.jsp").forward(request, response);

                }else if (page.equalsIgnoreCase("deposit")) {
                    request.getRequestDispatcher("/main/deposit.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {

            if (action.equalsIgnoreCase("formClient")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String fullName = request.getParameter("fullName");
                String birthDate = request.getParameter("birthDate");
                String phone = request.getParameter("phone");
                String street = request.getParameter("street");
                String number = request.getParameter("number");
                String district = request.getParameter("district");
                String cep = request.getParameter("cep");
                String city = request.getParameter("city");
                String uf = request.getParameter("state");

                ModelClient modelClient = new ModelClient();
                modelClient.setId_register(Long.valueOf(id));
                modelClient.setFirstName(name);
                modelClient.setFullName(fullName);
                modelClient.setBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate).getTime()));
                modelClient.setPhone(phone);

                ModelAddress modelAddress = new ModelAddress();
                modelAddress.setId_client(Long.valueOf(id));
                modelAddress.setStreet(street);
                modelAddress.setNumber(number);
                modelAddress.setDistrict(district);
                modelAddress.setCep(cep);
                modelAddress.setCity(city);
                modelAddress.setUf(uf);

                if (daoAddress.userExistsById(Long.valueOf(id)) && daoClient.userExistsById(Long.valueOf(id))) {
                    daoAddress.update(modelAddress);
                    daoClient.update(modelClient);
                    request.setAttribute("msg", "Dados Atualizados com Sucesso!");

                } else {
                    daoClient.insert(modelClient);
                    daoAddress.insert(modelAddress);
                    request.setAttribute("msg", "Dados Registrados com Sucesso!");
                }
                request.setAttribute("client", modelClient);
                request.setAttribute("address", modelAddress);
                request.getRequestDispatcher("/main/formClient.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("changePassword")) {
                String id = request.getParameter("id");
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");

                request.setAttribute("msg", "As senhas não correspondem");
                if (daoRegister.changePassword(oldPassword, newPassword, Long.valueOf(id)) > 0) {
                    request.setAttribute("msg", "Senha alterada com sucesso");
                }

                request.getRequestDispatcher("/main/infoAcc.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("withdraw")) {
                String id = request.getParameter("id");
                String withdrawValue = request.getParameter("withdrawValue");
                withdrawValue = withdrawValue.replace("R$ ", "").replace(".", "").replace(",", ".");

                String msg = "Você não possui valor suficiente.";
                Boolean test = daoBankOperation.withdraw(Double.parseDouble(withdrawValue), Integer.parseInt(id));
                if (test) {
                    msg = "Saque realizado com sucesso!";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/main/withdraw.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("deposit")) {
                String id = request.getParameter("id");
                String depositValue = request.getParameter("depositValue");
                depositValue = depositValue.replace("R$ ", "").replace(".", "").replace(",", ".");

                daoBankOperation.deposit(Double.parseDouble(depositValue), Integer.parseInt(id));
                request.setAttribute("msg", "Depósito realizado com sucesso!");
                request.getRequestDispatcher("/main/deposit.jsp").forward(request, response);

            } else if (action.equalsIgnoreCase("transfer")) {
                String id = request.getParameter("id");
                String account = request.getParameter("account");
                String agency = request.getParameter("agency");
                String transferValue = request.getParameter("transferValue");
                transferValue = transferValue.replace("R$ ", "").replace(".", "").replace(",", ".");

                String msg = "Você não possui valor suficiente ou conta não encontrada.";

                Boolean testOne = daoBankOperation.transfer(account, agency, Double.parseDouble(transferValue), Integer.parseInt(id));

                if (testOne) {
                    msg = "Transferência realizada com sucesso!";
                }

                request.setAttribute("msg", msg);
                request.getRequestDispatcher("/main/transfer.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
