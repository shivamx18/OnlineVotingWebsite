package com.user.controller;

import com.user.dao.VoterDAO;
import com.user.model.Voter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/VoterServlet")
public class VoterServlet extends HttpServlet {

    private VoterDAO voterDAO;

    @Override
    public void init() throws ServletException {
        voterDAO = new VoterDAO();
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
                deleteVoter(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "view":
                viewVoter(request, response); 
                break;
            default:
                listVoters(request, response);
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
                addVoter(request, response);
                break;
            case "update":
                updateVoter(request, response);
                break;
            default:
                listVoters(request, response);
                break;
        }
    }
    
    private void viewVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        
        if (session == null || session.getAttribute("userRole") == null) {
            System.out.println("Session is null or userRole not found.");
            response.sendRedirect("login.jsp");
            return;
        }

        
        String role = (String) session.getAttribute("userRole");
        System.out.println("User role: " + role);

        
        int voterId;
        try {
            voterId = Integer.parseInt(request.getParameter("voter_id"));
        } catch (NumberFormatException e) {
            System.out.println("Invalid voter ID: " + request.getParameter("voter_id"));
            response.sendRedirect("error.jsp"); 
            return;
        }

       
        VoterDAO dao = new VoterDAO();
        try (Connection connection = dao.getConnection()) {
            Voter voter = dao.selectVoter(voterId);
            if (voter == null) {
                System.out.println("Voter not found for ID: " + voterId);
                
                return;
            }

            
            request.setAttribute("voter", voter);

           
            if ("admin".equalsIgnoreCase(role)) {
                System.out.println("Forwarding to admin page.");
                request.setAttribute("message", "Welcome, Admin! You can view and manage voters.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin-view-voter.jsp");
                dispatcher.forward(request, response);

            } else if ("voter".equalsIgnoreCase(role)) {
                System.out.println("Forwarding to voter page.");
                request.setAttribute("message", "Welcome, Voter! You can view your details.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("voter.jsp");
                dispatcher.forward(request, response);

            } else {
                System.out.println("Unauthorized role: " + role);
               
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }


    
    
    private void listVoters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Voter> voters = voterDAO.selectAllVoter();
        request.setAttribute("voters", voters);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage-voters.jsp");
        dispatcher.forward(request, response);
    }

   
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-voter.jsp");
        dispatcher.forward(request, response);
    }

  
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                          
    	  HttpSession session = request.getSession(false);

          
          if (session == null || session.getAttribute("userRole") == null) {
              System.out.println("Session is null or userRole not found.");
              response.sendRedirect("login.jsp");
              return;
          }

          
          String role = (String) session.getAttribute("userRole");
          System.out.println("User role: " + role);

          
          int voterId;
          try {
              voterId = Integer.parseInt(request.getParameter("voter_id"));
          } catch (NumberFormatException e) {
              System.out.println("Invalid voter ID: " + request.getParameter("voter_id"));
              response.sendRedirect("error.jsp"); 
              return;
          }

         
          VoterDAO dao = new VoterDAO();
          try (Connection connection = dao.getConnection()) {
              Voter voter = dao.selectVoter(voterId);
              if (voter == null) {
                  System.out.println("Voter not found for ID: " + voterId);
                  
                  return;
              }

              
              request.setAttribute("voter", voter);

             
              if ("admin".equalsIgnoreCase(role)) {
                  System.out.println("Forwarding to admin page.");
                  request.setAttribute("message", "Welcome, Admin! You can view and manage voters.");
                  RequestDispatcher dispatcher = request.getRequestDispatcher("admin-edit-voter.jsp");
                  dispatcher.forward(request, response);

              } else if ("voter".equalsIgnoreCase(role)) {
                  System.out.println("Forwarding to voter page.");
                  request.setAttribute("message", "Welcome, Voter! You can view your details.");
                  RequestDispatcher dispatcher = request.getRequestDispatcher("voter-edit-voter.jsp");
                  dispatcher.forward(request, response);

              } else {
                  System.out.println("Unauthorized role: " + role);
                 
              }

          } catch (SQLException e) {
              e.printStackTrace();
              
          }
            
        
    }
   
    private void addVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String username = request.getParameter("username");
         String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");

        Voter existingVoterByUsername = voterDAO.selectVoterByUsername(username);
        Voter existingVoterByEmail = voterDAO.selectVoterByEmail(email);

        if (existingVoterByUsername != null) {
           
            request.setAttribute("error", "A voter with this username already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-voter.jsp");
            dispatcher.forward(request, response);
        } else if (existingVoterByEmail != null) {
            
            request.setAttribute("error", "A voter with this email already exists.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-voter.jsp");
            dispatcher.forward(request, response);
        } else {
            
            Voter newVoter = new Voter(username, password,email, firstName, lastName);
            voterDAO.insertVoter(newVoter);
            response.sendRedirect("VoterServlet");
        }
    }

    
    private void updateVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean status=false;
    	int voterId = Integer.parseInt(request.getParameter("voterId"));
        
              
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");

       
        
        Voter existingVoterByUsername = voterDAO.selectVoterByUsername(username);
        Voter existingVoterByEmail = voterDAO.selectVoterByEmail(email);

        if (existingVoterByUsername != null && existingVoterByUsername.getVoterId() != voterId) {
            
            request.setAttribute("error1", "A voter with this username already exists.");
            Voter existingVoter = voterDAO.selectVoter(voterId);
            request.setAttribute("voter", existingVoter);
            RequestDispatcher dispatcher = request.getRequestDispatcher("voter-edit-voter.jsp");
            dispatcher.forward(request, response);
        } else if (existingVoterByEmail != null && existingVoterByEmail.getVoterId() != voterId) {
            
            request.setAttribute("error2", "A voter with this email already exists.");
            Voter existingVoter = voterDAO.selectVoter(voterId);
            request.setAttribute("voter", existingVoter);
            RequestDispatcher dispatcher = request.getRequestDispatcher("voter-edit-voter.jsp");
            dispatcher.forward(request, response);
        } else {
          
            Voter updatedVoter = new Voter(voterId, username, password, firstName, lastName ,email);
            voterDAO.updateVoter(updatedVoter);
            
            response.sendRedirect("VoterServlet?action=view&voter_id="+ voterId);
        }
       
    }


    private void deleteVoter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id=Integer.parseInt(request.getParameter("voter_id"));
		VoterDAO dao=new VoterDAO();
		try(Connection connection=dao.getConnection())
		{
			dao.deleteVoter(id);
			response.sendRedirect("VoterServlet");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    }
}
