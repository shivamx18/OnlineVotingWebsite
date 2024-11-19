package com.user.model;

public class Position {

	   private int positionId;
	    private String positionName;
	    
	    public Position() {
	    	
	    }
	    
	    public Position( String positionName) {
	        
	        this.positionName = positionName;
	        
	    }
	    public Position(int positionId, String positionName) {
	    	super();
	        this.positionId = positionId;
	        this.positionName = positionName;
	        
	    }

		public int getPositionId() {
			return positionId;
		}

		public void setPositionId(int positionId) {
			this.positionId = positionId;
		}

		public String getPositionName() {
			return positionName;
		}

		public void setPositionName(String positionName) {
			this.positionName = positionName;
		}

		@Override
		public String toString() {
			return "Position [positionId=" + positionId + ", positionName=" + positionName + "]";
		}
		
	
	    
}