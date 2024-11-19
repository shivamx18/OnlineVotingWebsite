package com.user.model;

public class Vote {
	private int voteId;
    private int voterId;
    private int candidateId;
    private int totalVotes;
    
    public Vote()
    {}
    
    public Vote( int voterId, int candidateId, int totalVotes) {
        
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.totalVotes = totalVotes;
        
    }
 public Vote(  int candidateId, int totalVotes) {
        
       
        this.candidateId = candidateId;
        this.totalVotes = totalVotes;
        
    }
    public Vote(int voteId, int voterId, int candidateId, int totalVotes) {
    	super();
        this.voteId = voteId;
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.totalVotes = totalVotes;
        
    }

	public int getVoteId() {
		return voteId;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public int getTotalVotes() {
	    return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
	    this.totalVotes = totalVotes;
	 }
	
	@Override
    public String toString() {
        return "Vote{candidateId=" + candidateId + ", totalVotes=" + totalVotes + "}";
    }
   

}
