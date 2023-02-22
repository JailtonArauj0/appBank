package com.appbank.servlet;

import com.appbank.connection.SingleConnection;
import com.appbank.dao.DaoClient;
import com.appbank.dao.DaoLogin;
import com.appbank.models.ModelClient;
import com.appbank.models.ModelRegister;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    private Connection connection;
    private DaoLogin daoLogin = new DaoLogin();
    private DaoClient daoClient = new DaoClient();

    public ServletLogin() {
        connection = SingleConnection.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("logout")) {
                request.getSession().invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } else {
                doPost(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ModelRegister modelRegister = new ModelRegister();
            ModelClient modelClient = new ModelClient();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String url = request.getParameter("url");
            String msg = "";

            modelRegister.setEmail(email);
            modelRegister.setPassword(password);

            ModelRegister loggedUser = daoLogin.userExists(modelRegister);
            if (email.isEmpty() || password.isEmpty()) {
                msg = "Preencha todos os campos";
                request.setAttribute("msg", msg);
                RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
                redirect.forward(request, response);

            }else if (loggedUser.getEmail() != null && loggedUser.getPassword() != null) {
                modelClient = daoClient.queryUserById(loggedUser.getId());
                request.getSession().setAttribute("loggedUser", loggedUser.getEmail());
                request.getSession().setAttribute("loggedUserId", loggedUser);
                request.getSession().setAttribute("id", loggedUser.getId());
                request.getSession().setAttribute("client", modelClient);


                if (url == null || url.equals("null")) {
                    url = "/main/main.jsp";
                }
                RequestDispatcher redirect = request.getRequestDispatcher(url);
                redirect.forward(request, response);

            } else {
                msg = "Email ou senha incorretos";
                request.setAttribute("msg", msg);
                RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
                redirect.forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
