package com.user.model;

public class Candidate {

	 private int candidateId;
	    private String username;
	    private String password;
	    private String firstname;
	    private String lastname;
	    private String email;

	    public Candidate() {
	    	
	    }
	    public Candidate(String username, String password,String firstname,String lastname, String email) {
	        
	        this.username=username;
	        this.password=password;
	        this.firstname=firstname;
	        this.lastname=lastname;
	        this.email=email;
	       
	    }
	    public Candidate(int candidateId, String username, String password,String firstname,String lastname, String email) {
	        super();
	    	this.candidateId = candidateId;
	        this.username=username;
	        this.password=password;
	        this.firstname=firstname;
	        this.lastname=lastname;
	        this.email=email;
	       
	    }
		public int getCandidateId() {
			return candidateId;
		}
		public void setCandidateId(int candidateId) {
			this.candidateId = candidateId;
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
			return "Candidate [candidateId=" + candidateId + ", username=" + username + ", password=" + password
					+ ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
		}

	
}
