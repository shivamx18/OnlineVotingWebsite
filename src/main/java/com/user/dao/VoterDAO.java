package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Voter;

public class VoterDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/voting_system";
	private String jdbcUserName="root";
	private String jdbcPassword="12345678";
	
	private static final String INSERT_VOTER_SQL="INSERT INTO voter"+"(username,password,first_name,last_name,email) VALUES "+"(?,?,?,?,?);";
	private static final String SELECT_VOTER_BY_ID="SELECT * FROM voter where voter_id=?;";
	private static final String SELECT_ALL_VOTER="select * from voter;";
	private static final String DELETE_VOTER_SQL="delete from voter where voter_id=?;";
	private static final String UPDATE_VOTER_SQL="update voter set username=?,password=?,first_name=?,last_name=? email=? where voter_id=?;";
	
	
	public VoterDAO() {


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
	
	
	public void insertVoter(Voter voter)
	{
		VoterDAO dao=new VoterDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_VOTER_SQL);
			preparedStatement.setString(1, voter.getUsername());
			preparedStatement.setString(2, voter.getPassword());
			preparedStatement.setString(3, voter.getFirstname());
			preparedStatement.setString(4, voter.getLastname());
			preparedStatement.setString(5, voter.getEmail());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Voter selectVoter(int id)
	{
		Voter voter=new Voter();
		VoterDAO dao=new VoterDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_VOTER_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
			voter.setVoterId(id);	
			voter.setUsername(resultSet.getString("username"));
			voter.setPassword(resultSet.getString("password"));
			voter.setFirstname(resultSet.getString("first_name"));
			voter.setLastname(resultSet.getString("last_name"));
			voter.setEmail(resultSet.getString("email"));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return voter;
	}
	
	
	public List<Voter> selectAllVoter()
	{
		List<Voter> users=new ArrayList<Voter>();
		VoterDAO dao=new VoterDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_VOTER);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int voter_id = resultSet.getInt("voter_id");
				String username=resultSet.getString("username");
				String password=resultSet.getString("password");
				String first_name=resultSet.getString("first_name");
				String last_name=resultSet.getString("last_name");
				String email=resultSet.getString("email");
				
				users.add(new Voter(voter_id,username,password,first_name,last_name, email));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public boolean deleteVoter(int id)
	{
		boolean status=false;
		VoterDAO dao=new VoterDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_VOTER_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean updateVoter(Voter voter)
	{
		boolean status=false;
		VoterDAO dao=new VoterDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_VOTER_SQL);
			preparedStatement.setString(1, voter.getUsername());
			preparedStatement.setString(2, voter.getPassword());
			preparedStatement.setString(3, voter.getFirstname());
			preparedStatement.setString(4, voter.getLastname());
			preparedStatement.setString(4, voter.getEmail());
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
  
}
