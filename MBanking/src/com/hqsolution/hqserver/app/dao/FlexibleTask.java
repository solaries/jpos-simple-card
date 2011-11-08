package com.hqsolution.hqserver.app.dao;

public class FlexibleTask {
	private String taskCode;
	private Object data;
	
	public FlexibleTask() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public FlexibleTask(String taskCode, Object data) {
		super();
		this.taskCode = taskCode;
		this.data = data;
	}



	public void setData(Object data) {
		this.data = data;
	}
	
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
}
