package com.cravekart.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.cravekart.model.User;
import com.cravekart.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        try {

            User user = new User();

            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordHash(password);
            user.setAddress(address);
            user.setRole("CUSTOMER");

            int userId = userService.registerUser(user);

            response.setContentType("text/html");
            response.getWriter().println(
                "Registration Successful! User ID: " + userId
            );

        } catch (IllegalArgumentException e) {

            response.setContentType("text/html");
            response.getWriter().println(
                "Registration Failed: " + e.getMessage()
            );

        } catch (SQLException e) {

            throw new ServletException(
                "Database error during registration", e
            );
        }
    }
}