package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Position;

public class PositionDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/voting_system";
	private String jdbcUserName="root";
	private String jdbcPassword="12345678";
	
	private static final String INSERT_POSITION_SQL="INSERT INTO position (position_name) VALUES (?);";
	private static final String SELECT__POSITION_BY_ID="SELECT * FROM position where position_id=?;";
	private static final String SELECT_ALL__POSITION="select * from position;";
	private static final String DELETE__POSITION_SQL="delete from position where position_id=?;";
	private static final String UPDATE__POSITION_SQL="update position set position_name=? where position_id=?;";
	
	
	public PositionDAO() {


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
	
	
	public void insertPosition(Position position)
	{
		PositionDAO dao=new PositionDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_POSITION_SQL);
			preparedStatement.setString(1, position.getPositionName());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Position selectPosition(int id)
	{
		Position position=new Position();
		PositionDAO dao=new PositionDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT__POSITION_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
			position.setPositionId(id);	
			position.setPositionName(resultSet.getString("position_name"));
			
			
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return position;
	}
	
	
	public List<Position> selectAllPosition()
	{
		List<Position> users=new ArrayList<Position>();
		PositionDAO dao=new PositionDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL__POSITION);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int position_id = resultSet.getInt("position_id");
				String position_name = resultSet.getString("position_name");
				
				
				
				users.add(new Position(position_id,position_name));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public boolean deletePosition(int id)
	{
		boolean status=false;
		PositionDAO dao=new PositionDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE__POSITION_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean updatePosition(Position position)
	{
		boolean status=false;
		PositionDAO dao=new PositionDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE__POSITION_SQL);
			
			preparedStatement.setString(1, position.getPositionName());
			
			
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
   public static void main(String args[])
   {
	   PositionDAO dao=new PositionDAO();
	   if(dao.getConnection()!=null)
	   {
		   System.out.println("Successfully connected to the database!!");
	   }
	   else
	   {   
		   System.out.println("Problem in database connection!!");
	   }
	   
	   //Data insertion
	   Position position = new Position("abc");
	   
	   //dao.insertPosition(position);
	   dao.deletePosition(1);
		   
   }
}
