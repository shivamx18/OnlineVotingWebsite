package com.user.controller;

import com.user.dao.CandidateDAO;
import com.user.dao.VoteDAO;
import com.user.dao.VoterDAO;
import com.user.model.Candidate;
import com.user.model.Vote;
import com.user.model.Voter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

    private CandidateDAO candidateDAO;
    private VoteDAO voteDAO;

    @Override
    public void init() throws ServletException {
        candidateDAO = new CandidateDAO();
        voteDAO = new VoteDAO() ;
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userRole") == null) {
            System.out.println("Session is null or userRole not found.");
            response.sendRedirect("login.jsp");
            return;
        }

        String role = (String) session.getAttribute("userRole");
        System.out.println("User role: " + role);
      
        CandidateDAO dao = new CandidateDAO();
        try (Connection connection = dao.getConnection()) {
        	 List<Candidate> candidates = candidateDAO.selectAllCandidate();
           
        	 request.setAttribute("candidates", candidates);
        	 
        	 List<Vote> total_vote = voteDAO.countVote();
        	 request.setAttribute("total_vote", total_vote);
        	 
        	 
           
            if ("admin".equalsIgnoreCase(role)) {
                System.out.println("Forwarding to admin page.");
               
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-view-result.jsp");
                dispatcher.forward(request, response);

            } else if ("voter".equalsIgnoreCase(role)) {
                System.out.println("Forwarding to voter page.");
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("voter-view-result.jsp");
                dispatcher.forward(request, response);

            } else {
                System.out.println("Unauthorized role: " + role);
               
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
}
