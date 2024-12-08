package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Candidate;

public class CandidateDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/voting_system";
	private String jdbcUserName="root";
	private String jdbcPassword="12345678";
	
	private static final String INSERT_CANDIDATE_SQL="INSERT INTO candidate"+"(first_name,last_name,email) VALUES "+"(?,?,?);";
	private static final String SELECT_CANDIDATE_BY_ID="SELECT * FROM candidate where candidate_id=?;";
	private static final String SELECT_ALL_CANDIDATE="select * from candidate;";
	private static final String DELETE_CANDIDATE_SQL="delete from candidate where candidate_id=?;";
	private static final String UPDATE_CANDIDATE_SQL="update candidate set first_name=?,last_name=? ,email=? where candidate_id=?;";
	private static final String SELECT_CANDIDATE_BY_EMAIL="SELECT * FROM candidate where email=?;";
	
	
	public CandidateDAO() {


	} 
	//database connection
	public Connection getConnection()
	{
		Connection connection=null;
		
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	public void insertCandidate(Candidate candidate)
	{
		CandidateDAO dao=new CandidateDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_CANDIDATE_SQL);
			preparedStatement.setString(1, candidate.getFirstname());
			preparedStatement.setString(2, candidate.getLastname());
			preparedStatement.setString(3, candidate.getEmail());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Candidate selectCandidate(int id)
	{
		Candidate candidate=new Candidate();
		CandidateDAO dao=new CandidateDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_CANDIDATE_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
			candidate.setCandidateId(id);	
			candidate.setFirstname(resultSet.getString("first_name"));
			candidate.setLastname(resultSet.getString("last_name"));
			candidate.setEmail(resultSet.getString("email"));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return candidate;
	}
	
	public Candidate selectCandidate(String email)
	{
		Candidate candidate=new Candidate();
		CandidateDAO dao=new CandidateDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_CANDIDATE_BY_EMAIL);
			preparedStatement.setString(1, email);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
	            
	            return new Candidate(
	                resultSet.getInt("candidate_id"),
	                resultSet.getString("first_name"),
	                resultSet.getString("last_name"),
	                resultSet.getString("email")
	            );
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<Candidate> selectAllCandidate()
	{
		List<Candidate> users=new ArrayList<Candidate>();
		CandidateDAO dao=new CandidateDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_CANDIDATE);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int candidate_id = resultSet.getInt("candidate_id");		
				String first_name=resultSet.getString("first_name");
				String last_name=resultSet.getString("last_name");
				String email=resultSet.getString("email");
				
				users.add(new Candidate(candidate_id,first_name,last_name, email));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public boolean deleteCandidate(int id)
	{
		boolean status=false;
		CandidateDAO dao=new CandidateDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_CANDIDATE_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean updateCandidate(Candidate candidate)
	{
		boolean status=false;
		CandidateDAO dao=new CandidateDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_CANDIDATE_SQL);
			preparedStatement.setString(1, candidate.getFirstname());
			preparedStatement.setString(2, candidate.getLastname());
			preparedStatement.setString(3, candidate.getEmail());
			preparedStatement.setInt(4, candidate.getCandidateId());
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	public int countTotalCandidates() {
        int count = 0;
        CandidateDAO dao=new CandidateDAO();
        try (Connection connection = dao.getConnection()) {
            String query = "SELECT COUNT(*) AS total FROM candidate";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
	
  
}
