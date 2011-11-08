package com.hqsolution.hqserver.app.common;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;
import org.jpos.util.LogEvent;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.hqsolution.hqserver.app.exception.DuplicateKeyException;
import com.hqsolution.hqserver.app.log.HQLog;
import com.hqsolution.hqserver.app.log.LogFactory;
import com.hqsolution.hqserver.util.HQConfiguration;
import com.hqsolution.hqserver.util.SystemConstant;

/**
 * A <code>LoggableStatement<code> is a
 * {@link java.sql.PreparedStatement PreparedStatement} with added logging capability.
 * <p>
 * In addition to the methods declared in <code>PreparedStatement</code>,
 * <code>LoggableStatement<code> provides a method {@link #getQueryString}
 * which can be used to get the query string in a format
 * suitable for logging.
 *
 */
public class PreparedStatementWrapper implements PreparedStatement {
  private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
  private static final FastDateFormat TIMESTAMP_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd-HH.mm.ss.SSSSS");

  /** used for storing parameter values needed for producing log */
  private Map parameterValues = new HashMap();

  /** the query string with question marks as parameter placeholders */
  private String sqlTemplate;

  /** a statement created from a real database connection */
  private PreparedStatement delegate;

  /** friendly name for timing log */
  private String name;

  private static final String QUESTION = "?";

  /** start of execute */
  private long start;

  /** number of batched sql stmts */
  private int batchSize = 0;

  private static final long THRESHOLD =  20000;

  private static final int QUERY_TIMEOUT = 20000;
  
  private String realm = this.getClass().getName();
  
  HQLog log = LogFactory.getTimmingLog();

  /**
   * Constructs a LoggableStatement.
   *
   * Creates {@link java.sql.PreparedStatement PreparedStatement} with the query string <code>sql</code> using
   * the specified <code>connection</code> by calling {@link java.sql.Connection#prepareStatement(String)}.
   * <p>
   * Whenever a call is made to this <code>LoggableStatement</code> it is forwarded to the
   * prepared statment created from
   * <code>connection</code> after first saving relevant parameters for use in logging output.
   *
   * @param pstmt the wrapped prepared statement
   * @param sql        java.lang.String thw sql to exectute
   * @param name friendly name for logging
   * @throws java.sql.SQLException if unable to set query timeout
   */
  PreparedStatementWrapper(String sql, PreparedStatement pstmt, String name) throws SQLException {
    this.sqlTemplate = sql;
    this.delegate = pstmt;
    this.name = name;
    if (QUERY_TIMEOUT > 0)
      delegate.setQueryTimeout(QUERY_TIMEOUT);
  }

  /**
   * Constructs a LoggableStatement.
   *
   * Creates {@link java.sql.PreparedStatement PreparedStatement} with the query string <code>sql</code> using
   * the specified <code>connection</code> by calling {@link java.sql.Connection#prepareStatement(String)}.
   * <p>
   * Whenever a call is made to this <code>LoggableStatement</code> it is forwarded to the
   * prepared statment created from
   * <code>connection</code> after first saving relevant parameters for use in logging output.
   *
   * @param pstmt the wrapped prepared statement
   * @param sql        java.lang.String thw sql to exectute
   * @throws java.sql.SQLException if unable to set query timeout
   */
  PreparedStatementWrapper(String sql, PreparedStatement pstmt) throws SQLException {
    this(sql, pstmt, null);
  }

  /** {@inheritDoc} */
  public void addBatch() throws java.sql.SQLException {
    delegate.addBatch();
    batchSize++;
  }

  /** {@inheritDoc} */
  public void addBatch(String sql) throws java.sql.SQLException {
    delegate.addBatch(sql);
    batchSize++;
  }

  /** {@inheritDoc} */
  public void cancel() throws SQLException {
    delegate.cancel();
  }

  /** {@inheritDoc} */
  public void clearBatch() throws java.sql.SQLException {
    delegate.clearBatch();
    batchSize = 0;
  }

  /** {@inheritDoc} */
  public void clearParameters() throws java.sql.SQLException {
    delegate.clearParameters();
  }

  /** {@inheritDoc} */
  public void clearWarnings() throws java.sql.SQLException {
    delegate.clearWarnings();
  }

  /** {@inheritDoc} */
  public void close() throws java.sql.SQLException {
    delegate.close();
  }

  /** {@inheritDoc} */
  public boolean execute() throws java.sql.SQLException {
    try {
      startClock();
      boolean r = delegate.execute();
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public boolean execute(String sql) throws java.sql.SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      boolean r = delegate.execute(sql);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public int[] executeBatch() throws java.sql.SQLException {
    try {
      startClock();
      int[] r = delegate.executeBatch();
      stopClock(true);
      batchSize = 0;
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public java.sql.ResultSet executeQuery() throws java.sql.SQLException {
    try {
      startClock();
      ResultSet r = delegate.executeQuery();
      stopClock();
      return new ResultSetWrapper(this, r);
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public java.sql.ResultSet executeQuery(String sql)
    throws java.sql.SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      ResultSet r = delegate.executeQuery(sql);
      stopClock();
      return new ResultSetWrapper(this, r);
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public int executeUpdate() throws java.sql.SQLException {
    try {
      startClock();
      int r = delegate.executeUpdate();
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public int executeUpdate(String sql) throws java.sql.SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      int r = delegate.executeUpdate(sql);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public java.sql.Connection getConnection() throws java.sql.SQLException {
    return delegate.getConnection();
  }

  /** {@inheritDoc} */
  public int getFetchDirection() throws java.sql.SQLException {
    return delegate.getFetchDirection();
  }

  /** {@inheritDoc} */
  public int getFetchSize() throws java.sql.SQLException {
    return delegate.getFetchSize();
  }

  /** {@inheritDoc} */
  public int getMaxFieldSize() throws java.sql.SQLException {
    return delegate.getMaxFieldSize();
  }

  /** {@inheritDoc} */
  public int getMaxRows() throws java.sql.SQLException {
    return delegate.getMaxRows();
  }

  /** {@inheritDoc} */
  public java.sql.ResultSetMetaData getMetaData()
    throws java.sql.SQLException {
    return delegate.getMetaData();
  }

  /** {@inheritDoc} */
  public boolean getMoreResults() throws java.sql.SQLException {
    return delegate.getMoreResults();
  }

  /** {@inheritDoc} */
  public int getQueryTimeout() throws java.sql.SQLException {
    return delegate.getQueryTimeout();
  }

  /** {@inheritDoc} */
  public java.sql.ResultSet getResultSet() throws java.sql.SQLException {
    return new ResultSetWrapper(this, delegate.getResultSet());
  }

  /** {@inheritDoc} */
  public int getResultSetConcurrency() throws java.sql.SQLException {
    return delegate.getResultSetConcurrency();
  }

  /** {@inheritDoc} */
  public int getResultSetType() throws java.sql.SQLException {
    return delegate.getResultSetType();
  }

  /** {@inheritDoc} */
  public int getUpdateCount() throws java.sql.SQLException {
    return delegate.getUpdateCount();
  }

  /** {@inheritDoc} */
  public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
    return delegate.getWarnings();
  }

  /** {@inheritDoc} */
  public void setArray(int i, java.sql.Array x)
    throws java.sql.SQLException {

    delegate.setArray(i, x);
    saveQueryParamValue(i, x);

  }

  /** {@inheritDoc} */
  public void setAsciiStream(
    int parameterIndex,
    java.io.InputStream x,
    int length)
    throws java.sql.SQLException {

    delegate.setAsciiStream(parameterIndex, x, length);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setBigDecimal(int parameterIndex, java.math.BigDecimal x)
    throws java.sql.SQLException {
    delegate.setBigDecimal(parameterIndex, x);
    saveQueryParamValue(parameterIndex, x);

  }

  /** {@inheritDoc} */
  public void setBinaryStream(
    int parameterIndex,
    java.io.InputStream x,
    int length)
    throws java.sql.SQLException {
    delegate.setBinaryStream(parameterIndex, x, length);
    saveQueryParamValue(parameterIndex, x);

  }

  /** {@inheritDoc} */
  public void setBlob(int i, java.sql.Blob x) throws java.sql.SQLException {
    delegate.setBlob(i, x);
    saveQueryParamValue(i, x);
  }

  /** {@inheritDoc} */
  public void setBoolean(int parameterIndex, boolean x)
    throws java.sql.SQLException {
    delegate.setBoolean(parameterIndex, x);
    saveQueryParamValue(parameterIndex, Boolean.valueOf(x));

  }

  /** {@inheritDoc} */
  public void setByte(int parameterIndex, byte x)
    throws java.sql.SQLException {
    delegate.setByte(parameterIndex, x);
    saveQueryParamValue(parameterIndex, new Integer(x));
  }

  /** {@inheritDoc} */
  public void setBytes(int parameterIndex, byte[] x)
    throws java.sql.SQLException {
    delegate.setBytes(parameterIndex, x);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setCharacterStream(
    int parameterIndex,
    java.io.Reader reader,
    int length)
    throws java.sql.SQLException {
    delegate.setCharacterStream(parameterIndex, reader, length);
    saveQueryParamValue(parameterIndex, reader);

  }

  /** {@inheritDoc} */
  public void setClob(int i, java.sql.Clob x) throws java.sql.SQLException {
    delegate.setClob(i, x);
    saveQueryParamValue(i, x);

  }

  /** {@inheritDoc} */
  public void setCursorName(String name) throws java.sql.SQLException {
    delegate.setCursorName(name);

  }

  /** {@inheritDoc} */
  public void setDate(int parameterIndex, java.sql.Date x)
    throws java.sql.SQLException {

    delegate.setDate(parameterIndex, x);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setDate(
    int parameterIndex,
    java.sql.Date x,
    java.util.Calendar cal)
    throws java.sql.SQLException {
    delegate.setDate(parameterIndex, x, cal);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setDouble(int parameterIndex, double x)
    throws java.sql.SQLException {
    delegate.setDouble(parameterIndex, x);
    saveQueryParamValue(parameterIndex, new Double(x));
  }

  /** {@inheritDoc} */
  public void setEscapeProcessing(boolean enable)
    throws java.sql.SQLException {
    delegate.setEscapeProcessing(enable);

  }

  /** {@inheritDoc} */
  public void setFetchDirection(int direction) throws java.sql.SQLException {
    delegate.setFetchDirection(direction);
  }

  /** {@inheritDoc} */
  public void setFetchSize(int rows) throws java.sql.SQLException {
    delegate.setFetchSize(rows);
  }

  /** {@inheritDoc} */
  public void setFloat(int parameterIndex, float x)
    throws java.sql.SQLException {
    delegate.setFloat(parameterIndex, x);
    saveQueryParamValue(parameterIndex, new Float(x));

  }

  /** {@inheritDoc} */
  public void setInt(int parameterIndex, int x)
    throws java.sql.SQLException {
    delegate.setInt(parameterIndex, x);
    saveQueryParamValue(parameterIndex, new Integer(x));
  }

  /** {@inheritDoc} */
  public void setLong(int parameterIndex, long x)
    throws java.sql.SQLException {
    delegate.setLong(parameterIndex, x);
    saveQueryParamValue(parameterIndex, new Long(x));

  }

  /** {@inheritDoc} */
  public void setMaxFieldSize(int max) throws java.sql.SQLException {
    delegate.setMaxFieldSize(max);

  }

  /** {@inheritDoc} */
  public void setMaxRows(int max) throws java.sql.SQLException {
    delegate.setMaxRows(max);
  }

  /** {@inheritDoc} */
  public void setNull(int parameterIndex, int sqlType)
    throws java.sql.SQLException {
    delegate.setNull(parameterIndex, sqlType);
    saveQueryParamValue(parameterIndex, null);
  }

  /** {@inheritDoc} */
  public void setNull(int paramIndex, int sqlType, String typeName)
    throws java.sql.SQLException {
    delegate.setNull(paramIndex, sqlType, typeName);
    saveQueryParamValue(paramIndex, null);

  }

  /** {@inheritDoc} */
  public void setObject(int parameterIndex, Object x)
    throws java.sql.SQLException {
    try {
      delegate.setObject(parameterIndex, x);
      saveQueryParamValue(parameterIndex, x);
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public void setObject(int parameterIndex, Object x, int targetSqlType)
    throws java.sql.SQLException {
    delegate.setObject(parameterIndex, x, targetSqlType);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setObject(
    int parameterIndex,
    Object x,
    int targetSqlType,
    int scale)
    throws java.sql.SQLException {

    delegate.setObject(parameterIndex, x, targetSqlType, scale);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setQueryTimeout(int seconds) throws java.sql.SQLException {
    delegate.setQueryTimeout(seconds);
  }

  /** {@inheritDoc} */
  public void setRef(int i, java.sql.Ref x) throws java.sql.SQLException {
    delegate.setRef(i, x);
    saveQueryParamValue(i, x);

  }

  /** {@inheritDoc} */
  public void setShort(int parameterIndex, short x)
    throws java.sql.SQLException {
    delegate.setShort(parameterIndex, x);
    saveQueryParamValue(parameterIndex, new Integer(x));
  }

  /** {@inheritDoc} */
  public void setString(int parameterIndex, String x)
    throws java.sql.SQLException {

    try {
      delegate.setString(parameterIndex, x);
    } catch (SQLException e) {
      throw handleException(e);
    }
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setTime(int parameterIndex, java.sql.Time x)
    throws java.sql.SQLException {
    delegate.setTime(parameterIndex, x);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setTime(
    int parameterIndex,
    java.sql.Time x,
    java.util.Calendar cal)
    throws java.sql.SQLException {
    delegate.setTime(parameterIndex, x, cal);
    saveQueryParamValue(parameterIndex, x);

  }

  /** {@inheritDoc} */
  public void setTimestamp(int parameterIndex, java.sql.Timestamp x)
    throws java.sql.SQLException {
    delegate.setTimestamp(parameterIndex, x);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setTimestamp(
    int parameterIndex,
    java.sql.Timestamp x,
    java.util.Calendar cal)
    throws java.sql.SQLException {
    delegate.setTimestamp(parameterIndex, x, cal);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public void setUnicodeStream(
    int parameterIndex,
    java.io.InputStream x,
    int length)
    throws java.sql.SQLException {
    //noinspection Deprecation
    delegate.setUnicodeStream(parameterIndex, x, length);
    saveQueryParamValue(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public ParameterMetaData getParameterMetaData() throws SQLException {
    return delegate.getParameterMetaData();
  }

  /** {@inheritDoc} */
  public void setURL(int parameterIndex, URL x) throws SQLException {
    delegate.setURL(parameterIndex, x);
  }

  /** {@inheritDoc} */
  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      boolean r = delegate.execute(sql, autoGeneratedKeys);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      boolean r = delegate.execute(sql, columnIndexes);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public boolean execute(String sql, String[] columnNames) throws SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      boolean r = delegate.execute(sql, columnNames);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      int r = delegate.executeUpdate(sql, autoGeneratedKeys);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      int r = delegate.executeUpdate(sql, columnIndexes);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    try {
      startClock();
      sqlTemplate = sql;
      int r = delegate.executeUpdate(sql, columnNames);
      stopClock();
      return r;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public ResultSet getGeneratedKeys() throws SQLException {
    return new ResultSetWrapper(this, delegate.getGeneratedKeys());
  }

  /** {@inheritDoc} */
  public boolean getMoreResults(int current) throws SQLException {
    return delegate.getMoreResults(current);
  }

  /** {@inheritDoc} */
  public int getResultSetHoldability() throws SQLException {
    return delegate.getResultSetHoldability();
  }

  /** {@inheritDoc} */
  public String getQueryString() {
    StringBuffer buf = new StringBuffer();
    try {
      int startFrom = 0;
      int arg = 0;
      while (startFrom < (sqlTemplate.length() - 1) && sqlTemplate.indexOf(QUESTION, startFrom) >= 0) {
        int questionPos = sqlTemplate.indexOf(QUESTION, startFrom);
        buf.append(sqlTemplate.substring(startFrom, questionPos));
        startFrom = questionPos;
        startFrom++;

        arg++;
        Object v = parameterValues.get(new Integer(arg));
        if (v == null) {
          buf.append("NULL");
        } else {
          if (v instanceof String || v instanceof Timestamp || v instanceof Date) {
            buf.append("'");
            if (v instanceof Timestamp) {
            	 buf.append(TIMESTAMP_FORMAT.format((Timestamp) v));
            } else if (v instanceof Date) {
                buf.append(DATE_FORMAT.format((Date) v));
            } else {
              String s = (String) v;
              if (s.length() > 50) {
                buf.append(s.substring(0,50));
                buf.append("...");
              } else {
                buf.append(s);
              }
            }
            buf.append("'");
          } else {
            buf.append(v.toString());
          }
        }
      }
      if (startFrom < (sqlTemplate.length() - 1))
        buf.append(sqlTemplate.substring(startFrom));
      
    } catch (Throwable t) {
    	log.error("Error getting resolved query string " + parameterValues, t);
    }
    return buf.toString().trim();
  }

  /** {@inheritDoc} */
  public boolean isWrapperFor(Class<?> aClass) throws SQLException {
    return delegate.isWrapperFor(aClass);
  }

  /** {@inheritDoc} */
  public <T> T unwrap(Class<T> tClass) throws SQLException {
    return delegate.unwrap(tClass);
  }

  /** {@inheritDoc} */
  public void setAsciiStream(int i, InputStream inputStream) throws SQLException {
    delegate.setAsciiStream(i, inputStream);
  }

  /** {@inheritDoc} */
  public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
    delegate.setAsciiStream(i, inputStream, l);
  }

  /** {@inheritDoc} */
  public void setBinaryStream(int i, InputStream inputStream) throws SQLException {
    delegate.setBinaryStream(i, inputStream);
  }

  /** {@inheritDoc} */
  public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
    delegate.setBinaryStream(i, inputStream, l);
  }

  /** {@inheritDoc} */
  public void setBlob(int i, InputStream inputStream) throws SQLException {
    delegate.setBlob(i, inputStream);
  }

  /** {@inheritDoc} */
  public void setBlob(int i, InputStream inputStream, long l) throws SQLException {
    delegate.setBlob(i, inputStream, l);
  }

  /** {@inheritDoc} */
  public void setCharacterStream(int i, Reader reader) throws SQLException {
    delegate.setCharacterStream(i, reader);
  }

  /** {@inheritDoc} */
  public void setCharacterStream(int i, Reader reader, long l) throws SQLException {
    delegate.setCharacterStream(i, reader, l);
  }

  /** {@inheritDoc} */
  public void setClob(int i, Reader reader) throws SQLException {
    delegate.setClob(i, reader);
  }

  /** {@inheritDoc} */
  public void setClob(int i, Reader reader, long l) throws SQLException {
    delegate.setClob(i, reader, l);
  }

  /** {@inheritDoc} */
  public void setNCharacterStream(int i, Reader reader) throws SQLException {
    delegate.setNCharacterStream(i, reader);
  }

  /** {@inheritDoc} */
  public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {
    delegate.setNCharacterStream(i, reader, l);
  }

  /** {@inheritDoc} */
  public void setNClob(int i, NClob nClob) throws SQLException {
    delegate.setNClob(i, nClob);
  }

  /** {@inheritDoc} */
  public void setNClob(int i, Reader reader) throws SQLException {
    delegate.setNClob(i, reader);
  }

  /** {@inheritDoc} */
  public void setNClob(int i, Reader reader, long l) throws SQLException {
    delegate.setNClob(i, reader, l);
  }

  /** {@inheritDoc} */
  public void setNString(int i, String s) throws SQLException {
    delegate.setNString(i, s);
  }

  /** {@inheritDoc} */
  public void setRowId(int i, RowId rowId) throws SQLException {
    delegate.setRowId(i, rowId);
  }

  /** {@inheritDoc} */
  public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {
    delegate.setSQLXML(i, sqlxml);
  }

  /** {@inheritDoc} */
  public boolean isClosed() throws SQLException {
    return delegate.isClosed();
  }

  /** {@inheritDoc} */
  public boolean isPoolable() throws SQLException {
    return delegate.isPoolable();
  }

  /** {@inheritDoc} */
  public void setPoolable(boolean b) throws SQLException {
    delegate.setPoolable(b);
  }

  /**
   * Saves the parameter value <code>obj</code> for the specified <code>position</code> for use in logging output
   *
   * @param position position (starting at 1) of the parameter to save
   * @param obj      java.lang.Object the parameter value to save
   */
  private void saveQueryParamValue(int position, Object obj) {
    parameterValues.put(position, obj);
  }

  private void startClock() {
      log.trace("Executing SQL stmt " + (name==null?"":name+ " ") + getQueryString());
    start = System.currentTimeMillis();
  }

  private void stopClock() {
    stopClock(false);
  }

  private void stopClock(boolean batched) {
    long stop = System.currentTimeMillis();
    long dur = stop - start;
    
    if (name != null) {
      if (batched) {
        log.debug(name + "-b " + dur);
      } else {
        log.debug(name + " " + dur);
      }
    }
    if (THRESHOLD > 0L && dur > THRESHOLD) {
      if (batched) {
        log.warn("Slow " + batchSize + " batch SQL stmts " + (name==null?"":name+" ")
          + dur + " ms " + getQueryString());
      } else {
        log.warn("Slow SQL stmt " + (name==null?"":name+" ") + dur + " ms " + getQueryString());
      }
    }
    start = 0L;
  }

  /**
   * Wrap a driver sql exception with our own that contains the sql stmt being processed,
   * and may use specific dup key exception class if applicable
   * @param s sql exception to wrap
   * @return SQLException wrapped sql exception
   */
  private SQLException handleException(SQLException s) {
    if (s == null)
      return s;

    String tm = "";
    if (start > 0L)
      tm = (System.currentTimeMillis() - start) + "ms ";
    
    SQLException se = s.getNextException();
    String msg = s.getMessage() + " " + (se==null?"":se.getMessage()) + " " + tm + "[" + getQueryString() + "]";
    SQLException next;
    // DB2 error code for dup key is -803
    if (s.getErrorCode() == -803) {
      next = new DuplicateKeyException(msg, s.getSQLState(), s.getErrorCode());
    } else {
      if (s instanceof BatchUpdateException) {
        BatchUpdateException b = (BatchUpdateException) s;
        next = new BatchUpdateException(msg, b.getSQLState(), b.getErrorCode(), b.getUpdateCounts());
      } else {
        next = new SQLException(msg, s.getSQLState(), s.getErrorCode());
      }
    }
    if (se != null)
      next.setNextException(se);
    // use stack trace from driver exception so we keep the real stack info
    next.setStackTrace(s.getStackTrace());
    return next;
  }

  /** @return name of statement */
  public String getName() {
    return name;
  }

  /**
   * @return string representation of object
   * @see Object#toString()
   */
  public final String toString() {
    final StringBuffer sb = new StringBuffer(200);
    sb.append("PreparedStatementWrapper{");
    sb.append(" wrappedStatement=").append(delegate);
    sb.append(", sql=\"").append(getQueryString());
    sb.append("\"}");
    return sb.toString();
  }
}

