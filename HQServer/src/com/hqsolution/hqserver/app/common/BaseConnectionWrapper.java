package com.hqsolution.hqserver.app.common;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;


/**
 * Wrapper for jdbc connection so we can check that it is closed properly.
 * Delegates all calls to delegate object.
 */
public class BaseConnectionWrapper implements DatabaseConnection {

  private boolean closed;
  private Connection delegate;
  private Exception createStack = null;

  /**
   * Create a new wrapped connection as a delegate for the passed
   * real connection
   * @param delegate real connection to wrap
   */
  protected BaseConnectionWrapper(Connection delegate) {
    this.delegate = delegate;

  }

  /**
   * Get the underlying real connection
   * @return connection underlying connection
   */
  protected Connection getDelegate() {
    return delegate;
  }

  /** {@inheritDoc} */
  public void clearWarnings() throws SQLException {
    getDelegate().clearWarnings();
  }

  /** {@inheritDoc} */
  public void close() throws SQLException {
    markClosed();
    getDelegate().close();
  }

  /** mark that the connection has been closed */
  protected void markClosed() {
    if (closed){
      return;
    }
    closed=true;
    createStack=null;
  }

  /** {@inheritDoc} */
  public void commit() throws SQLException {
    getDelegate().commit();
  }

  /** {@inheritDoc} */
  public Statement createStatement() throws SQLException {
    return getDelegate().createStatement();
  }

  /** {@inheritDoc} */
  public Statement createStatement(int resultSetType, int resultSetConcurrency)
    throws SQLException {
    return getDelegate().createStatement(resultSetType, resultSetConcurrency);
  }

  /** {@inheritDoc} */
  public Statement createStatement(int resultSetType, int resultSetConcurrency,
      int resultSetHoldability) throws SQLException {
    return getDelegate().createStatement(resultSetType, resultSetConcurrency,
        resultSetHoldability);
  }

  /** {@inheritDoc} */
  public boolean getAutoCommit() throws SQLException {
    return getDelegate().getAutoCommit();
  }

  /** {@inheritDoc} */
  public String getCatalog() throws SQLException {
    return getDelegate().getCatalog();
  }

  /** {@inheritDoc} */
  public int getHoldability() throws SQLException {
    return getDelegate().getHoldability();
  }

  /** {@inheritDoc} */
  public DatabaseMetaData getMetaData() throws SQLException {
    return getDelegate().getMetaData();
  }

  /** {@inheritDoc} */
  public int getTransactionIsolation() throws SQLException {
    return getDelegate().getTransactionIsolation();
  }

  /** {@inheritDoc} */
  public Map<java.lang.String,java.lang.Class<?>> getTypeMap() throws SQLException {
    return getDelegate().getTypeMap();
  }

  /** {@inheritDoc} */
  public SQLWarning getWarnings() throws SQLException {
    return getDelegate().getWarnings();
  }

  /** {@inheritDoc} */
  public boolean isClosed() throws SQLException {
    return getDelegate().isClosed();
  }

  /** {@inheritDoc} */
  public boolean isReadOnly() throws SQLException {
    return getDelegate().isReadOnly();
  }

  /** {@inheritDoc} */
  public String nativeSQL(String sql) throws SQLException {
    return getDelegate().nativeSQL(sql);
  }

  /** {@inheritDoc} */
  public CallableStatement prepareCall(String sql) throws SQLException {
    return getDelegate().prepareCall(sql);
  }

  /** {@inheritDoc} */
  public CallableStatement prepareCall(String sql, int resultSetType,
                                       int resultSetConcurrency) throws SQLException {
    return getDelegate().prepareCall(sql, resultSetType, resultSetConcurrency);
  }

  /** {@inheritDoc} */
  public CallableStatement prepareCall(String sql, int resultSetType,
      int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    return getDelegate().prepareCall(sql, resultSetType, resultSetConcurrency,
        resultSetHoldability);
  }

  /** {@inheritDoc} */
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return prepareStatement(sql, (String) null);
  }

  /** {@inheritDoc} 
 * @throws SQLException */
  public PreparedStatement prepareStatement(String sql, String name) throws SQLException {
    try {
      return new PreparedStatementWrapper(sql, getDelegate().prepareStatement(sql), name);
    } catch (SQLException s) {
      //throw new SQLRuntimeException("Error preparing stmt: " + sql, s);
    	throw new SQLException("Error preparing stmt: " + sql, s);
    }
  }

  /** {@inheritDoc} */
  public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
    return new PreparedStatementWrapper(sql, getDelegate().prepareStatement(sql, autoGeneratedKeys));
  }

  /** {@inheritDoc} */
  public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
    return new PreparedStatementWrapper(sql, getDelegate().prepareStatement(sql, columnIndexes));
  }

  /** {@inheritDoc} */
  public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
    return new PreparedStatementWrapper(sql, getDelegate().prepareStatement(sql, columnNames));
  }

  /** {@inheritDoc} */
  public PreparedStatement prepareStatement(String sql, int resultSetType,
      int resultSetConcurrency) throws SQLException {
    return new PreparedStatementWrapper(sql, getDelegate().prepareStatement(sql, resultSetType, resultSetConcurrency));
  }

  /** {@inheritDoc} */
  public PreparedStatement prepareStatement(String sql, int resultSetType,
      int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    return new PreparedStatementWrapper(sql, getDelegate().prepareStatement(sql, resultSetType, resultSetConcurrency,
      resultSetHoldability));
  }

  /** {@inheritDoc} */
  public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    getDelegate().releaseSavepoint(savepoint);
  }

  /** {@inheritDoc} */
  public void rollback() throws SQLException {
    getDelegate().rollback();
  }

  /** {@inheritDoc} */
  public void rollback(Savepoint savepoint) throws SQLException {
    getDelegate().rollback(savepoint);
  }

  /** {@inheritDoc} */
  public void setAutoCommit(boolean autoCommit) throws SQLException {
    getDelegate().setAutoCommit(autoCommit);
  }

  /** {@inheritDoc} */
  public void setCatalog(String catalog) throws SQLException {
    getDelegate().setCatalog(catalog);
  }

  /** {@inheritDoc} */
  public void setHoldability(int holdability) throws SQLException {
    getDelegate().setHoldability(holdability);
  }

  /** {@inheritDoc} */
  public void setReadOnly(boolean readOnly) throws SQLException {
    getDelegate().setReadOnly(readOnly);
  }

  /** {@inheritDoc} */
  public Savepoint setSavepoint() throws SQLException {
    return getDelegate().setSavepoint();
  }

  /** {@inheritDoc} */
  public Savepoint setSavepoint(String name) throws SQLException {
    return getDelegate().setSavepoint(name);
  }

  /** {@inheritDoc} */
  public void setTransactionIsolation(int level) throws SQLException {
    getDelegate().setTransactionIsolation(level);
  }

  /** {@inheritDoc} */
  public void setTypeMap(Map<java.lang.String,java.lang.Class<?>> map) throws SQLException {
    getDelegate().setTypeMap(map);
  }

  /** {@inheritDoc} */
  public Struct createStruct(String s, Object[] objects) throws SQLException {
    return delegate.createStruct(s, objects);
  }

  /** {@inheritDoc} */
  public boolean isValid(int i) throws SQLException {
    return delegate.isValid(i);
  }

  /** {@inheritDoc} */
  public boolean isWrapperFor(Class<?> aClass) throws SQLException {
    return delegate.isWrapperFor(aClass);
  }

  /** {@inheritDoc} */
  public Array createArrayOf(String s, Object[] objects) throws SQLException {
    return delegate.createArrayOf(s, objects);
  }

  /** {@inheritDoc} */
  public void setClientInfo(Properties properties) throws SQLClientInfoException {
    delegate.setClientInfo(properties);
  }

  /** {@inheritDoc} */
  public NClob createNClob() throws SQLException {
    return delegate.createNClob();
  }

  /** {@inheritDoc} */
  public SQLXML createSQLXML() throws SQLException {
    return delegate.createSQLXML();
  }

  /** {@inheritDoc} */
  public Properties getClientInfo() throws SQLException {
    return delegate.getClientInfo();
  }

  /** {@inheritDoc} */
  public String getClientInfo(String s) throws SQLException {
    return delegate.getClientInfo(s);
  }

  /** {@inheritDoc} */
  public Blob createBlob() throws SQLException {
    return delegate.createBlob();
  }

  /** {@inheritDoc} */
  public Clob createClob() throws SQLException {
    return delegate.createClob();
  }

  /** {@inheritDoc} */
  public <T> T unwrap(Class<T> tClass) throws SQLException {
    return delegate.unwrap(tClass);
  }

  /** {@inheritDoc} */
  public void setClientInfo(String s, String s1) throws SQLClientInfoException {
    delegate.setClientInfo(s, s1);
  }

  /**
   * @return string representation of object
   * @see Object#toString()
   */
  public String toString() {
    final StringBuffer sb = new StringBuffer(200);
    sb.append("BaseConnectionWrapper{");
    sb.append("delegate=").append(delegate);
    sb.append("}");
    return sb.toString();
  }
}
