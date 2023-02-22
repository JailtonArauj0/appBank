package com.appbank.servlet;

import com.appbank.dao.DaoRegister;
import com.appbank.models.ModelRegister;
import com.appbank.utils.CreateAccNumber;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletSignIn", value = "/ServletSignIn")
public class ServletSignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ModelRegister modelRegister = new ModelRegister();
            CreateAccNumber accNumber = new CreateAccNumber();
            DaoRegister daoRegister = new DaoRegister();

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            modelRegister.setEmail(email);
            modelRegister.setPassword(password);
            modelRegister.setAccountNumber(accNumber.accNumber());
            modelRegister.setAgencyNumber(accNumber.agencyNumber());

            daoRegister.createAccount(modelRegister);
            request.setAttribute("msg", "Conta criada com sucesso!");
            request.getRequestDispatcher("/main/auth-register.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
