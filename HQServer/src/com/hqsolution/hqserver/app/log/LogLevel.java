package com.hqsolution.hqserver.app.log;

public enum LogLevel { 
	TRACE(0), 
	DEBUG(1), 
	INFO(2), 
	WARN(3), 
	ERROR(4), 
	FATAL(5);
	
	private final int weight;
	
	LogLevel(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}
	
}   
