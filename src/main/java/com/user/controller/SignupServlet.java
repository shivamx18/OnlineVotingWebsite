package com.user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;


import java.io.IOException;
import java.sql.*;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SignupServlet.class.getName());

    private static final String DB_URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    public SignupServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	 request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
	        

        Connection connection = null;

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

           
            if (isUsernameOrEmailExists(connection, username, email)) {
                
                request.setAttribute("errorMessage", "Username or email already exists.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return; 
            }
            
           
          
            String sql = "INSERT INTO voter ( username , password, first_name, last_name,email) VALUES (?, ?, ?, ?, ?)";
           


            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, firstName);
                statement.setString(4, lastName);
                statement.setString(5, email);
                int rowsAffected = statement.executeUpdate();
                logger.info("Rows affected: " + rowsAffected); 
                
                if (rowsAffected > 0) {
                    logger.info("User signed up successfully ");
                    
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    
                    logger.info("Session set: username=" + session.getAttribute("username"));
                    
                    response.sendRedirect("login.jsp?signup=success");
                } else {
                	 logger.warning("Error: Unable to signup user.");
                    response.getWriter().println("Error: Unable to signup user.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred during signup.");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        
       
    
    
    }
    private boolean isUsernameOrEmailExists(Connection connection, String username, String email) throws SQLException {
        String sql =  "SELECT COUNT(*) FROM voter WHERE username = ? OR email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, email);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; 
                }
            }
        }
        return false;
    }
    

}
