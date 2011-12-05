package com.hqsolution.hqserver.app.resource;

import com.hqsolution.hqserver.app.common.DatabaseConnection;

/**
 * Common method for managing resource of jPos Transaction. 
 * @author HUNGPT
 *
 */
public interface ResourceManager {
	
	/**
	 * Get connection is appropriate with configuration and tie up to data access object.
	 * @return DatabaseConnection connection
	 */
	public DatabaseConnection getQ2ContainerConnection();
}
