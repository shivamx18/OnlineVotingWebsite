package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Vote;

public class VoteDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/voting_system";
	private String jdbcUserName="root";
	private String jdbcPassword="12345678";
	
	private static final String INSERT_VOTE_SQL="INSERT INTO vote"+"(voter_id,candidate_id) VALUES "+"(?,?);";
	private static final String SELECT_BY_VOTER_ID="SELECT * FROM vote where voter_id=?;";
	private static final String SELECT_ALL_VOTE="select * from vote;";
	private static final String DELETE_VOTE_SQL="delete from vote where voter_id=?;";
	private static final String UPDATE_VOTE_SQL="update vote set candidate_id=? where voter_id=?;";
	private static final String COUNT_TOTAL_VOTE="SELECT candidate_id, COUNT(*) AS total_votes FROM vote GROUP BY candidate_id ORDER BY total_votes DESC;";
	
	
	public VoteDAO() {


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
	
	
	public void insertVote(Vote vote)
	{
		VoteDAO dao=new VoteDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_VOTE_SQL);
			preparedStatement.setInt(1, vote.getVoterId());
			preparedStatement.setInt(2, vote.getCandidateId()); 
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Vote selectVote(int id)
	{
		Vote vote=new Vote();
		VoteDAO dao=new VoteDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_BY_VOTER_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
			vote.setVoteId(id);	
			vote.setVoterId(resultSet.getInt("voter_id"));
			vote.setCandidateId(resultSet.getInt("candidate_id"));
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return vote;
	}
	public List<Vote> countVote()
	{
		List<Vote> vote=new ArrayList<Vote>();
		VoteDAO dao=new VoteDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(COUNT_TOTAL_VOTE);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				 
				int total_votes = resultSet.getInt("total_votes");
				int candidate_id = resultSet.getInt("candidate_id");
				
				
				
				vote.add(new Vote(candidate_id,total_votes));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return vote;
	}
	
	public List<Vote> displayAllVote()
	{
		List<Vote> users=new ArrayList<Vote>();
		VoteDAO dao=new VoteDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_VOTE);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int vote_id = resultSet.getInt("vote_id");
				int voter_id = resultSet.getInt("voter_id");
				int candidate_id = resultSet.getInt("candidate_id");
				
				
				
				users.add(new Vote(vote_id,voter_id,candidate_id));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public boolean deleteVote(int id)
	{
		boolean status=false;
		VoteDAO dao=new VoteDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_VOTE_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean updateVote(Vote vote)
	{
		boolean status=false;
		VoteDAO dao=new VoteDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_VOTE_SQL);
			
			preparedStatement.setInt(1, vote.getVoterId());
			preparedStatement.setInt(2, vote.getCandidateId());
			
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
  
}
