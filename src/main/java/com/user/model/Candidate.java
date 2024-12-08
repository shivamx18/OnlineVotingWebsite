package com.user.model;

public class Candidate {

		private int candidateId;  
	    private String firstname;
	    private String lastname;
	    private String email;

	    public Candidate() {
	    	
	    }
	    public Candidate(String firstname,String lastname, String email) {
	        
	       
	        this.firstname=firstname;
	        this.lastname=lastname;
	        this.email=email;
	       
	    }
	    public Candidate(int candidateId, String firstname,String lastname, String email) {
	        super();
	    	this.candidateId = candidateId;
	       
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
			return "Candidate [candidateId=" + candidateId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
		}

	
}
