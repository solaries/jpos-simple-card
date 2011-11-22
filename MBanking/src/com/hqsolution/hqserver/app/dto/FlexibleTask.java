package com.hqsolution.hqserver.app.dto;

import java.io.Serializable;

/**
 * To transfer object and taskcode
 * 
 * @author QuanLe
 *
 */
public class FlexibleTask implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -940962263294051109L;
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


	/**
	 * Set Object to be transferred
	 * 
	 * @param data Object to be transferred
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Object getData() {
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((taskCode == null) ? 0 : taskCode.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlexibleTask other = (FlexibleTask) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (taskCode == null) {
			if (other.taskCode != null)
				return false;
		} else if (!taskCode.equals(other.taskCode))
			return false;
		return true;
	}
	
	
}
