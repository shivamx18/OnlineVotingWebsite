package com.user.controller;

import com.user.dao.CandidateDAO;
import com.user.dao.VoterDAO;
import com.user.model.Candidate;
import com.user.model.Voter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CandidateServlet")
public class CandidateServlet extends HttpServlet {

    private CandidateDAO candidateDAO;

    @Override
    public void init() throws ServletException {
        candidateDAO = new CandidateDAO(); 
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "delete":
                deleteCandidate(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "view":
                viewCandidate(request, response); 
                break;
            default:
                listCandidates(request, response); 
                break;
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                addCandidate(request, response);
                break;
            case "update":
                updateCandidate(request, response);
                break;
            default:
                listCandidates(request, response); 
                break;
        }
    }

    
    private void viewCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	int candidateId = Integer.parseInt(request.getParameter("candidate_id"));
            CandidateDAO dao = new CandidateDAO();
           
            try(Connection connection=dao.getConnection())
    		{

            	Candidate candidate= dao.selectCandidate(candidateId);
                request.setAttribute("candidate", candidate);
                RequestDispatcher dispatcher = request.getRequestDispatcher("view-candidate.jsp");
                dispatcher.forward(request, response);
            
            } catch (SQLException e) {
				
				e.printStackTrace();
			}
         
    }

    private void listCandidates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Candidate> candidates = candidateDAO.selectAllCandidate();
        request.setAttribute("candidates", candidates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-candidates.jsp");
        dispatcher.forward(request, response);
    }

    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-candidate.jsp");
        dispatcher.forward(request, response);
    }

    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int candidateId = Integer.parseInt(request.getParameter("candidate_id"));
        Candidate existingCandidate = candidateDAO.selectCandidate(candidateId);
        request.setAttribute("candidate", existingCandidate);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-candidate.jsp");
        dispatcher.forward(request, response);
    }

   
    private void addCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        

        Candidate existingCandidate = candidateDAO.selectCandidate(email);
        if (existingCandidate != null) {
            
            request.setAttribute("error", "A candidate with this email already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-candidate.jsp");
            dispatcher.forward(request, response);
        } else {
            Candidate newCandidate = new Candidate(firstName, lastName, email);
            candidateDAO.insertCandidate(newCandidate);
            response.sendRedirect("CandidateServlet");
        }
        }

    
    private void updateCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean status=false;
    	int candidateId = Integer.parseInt(request.getParameter("candidateId"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");

        Candidate existingCandidateWithEmail = candidateDAO.selectCandidate(email);
        if (existingCandidateWithEmail != null && existingCandidateWithEmail.getCandidateId() != candidateId) {
            
            request.setAttribute("error", "A candidate with this email already exists.");
            CandidateDAO dao = new CandidateDAO();
            Candidate existingCandidate = dao.selectCandidate(candidateId);
            request.setAttribute("candidate", existingCandidate);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-candidate.jsp");
            dispatcher.forward(request, response);
        } else {
            
            CandidateDAO dao = new CandidateDAO();
            try (Connection connection = dao.getConnection()) {
                Candidate candidate = dao.selectCandidate(candidateId);
                candidate.setFirstname(firstName);
                candidate.setLastname(lastName);
                candidate.setEmail(email);
                
                 status = dao.updateCandidate(candidate);
                response.sendRedirect("CandidateServlet?action=view&candidate_id=" +candidateId);
            } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	            e.printStackTrace();
	            request.setAttribute("error", "An error occurred: " + e.getMessage());
            
            	}
        }

}

    
    private void deleteCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("candidate_id"));
        CandidateDAO dao = new CandidateDAO();
        
        try(Connection connection=dao.getConnection())
		{
			dao.deleteCandidate(id);
			response.sendRedirect("CandidateServlet");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    
    }
}
