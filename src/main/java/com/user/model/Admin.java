package com.user.model;

public class Admin {

	private int adminId;
	

	private String username;
	private String password;
    private String firstname;
    private String lastname;
	private String email;
	
	public Admin() {
		
	}
	 public Admin(String username, String password,String firstname,String lastname, String email) {
	        
	        this.username = username;
	        this.password = password;
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.email = email;
	    }
	  public Admin(int adminId, String username, String password,String firstname,String lastname, String email) {
	        this.adminId = adminId;
	        this.username = username;
	        this.password = password;
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.email = email;
	    
	 }
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", username=" + username + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + "]";
	}
	  
	  
}
