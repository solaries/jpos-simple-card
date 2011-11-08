package com.hqsolution.hqserver.app.log;

/**
 * Determine of the log is able to receive a log level.
 * @author HUNGPT
 *
 */
public interface Logable {
	
	public boolean isTraceAble();
	
	public boolean isDebugAble();
	
	public boolean isInfoAble();
	
	public boolean isWarnAble();
	
	public boolean isErrorAble();
	
	public boolean isFatalAble();
}
