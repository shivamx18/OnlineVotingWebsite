package com.user.controller;

import com.user.dao.CandidateDAO;
import com.user.dao.VoteDAO;
import com.user.model.Candidate;
import com.user.model.Vote;
import com.user.model.Voter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {

    private VoteDAO voteDAO;
    private CandidateDAO candidateDAO;

    @Override
    public void init() throws ServletException {
        voteDAO = new VoteDAO();
        candidateDAO = new CandidateDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action"); 
        if (action == null) {
            action = ""; 
        }
        switch (action) {
            case "vote":
                handleVote(request, response);
                break;
            
            default:
                handleVotePage(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); 
        if (action == null) {
            action = ""; 
        }
        switch (action) {
            case "vote":
                handleVote(request, response);
                break;
           
            default:
                handleVotePage(request, response);
                break;
        }
    }

   
    private void handleVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userRole") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int voterId = (Integer) session.getAttribute("voterId");
        int candidateId = Integer.parseInt(request.getParameter("candidate_id"));
        
        
        Vote existingVote = voteDAO.selectVote(voterId);
        if (existingVote.getVoterId() != 0) {
           
            request.setAttribute("message", "You have already voted  ");
            System.out.println("Already Voted");
            RequestDispatcher dispatcher = request.getRequestDispatcher("VoteServlet?action=default");
            dispatcher.forward(request, response);
            return;
        }

        Vote vote = new Vote();
        vote.setVoterId(voterId);
        vote.setCandidateId(candidateId);

        voteDAO.insertVote(vote);

        
       
        request.setAttribute("message", "Vote casted to Candidated ID : " + candidateId);
        System.out.println("succesfully Voted to " + candidateId );

      
        RequestDispatcher dispatcher = request.getRequestDispatcher("VoteServlet?action=default");
        dispatcher.forward(request, response);
    }

   
  

    
  
    
    private void handleVotePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         
         CandidateDAO dao = new CandidateDAO();
         List<Candidate> candidateList = dao.selectAllCandidate();
         if (candidateList.isEmpty()) {
             System.out.println("No candidates found.");
         }
         request.setAttribute("candidate", candidateList); 
         RequestDispatcher dispatcher = request.getRequestDispatcher("vote.jsp");
         dispatcher.forward(request, response);
    }

}
