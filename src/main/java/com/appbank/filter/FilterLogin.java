package com.appbank.filter;

import com.appbank.connection.SingleConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/main/main.jsp", "/main/formClient.jsp", "/main/body.jsp"})
public class FilterLogin implements Filter {
    private Connection connection;

    public FilterLogin() {
    }

    public void init(FilterConfig config) throws ServletException {
        connection = SingleConnection.getConnection();
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();

            String loggedUser = (String) session.getAttribute("loggedUser");
            String urlToAuth = req.getServletPath();

            if (loggedUser == null && !urlToAuth.equalsIgnoreCase("/main/ServletLogin")) {
                RequestDispatcher redirect = req.getRequestDispatcher("/index.jsp?url=" + urlToAuth);
                req.setAttribute("msg", "Por favor, realize o login");
                redirect.forward(request, response);
                return;
            } else {
                chain.doFilter(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
