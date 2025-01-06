package com.user.controller;

import com.user.dao.CandidateDAO;
import com.user.dao.VoteDAO;
import com.user.dao.VoterDAO;
import com.user.model.Admin;
import com.user.model.Candidate;
import com.user.model.Voter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    private CandidateDAO candidateDAO;
    private VoterDAO voterDAO;
    private VoteDAO voteDAO;

    @Override
    public void init() throws ServletException {
        // Initialize DAO objects
        candidateDAO = new CandidateDAO();
        voterDAO = new VoterDAO();
        voteDAO = new VoteDAO();
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                showLoginPage(request, response);
                break;
            case "logout":
                logoutAdmin(request, response);
                break;
            case "dashboard":
                showDashboard(request, response);
                break;
            case "manage_candidates":
                manageCandidates(request, response);
                break;
            case "manage_voters":
                manageVoters(request, response);
                break;
            case "view_results":
                viewResults(request, response);
                break;
            default:
                adminPage(request,response);
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
            case "login":
                loginAdmin(request, response);
                break;
            case "add_candidate":
                addCandidate(request, response);
                break;
            case "add_voter":
                addVoter(request, response);
                break;
            default:
                adminPage(request,response);
                break;
        }
    
    }

    
    private void adminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int totalCandidates = candidateDAO.countTotalCandidates();
    	int totalVoters = voterDAO.countTotalVoters();
        int totalVotes = voteDAO.countTotalVotes();
        
        request.setAttribute("totalCandidates", totalCandidates);
        request.setAttribute("totalVoters", totalVoters);
        request.setAttribute("totalVotes", totalVotes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    
    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        
        if ("admin".equals(username) && "password".equals(password)) {
            
            Admin admin = new Admin(username );
            request.getSession().setAttribute("admin", admin);

            
            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    
    private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }

   
    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        
        int totalCandidates = candidateDAO.selectAllCandidate().size();
        int totalVoters = voterDAO.selectAllVoter().size();
        int totalVotes = totalVoters; 

        
        request.setAttribute("totalCandidates", totalCandidates);
        request.setAttribute("totalVoters", totalVoters);
        request.setAttribute("totalVotes", totalVotes);

       
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }

   
    private void manageCandidates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        
        List<Candidate> candidates = candidateDAO.selectAllCandidate();
        request.setAttribute("candidates", candidates);

      
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-candidates.jsp");
        dispatcher.forward(request, response);
    }

    
    private void manageVoters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

      
        List<Voter> voters = voterDAO.selectAllVoter();
        request.setAttribute("voters", voters);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-voters.jsp");
        dispatcher.forward(request, response);
    }

  
    private void viewResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        
        request.setAttribute("results", "Voting results will be displayed here.");

       
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-results.jsp");
        dispatcher.forward(request, response);
    }

    
    private void addCandidate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String party = request.getParameter("party");

        Candidate newCandidate = new Candidate(firstName, lastName, party);
        candidateDAO.insertCandidate(newCandidate);

       
        response.sendRedirect("CandidateServlet?action=manage_candidates");
    }

    private void addVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");

        
        Voter newVoter = new Voter(username,password,firstName, lastName, email);
        voterDAO.insertVoter(newVoter);

        
        response.sendRedirect("VoterServlet?action=manage_voters");
    }

}
