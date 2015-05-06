package agh.sius.server.common;

public enum OrderState {
	IN_PROGRESS, REALIZED;
	
	@Override
	public String toString(){
		if (this.equals(IN_PROGRESS))
			return "W trakcie realizacji";
		
		if (this.equals(REALIZED))
			return "Zrealizowane";
		
		return "Unknown";
	}
}
