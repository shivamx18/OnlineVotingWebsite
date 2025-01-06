package com.user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

import com.user.dao.VoterDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/voting_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

     

        Connection conn = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            VoterDAO dao = new VoterDAO();
            String voterId = dao.getVoterIdByUsername(username);

            
            if (authenticateUser(request, conn, username, password, "admin")) {
            	String role = "admin";
                logger.info("Admin login successful for username: " + username);
                
                String candidateCountQuery = "SELECT COUNT(*) AS total_candidates FROM candidate";
                PreparedStatement candidateStatement = conn.prepareStatement(candidateCountQuery);
                ResultSet candidateResultSet = candidateStatement.executeQuery();

                if (candidateResultSet.next()) {
                    int totalCandidates = candidateResultSet.getInt("total_candidates");
                    request.setAttribute("totalCandidates", totalCandidates);
                }

                
                String voterCountQuery = "SELECT COUNT(*) AS total_voters FROM voter";
                PreparedStatement voterStatement = conn.prepareStatement(voterCountQuery);
                ResultSet voterResultSet = voterStatement.executeQuery();

                if (voterResultSet.next()) {
                    int totalVoters = voterResultSet.getInt("total_voters");
                    request.setAttribute("totalVoters", totalVoters);
                }
                
               
                
                createSessionAndRedirect(request, response, username,role, "AdminServlet");
                return;
            }

            
            if (authenticateUser(request, conn, username, password, "voter")) {
            	String role = "voter";
                logger.info("Voter login successful for username: " + username);
                String redirectUrl = "VoterServlet?action=view&voter_id=" + voterId;
                createSessionAndRedirect(request, response, username, role, redirectUrl);
                return;
            }

            request.setAttribute("errorMessage", "Invalid username or password.");
            
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            logger.severe("Error occurred during login: " + e.getMessage());
            request.setAttribute("errorMessage", "An error occurred. Please try again later.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean authenticateUser(HttpServletRequest request, Connection conn, String username, String password, String tableName) throws SQLException {
        String idColumn = "id";  
        if ("admin".equalsIgnoreCase(tableName)) {
            idColumn = "admin_id";  
        } else if ("voter".equalsIgnoreCase(tableName)) {
            idColumn = "voter_id";  
        }

        String sql = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    
                    int userId = result.getInt(idColumn); 
                    HttpSession session = request.getSession();
                    session.setAttribute(tableName + "Id", userId);  
                    session.setAttribute("username", username);    
                    
                    logger.info("User authenticated in table: " + tableName);
                    return true;
                }
            }
        }
        
        logger.warning("Authentication failed for user: " + username + " in table: " + tableName);
        return false;
    }

    private void createSessionAndRedirect(HttpServletRequest request, HttpServletResponse response, String username, String role ,String redirectPage) throws IOException {
        
       

        
        HttpSession session = request.getSession();
        session.setAttribute("username", username);  
        session.setAttribute("userRole", role);

        
        logger.info("Session created for username: " + username);
        
       
        response.sendRedirect(request.getContextPath() + "/" + redirectPage);
    }
}
