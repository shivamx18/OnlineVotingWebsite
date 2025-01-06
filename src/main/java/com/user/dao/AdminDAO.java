package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.Admin;

public class AdminDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/voting_system";
	private String jdbcUserName="root";
	private String jdbcPassword="12345678";
	
	private static final String INSERT_ADMIN_SQL="INSERT INTO admin"+"(username,password,first_name,last_name,email) VALUES "+"(?,?,?,?,?);";
	private static final String SELECT_ADMIN_BY_ID="SELECT * FROM admin where admin_id=?;";
	private static final String SELECT_ALL_ADMIN="select * from admin;";
	private static final String DELETE_ADMIN_SQL="delete from admin where admin_id=?;";
	private static final String UPDATE_ADMIN_SQL="update admin set username=?,password=?,first_name=?,last_name=? email=? where admin_id=?;";
	
	
	public AdminDAO() {


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
	
	
	public void insertAdmin(Admin admin)
	{
		AdminDAO dao=new AdminDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_ADMIN_SQL);
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, admin.getPassword());
			preparedStatement.setString(3, admin.getFirstname());
			preparedStatement.setString(4, admin.getLastname());
			preparedStatement.setString(5, admin.getEmail());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Admin selectAdmin(int id)
	{
		Admin admin=new Admin();
		AdminDAO dao=new AdminDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ADMIN_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet  resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
			admin.setAdminId(id);	
			admin.setUsername(resultSet.getString("username"));
			admin.setPassword(resultSet.getString("password"));
			admin.setFirstname(resultSet.getString("first_name"));
			admin.setLastname(resultSet.getString("last_name"));
			admin.setEmail(resultSet.getString("email"));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return admin;
	}
	
	
	public List<Admin> selectAllAdmin()
	{
		List<Admin> users=new ArrayList<Admin>();
		AdminDAO dao=new AdminDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_ADMIN);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int admin_id = resultSet.getInt("admin_id");
				String username=resultSet.getString("username");
				String password=resultSet.getString("password");
				String first_name=resultSet.getString("first_name");
				String last_name=resultSet.getString("last_name");
				String email=resultSet.getString("email");
				
				users.add(new Admin(admin_id,username,password,first_name,last_name, email));
	
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	public boolean deleteAdmin(int id)
	{
		boolean status=false;
		AdminDAO dao=new AdminDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_ADMIN_SQL);
			preparedStatement.setInt(1, id);
			
			status=preparedStatement.execute();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	public boolean updateAdmin(Admin admin)
	{
		boolean status=false;
		AdminDAO dao=new AdminDAO();
		try(Connection connection=dao.getConnection())
		{
			PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_ADMIN_SQL);
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, admin.getPassword());
			preparedStatement.setString(3, admin.getFirstname());
			preparedStatement.setString(4, admin.getLastname());
			preparedStatement.setString(4, admin.getEmail());
			
			
			
			status=preparedStatement.executeUpdate()>0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
 
}
