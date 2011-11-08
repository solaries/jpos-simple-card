package com.hqsolution.hqserver.app.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * HQsolution wrapper around normal jdbc database connection to
 * add additional features.
 *
 * @author HUNGPT
 */
public interface DatabaseConnection extends Connection {

  /**
   * Like normal prepare statement except takes additional name parameter which is
   * used for logging executing time of this statement to the timing log
   * @param sql to prepare
   * @param name for this stmt to use in timing log
   * @return the prepared statement
 * @throws SQLException 
   * @see java.sql.Connection#prepareStatement(String)
   */
  PreparedStatement prepareStatement(String sql, String name) throws SQLException;
}
