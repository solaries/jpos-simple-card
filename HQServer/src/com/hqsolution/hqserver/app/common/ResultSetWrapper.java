package com.hqsolution.hqserver.app.common;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.BatchUpdateException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import com.hqsolution.hqserver.app.exception.DuplicateKeyException;
import com.hqsolution.hqserver.app.log.HQLog;
import com.hqsolution.hqserver.app.log.LogFactory;

/**
 * Wraps a result set so we can monitor and log slow next calls
 */
public class ResultSetWrapper implements ResultSet {

  private static final long THRESHOLD = 20000;

  private HQLog log = LogFactory.getTimmingLog();
  
  /** start of next */
  private long start;

  private final PreparedStatementWrapper statement;
  private final ResultSet delegate;

  ResultSetWrapper(PreparedStatementWrapper statement, ResultSet delegate) {
    this.statement = statement;
    this.delegate = delegate;
  }

  /** {@inheritDoc} */
  public boolean absolute(int i) throws SQLException {
    return delegate.absolute(i);
  }

  /** {@inheritDoc} */
  public void afterLast() throws SQLException {
    delegate.afterLast();
  }

  /** {@inheritDoc} */
  public void beforeFirst() throws SQLException {
    delegate.beforeFirst();
  }

  /** {@inheritDoc} */
  public void cancelRowUpdates() throws SQLException {
    delegate.cancelRowUpdates();
  }

  /** {@inheritDoc} */
  public void clearWarnings() throws SQLException {
    delegate.clearWarnings();
  }

  /** {@inheritDoc} */
  public void close() throws SQLException {
    delegate.close();
  }

  /** {@inheritDoc} */
  public void deleteRow() throws SQLException {
    delegate.deleteRow();
  }

  /** {@inheritDoc} */
  public int findColumn(String s) throws SQLException {
    return delegate.findColumn(s);
  }

  /** {@inheritDoc} */
  public boolean first() throws SQLException {
    return delegate.first();
  }

  /** {@inheritDoc} */
  public Array getArray(int i) throws SQLException {
    return delegate.getArray(i);
  }

  /** {@inheritDoc} */
  public Array getArray(String s) throws SQLException {
    return delegate.getArray(s);
  }

  /** {@inheritDoc} */
  public InputStream getAsciiStream(int i) throws SQLException {
    return delegate.getAsciiStream(i);
  }

  /** {@inheritDoc} */
  public InputStream getAsciiStream(String s) throws SQLException {
    return delegate.getAsciiStream(s);
  }

  /** {@inheritDoc} */
  public BigDecimal getBigDecimal(int i) throws SQLException {
    return delegate.getBigDecimal(i);
  }

  /** {@inheritDoc} */
  public BigDecimal getBigDecimal(int i, int i1) throws SQLException {
    return delegate.getBigDecimal(i, i1);
  }

  /** {@inheritDoc} */
  public BigDecimal getBigDecimal(String s) throws SQLException {
    return delegate.getBigDecimal(s);
  }

  /** {@inheritDoc} */
  public BigDecimal getBigDecimal(String s, int i) throws SQLException {
    return delegate.getBigDecimal(s, i);
  }

  /** {@inheritDoc} */
  public InputStream getBinaryStream(int i) throws SQLException {
    return delegate.getBinaryStream(i);
  }

  /** {@inheritDoc} */
  public InputStream getBinaryStream(String s) throws SQLException {
    return delegate.getBinaryStream(s);
  }

  /** {@inheritDoc} */
  public Blob getBlob(int i) throws SQLException {
    return delegate.getBlob(i);
  }

  /** {@inheritDoc} */
  public Blob getBlob(String s) throws SQLException {
    return delegate.getBlob(s);
  }

  /** {@inheritDoc} */
  public boolean getBoolean(int i) throws SQLException {
    return delegate.getBoolean(i);
  }

  /** {@inheritDoc} */
  public boolean getBoolean(String s) throws SQLException {
    return delegate.getBoolean(s);
  }

  /** {@inheritDoc} */
  public byte getByte(int i) throws SQLException {
    return delegate.getByte(i);
  }

  /** {@inheritDoc} */
  public byte getByte(String s) throws SQLException {
    return delegate.getByte(s);
  }

  /** {@inheritDoc} */
  public byte[] getBytes(int i) throws SQLException {
    return delegate.getBytes(i);
  }

  /** {@inheritDoc} */
  public byte[] getBytes(String s) throws SQLException {
    return delegate.getBytes(s);
  }

  /** {@inheritDoc} */
  public Reader getCharacterStream(int i) throws SQLException {
    return delegate.getCharacterStream(i);
  }

  /** {@inheritDoc} */
  public Reader getCharacterStream(String s) throws SQLException {
    return delegate.getCharacterStream(s);
  }

  /** {@inheritDoc} */
  public Clob getClob(int i) throws SQLException {
    return delegate.getClob(i);
  }

  /** {@inheritDoc} */
  public Clob getClob(String s) throws SQLException {
    return delegate.getClob(s);
  }

  /** {@inheritDoc} */
  public int getConcurrency() throws SQLException {
    return delegate.getConcurrency();
  }

  /** {@inheritDoc} */
  public String getCursorName() throws SQLException {
    return delegate.getCursorName();
  }

  /** {@inheritDoc} */
  public Date getDate(int i) throws SQLException {
    return delegate.getDate(i);
  }

  /** {@inheritDoc} */
  public Date getDate(int i, Calendar calendar) throws SQLException {
    return delegate.getDate(i, calendar);
  }

  /** {@inheritDoc} */
  public Date getDate(String s) throws SQLException {
    return delegate.getDate(s);
  }

  /** {@inheritDoc} */
  public Date getDate(String s, Calendar calendar) throws SQLException {
    return delegate.getDate(s, calendar);
  }

  /** {@inheritDoc} */
  public double getDouble(int i) throws SQLException {
    return delegate.getDouble(i);
  }

  /** {@inheritDoc} */
  public double getDouble(String s) throws SQLException {
    return delegate.getDouble(s);
  }

  /** {@inheritDoc} */
  public int getFetchDirection() throws SQLException {
    return delegate.getFetchDirection();
  }

  /** {@inheritDoc} */
  public int getFetchSize() throws SQLException {
    return delegate.getFetchSize();
  }

  /** {@inheritDoc} */
  public float getFloat(int i) throws SQLException {
    return delegate.getFloat(i);
  }

  /** {@inheritDoc} */
  public float getFloat(String s) throws SQLException {
    return delegate.getFloat(s);
  }

  /** {@inheritDoc} */
  public int getHoldability() throws SQLException {
    return delegate.getHoldability();
  }

  /** {@inheritDoc} */
  public int getInt(int i) throws SQLException {
    return delegate.getInt(i);
  }

  /** {@inheritDoc} */
  public int getInt(String s) throws SQLException {
    return delegate.getInt(s);
  }

  /** {@inheritDoc} */
  public long getLong(int i) throws SQLException {
    return delegate.getLong(i);
  }

  /** {@inheritDoc} */
  public long getLong(String s) throws SQLException {
    return delegate.getLong(s);
  }

  /** {@inheritDoc} */
  public ResultSetMetaData getMetaData() throws SQLException {
    return delegate.getMetaData();
  }

  /** {@inheritDoc} */
  public Reader getNCharacterStream(int i) throws SQLException {
    return delegate.getNCharacterStream(i);
  }

  /** {@inheritDoc} */
  public Reader getNCharacterStream(String s) throws SQLException {
    return delegate.getNCharacterStream(s);
  }

  /** {@inheritDoc} */
  public NClob getNClob(int i) throws SQLException {
    return delegate.getNClob(i);
  }

  /** {@inheritDoc} */
  public NClob getNClob(String s) throws SQLException {
    return delegate.getNClob(s);
  }

  /** {@inheritDoc} */
  public String getNString(int i) throws SQLException {
    return delegate.getNString(i);
  }

  /** {@inheritDoc} */
  public String getNString(String s) throws SQLException {
    return delegate.getNString(s);
  }

  /** {@inheritDoc} */
  public Object getObject(int i) throws SQLException {
    return delegate.getObject(i);
  }

  /** {@inheritDoc} */
  public Object getObject(int i, Map<String, Class<?>> stringClassMap) throws SQLException {
    return delegate.getObject(i, stringClassMap);
  }

  /** {@inheritDoc} */
  public Object getObject(String s) throws SQLException {
    return delegate.getObject(s);
  }

  /** {@inheritDoc} */
  public Object getObject(String s, Map<String, Class<?>> stringClassMap) throws SQLException {
    return delegate.getObject(s, stringClassMap);
  }

  /** {@inheritDoc} */
  public Ref getRef(int i) throws SQLException {
    return delegate.getRef(i);
  }

  /** {@inheritDoc} */
  public Ref getRef(String s) throws SQLException {
    return delegate.getRef(s);
  }

  /** {@inheritDoc} */
  public int getRow() throws SQLException {
    return delegate.getRow();
  }

  /** {@inheritDoc} */
  public RowId getRowId(int i) throws SQLException {
    return delegate.getRowId(i);
  }

  /** {@inheritDoc} */
  public RowId getRowId(String s) throws SQLException {
    return delegate.getRowId(s);
  }

  /** {@inheritDoc} */
  public short getShort(int i) throws SQLException {
    return delegate.getShort(i);
  }

  /** {@inheritDoc} */
  public short getShort(String s) throws SQLException {
    return delegate.getShort(s);
  }

  /** {@inheritDoc} */
  public SQLXML getSQLXML(int i) throws SQLException {
    return delegate.getSQLXML(i);
  }

  /** {@inheritDoc} */
  public SQLXML getSQLXML(String s) throws SQLException {
    return delegate.getSQLXML(s);
  }

  /** {@inheritDoc} */
  public Statement getStatement() throws SQLException {
    return statement;
  }

  /** {@inheritDoc} */
  public String getString(int i) throws SQLException {
    return delegate.getString(i);
  }

  /** {@inheritDoc} */
  public String getString(String s) throws SQLException {
    return delegate.getString(s);
  }

  /** {@inheritDoc} */
  public Time getTime(int i) throws SQLException {
    return delegate.getTime(i);
  }

  /** {@inheritDoc} */
  public Time getTime(int i, Calendar calendar) throws SQLException {
    return delegate.getTime(i, calendar);
  }

  /** {@inheritDoc} */
  public Time getTime(String s) throws SQLException {
    return delegate.getTime(s);
  }

  /** {@inheritDoc} */
  public Time getTime(String s, Calendar calendar) throws SQLException {
    return delegate.getTime(s, calendar);
  }

  /** {@inheritDoc} */
  public Timestamp getTimestamp(int i) throws SQLException {
    return delegate.getTimestamp(i);
  }

  /** {@inheritDoc} */
  public Timestamp getTimestamp(int i, Calendar calendar) throws SQLException {
    return delegate.getTimestamp(i, calendar);
  }

  /** {@inheritDoc} */
  public Timestamp getTimestamp(String s) throws SQLException {
    return delegate.getTimestamp(s);
  }

  /** {@inheritDoc} */
  public Timestamp getTimestamp(String s, Calendar calendar) throws SQLException {
    return delegate.getTimestamp(s, calendar);
  }

  /** {@inheritDoc} */
  public int getType() throws SQLException {
    return delegate.getType();
  }

  /** {@inheritDoc} */
  public InputStream getUnicodeStream(int i) throws SQLException {
    return delegate.getUnicodeStream(i);
  }

  /** {@inheritDoc} */
  public InputStream getUnicodeStream(String s) throws SQLException {
    return delegate.getUnicodeStream(s);
  }

  /** {@inheritDoc} */
  public URL getURL(int i) throws SQLException {
    return delegate.getURL(i);
  }

  /** {@inheritDoc} */
  public URL getURL(String s) throws SQLException {
    return delegate.getURL(s);
  }

  /** {@inheritDoc} */
  public SQLWarning getWarnings() throws SQLException {
    return delegate.getWarnings();
  }

  /** {@inheritDoc} */
  public void insertRow() throws SQLException {
    delegate.insertRow();
  }

  /** {@inheritDoc} */
  public boolean isAfterLast() throws SQLException {
    return delegate.isAfterLast();
  }

  /** {@inheritDoc} */
  public boolean isBeforeFirst() throws SQLException {
    return delegate.isBeforeFirst();
  }

  /** {@inheritDoc} */
  public boolean isClosed() throws SQLException {
    return delegate.isClosed();
  }

  /** {@inheritDoc} */
  public boolean isFirst() throws SQLException {
    return delegate.isFirst();
  }

  /** {@inheritDoc} */
  public boolean isLast() throws SQLException {
    return delegate.isLast();
  }

  /** {@inheritDoc} */
  public boolean last() throws SQLException {
    return delegate.last();
  }

  /** {@inheritDoc} */
  public void moveToCurrentRow() throws SQLException {
    delegate.moveToCurrentRow();
  }

  /** {@inheritDoc} */
  public void moveToInsertRow() throws SQLException {
    delegate.moveToInsertRow();
  }

  /** {@inheritDoc} */
  public boolean next() throws SQLException {
    try {
      startClock();
      boolean n = delegate.next();
      stopClock();
      return n;
    } catch (SQLException e) {
      throw handleException(e);
    }
  }

  /** {@inheritDoc} */
  public boolean previous() throws SQLException {
    return delegate.previous();
  }

  /** {@inheritDoc} */
  public void refreshRow() throws SQLException {
    delegate.refreshRow();
  }

  /** {@inheritDoc} */
  public boolean relative(int i) throws SQLException {
    return delegate.relative(i);
  }

  /** {@inheritDoc} */
  public boolean rowDeleted() throws SQLException {
    return delegate.rowDeleted();
  }

  /** {@inheritDoc} */
  public boolean rowInserted() throws SQLException {
    return delegate.rowInserted();
  }

  /** {@inheritDoc} */
  public boolean rowUpdated() throws SQLException {
    return delegate.rowUpdated();
  }

  /** {@inheritDoc} */
  public void setFetchDirection(int i) throws SQLException {
    delegate.setFetchDirection(i);
  }

  /** {@inheritDoc} */
  public void setFetchSize(int i) throws SQLException {
    delegate.setFetchSize(i);
  }

  /** {@inheritDoc} */
  public void updateArray(int i, Array array) throws SQLException {
    delegate.updateArray(i, array);
  }

  /** {@inheritDoc} */
  public void updateArray(String s, Array array) throws SQLException {
    delegate.updateArray(s, array);
  }

  /** {@inheritDoc} */
  public void updateAsciiStream(int i, InputStream inputStream) throws SQLException {
    delegate.updateAsciiStream(i, inputStream);
  }

  /** {@inheritDoc} */
  public void updateAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {
    delegate.updateAsciiStream(i, inputStream, i1);
  }

  /** {@inheritDoc} */
  public void updateAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
    delegate.updateAsciiStream(i, inputStream, l);
  }

  /** {@inheritDoc} */
  public void updateAsciiStream(String s, InputStream inputStream) throws SQLException {
    delegate.updateAsciiStream(s, inputStream);
  }

  /** {@inheritDoc} */
  public void updateAsciiStream(String s, InputStream inputStream, int i) throws SQLException {
    delegate.updateAsciiStream(s, inputStream, i);
  }

  /** {@inheritDoc} */
  public void updateAsciiStream(String s, InputStream inputStream, long l) throws SQLException {
    delegate.updateAsciiStream(s, inputStream, l);
  }

  /** {@inheritDoc} */
  public void updateBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
    delegate.updateBigDecimal(i, bigDecimal);
  }

  /** {@inheritDoc} */
  public void updateBigDecimal(String s, BigDecimal bigDecimal) throws SQLException {
    delegate.updateBigDecimal(s, bigDecimal);
  }

  /** {@inheritDoc} */
  public void updateBinaryStream(int i, InputStream inputStream) throws SQLException {
    delegate.updateBinaryStream(i, inputStream);
  }

  /** {@inheritDoc} */
  public void updateBinaryStream(int i, InputStream inputStream, int i1) throws SQLException {
    delegate.updateBinaryStream(i, inputStream, i1);
  }

  /** {@inheritDoc} */
  public void updateBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
    delegate.updateBinaryStream(i, inputStream, l);
  }

  /** {@inheritDoc} */
  public void updateBinaryStream(String s, InputStream inputStream) throws SQLException {
    delegate.updateBinaryStream(s, inputStream);
  }

  /** {@inheritDoc} */
  public void updateBinaryStream(String s, InputStream inputStream, int i) throws SQLException {
    delegate.updateBinaryStream(s, inputStream, i);
  }

  /** {@inheritDoc} */
  public void updateBinaryStream(String s, InputStream inputStream, long l) throws SQLException {
    delegate.updateBinaryStream(s, inputStream, l);
  }

  /** {@inheritDoc} */
  public void updateBlob(int i, Blob blob) throws SQLException {
    delegate.updateBlob(i, blob);
  }

  /** {@inheritDoc} */
  public void updateBlob(int i, InputStream inputStream) throws SQLException {
    delegate.updateBlob(i, inputStream);
  }

  /** {@inheritDoc} */
  public void updateBlob(int i, InputStream inputStream, long l) throws SQLException {
    delegate.updateBlob(i, inputStream, l);
  }

  /** {@inheritDoc} */
  public void updateBlob(String s, Blob blob) throws SQLException {
    delegate.updateBlob(s, blob);
  }

  /** {@inheritDoc} */
  public void updateBlob(String s, InputStream inputStream) throws SQLException {
    delegate.updateBlob(s, inputStream);
  }

  /** {@inheritDoc} */
  public void updateBlob(String s, InputStream inputStream, long l) throws SQLException {
    delegate.updateBlob(s, inputStream, l);
  }

  /** {@inheritDoc} */
  public void updateBoolean(int i, boolean b) throws SQLException {
    delegate.updateBoolean(i, b);
  }

  /** {@inheritDoc} */
  public void updateBoolean(String s, boolean b) throws SQLException {
    delegate.updateBoolean(s, b);
  }

  /** {@inheritDoc} */
  public void updateByte(int i, byte b) throws SQLException {
    delegate.updateByte(i, b);
  }

  /** {@inheritDoc} */
  public void updateByte(String s, byte b) throws SQLException {
    delegate.updateByte(s, b);
  }

  /** {@inheritDoc} */
  public void updateBytes(int i, byte[] bytes) throws SQLException {
    delegate.updateBytes(i, bytes);
  }

  /** {@inheritDoc} */
  public void updateBytes(String s, byte[] bytes) throws SQLException {
    delegate.updateBytes(s, bytes);
  }

  /** {@inheritDoc} */
  public void updateCharacterStream(int i, Reader reader) throws SQLException {
    delegate.updateCharacterStream(i, reader);
  }

  /** {@inheritDoc} */
  public void updateCharacterStream(int i, Reader reader, int i1) throws SQLException {
    delegate.updateCharacterStream(i, reader, i1);
  }

  /** {@inheritDoc} */
  public void updateCharacterStream(int i, Reader reader, long l) throws SQLException {
    delegate.updateCharacterStream(i, reader, l);
  }

  /** {@inheritDoc} */
  public void updateCharacterStream(String s, Reader reader) throws SQLException {
    delegate.updateCharacterStream(s, reader);
  }

  /** {@inheritDoc} */
  public void updateCharacterStream(String s, Reader reader, int i) throws SQLException {
    delegate.updateCharacterStream(s, reader, i);
  }

  /** {@inheritDoc} */
  public void updateCharacterStream(String s, Reader reader, long l) throws SQLException {
    delegate.updateCharacterStream(s, reader, l);
  }

  /** {@inheritDoc} */
  public void updateClob(int i, Clob clob) throws SQLException {
    delegate.updateClob(i, clob);
  }

  /** {@inheritDoc} */
  public void updateClob(int i, Reader reader) throws SQLException {
    delegate.updateClob(i, reader);
  }

  /** {@inheritDoc} */
  public void updateClob(int i, Reader reader, long l) throws SQLException {
    delegate.updateClob(i, reader, l);
  }

  /** {@inheritDoc} */
  public void updateClob(String s, Clob clob) throws SQLException {
    delegate.updateClob(s, clob);
  }

  /** {@inheritDoc} */
  public void updateClob(String s, Reader reader) throws SQLException {
    delegate.updateClob(s, reader);
  }

  /** {@inheritDoc} */
  public void updateClob(String s, Reader reader, long l) throws SQLException {
    delegate.updateClob(s, reader, l);
  }

  /** {@inheritDoc} */
  public void updateDate(int i, Date date) throws SQLException {
    delegate.updateDate(i, date);
  }

  /** {@inheritDoc} */
  public void updateDate(String s, Date date) throws SQLException {
    delegate.updateDate(s, date);
  }

  /** {@inheritDoc} */
  public void updateDouble(int i, double v) throws SQLException {
    delegate.updateDouble(i, v);
  }

  /** {@inheritDoc} */
  public void updateDouble(String s, double v) throws SQLException {
    delegate.updateDouble(s, v);
  }

  /** {@inheritDoc} */
  public void updateFloat(int i, float v) throws SQLException {
    delegate.updateFloat(i, v);
  }

  /** {@inheritDoc} */
  public void updateFloat(String s, float v) throws SQLException {
    delegate.updateFloat(s, v);
  }

  /** {@inheritDoc} */
  public void updateInt(int i, int i1) throws SQLException {
    delegate.updateInt(i, i1);
  }

  /** {@inheritDoc} */
  public void updateInt(String s, int i) throws SQLException {
    delegate.updateInt(s, i);
  }

  /** {@inheritDoc} */
  public void updateLong(int i, long l) throws SQLException {
    delegate.updateLong(i, l);
  }

  /** {@inheritDoc} */
  public void updateLong(String s, long l) throws SQLException {
    delegate.updateLong(s, l);
  }

  /** {@inheritDoc} */
  public void updateNCharacterStream(int i, Reader reader) throws SQLException {
    delegate.updateNCharacterStream(i, reader);
  }

  /** {@inheritDoc} */
  public void updateNCharacterStream(int i, Reader reader, long l) throws SQLException {
    delegate.updateNCharacterStream(i, reader, l);
  }

  /** {@inheritDoc} */
  public void updateNCharacterStream(String s, Reader reader) throws SQLException {
    delegate.updateNCharacterStream(s, reader);
  }

  /** {@inheritDoc} */
  public void updateNCharacterStream(String s, Reader reader, long l) throws SQLException {
    delegate.updateNCharacterStream(s, reader, l);
  }

  /** {@inheritDoc} */
  public void updateNClob(int i, NClob nClob) throws SQLException {
    delegate.updateNClob(i, nClob);
  }

  /** {@inheritDoc} */
  public void updateNClob(int i, Reader reader) throws SQLException {
    delegate.updateNClob(i, reader);
  }

  /** {@inheritDoc} */
  public void updateNClob(int i, Reader reader, long l) throws SQLException {
    delegate.updateNClob(i, reader, l);
  }

  /** {@inheritDoc} */
  public void updateNClob(String s, NClob nClob) throws SQLException {
    delegate.updateNClob(s, nClob);
  }

  /** {@inheritDoc} */
  public void updateNClob(String s, Reader reader) throws SQLException {
    delegate.updateNClob(s, reader);
  }

  /** {@inheritDoc} */
  public void updateNClob(String s, Reader reader, long l) throws SQLException {
    delegate.updateNClob(s, reader, l);
  }

  /** {@inheritDoc} */
  public void updateNString(int i, String s) throws SQLException {
    delegate.updateNString(i, s);
  }

  /** {@inheritDoc} */
  public void updateNString(String s, String s1) throws SQLException {
    delegate.updateNString(s, s1);
  }

  /** {@inheritDoc} */
  public void updateNull(int i) throws SQLException {
    delegate.updateNull(i);
  }

  /** {@inheritDoc} */
  public void updateNull(String s) throws SQLException {
    delegate.updateNull(s);
  }

  /** {@inheritDoc} */
  public void updateObject(int i, Object o) throws SQLException {
    delegate.updateObject(i, o);
  }

  /** {@inheritDoc} */
  public void updateObject(int i, Object o, int i1) throws SQLException {
    delegate.updateObject(i, o, i1);
  }

  /** {@inheritDoc} */
  public void updateObject(String s, Object o) throws SQLException {
    delegate.updateObject(s, o);
  }

  /** {@inheritDoc} */
  public void updateObject(String s, Object o, int i) throws SQLException {
    delegate.updateObject(s, o, i);
  }

  /** {@inheritDoc} */
  public void updateRef(int i, Ref ref) throws SQLException {
    delegate.updateRef(i, ref);
  }

  /** {@inheritDoc} */
  public void updateRef(String s, Ref ref) throws SQLException {
    delegate.updateRef(s, ref);
  }

  /** {@inheritDoc} */
  public void updateRow() throws SQLException {
    delegate.updateRow();
  }

  /** {@inheritDoc} */
  public void updateRowId(int i, RowId rowId) throws SQLException {
    delegate.updateRowId(i, rowId);
  }

  /** {@inheritDoc} */
  public void updateRowId(String s, RowId rowId) throws SQLException {
    delegate.updateRowId(s, rowId);
  }

  /** {@inheritDoc} */
  public void updateShort(int i, short s) throws SQLException {
    delegate.updateShort(i, s);
  }

  /** {@inheritDoc} */
  public void updateShort(String s, short i) throws SQLException {
    delegate.updateShort(s, i);
  }

  /** {@inheritDoc} */
  public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {
    delegate.updateSQLXML(i, sqlxml);
  }

  /** {@inheritDoc} */
  public void updateSQLXML(String s, SQLXML sqlxml) throws SQLException {
    delegate.updateSQLXML(s, sqlxml);
  }

  /** {@inheritDoc} */
  public void updateString(int i, String s) throws SQLException {
    delegate.updateString(i, s);
  }

  /** {@inheritDoc} */
  public void updateString(String s, String s1) throws SQLException {
    delegate.updateString(s, s1);
  }

  /** {@inheritDoc} */
  public void updateTime(int i, Time time) throws SQLException {
    delegate.updateTime(i, time);
  }

  /** {@inheritDoc} */
  public void updateTime(String s, Time time) throws SQLException {
    delegate.updateTime(s, time);
  }

  /** {@inheritDoc} */
  public void updateTimestamp(int i, Timestamp timestamp) throws SQLException {
    delegate.updateTimestamp(i, timestamp);
  }

  /** {@inheritDoc} */
  public void updateTimestamp(String s, Timestamp timestamp) throws SQLException {
    delegate.updateTimestamp(s, timestamp);
  }

  /** {@inheritDoc} */
  public boolean wasNull() throws SQLException {
    return delegate.wasNull();
  }

  /** {@inheritDoc} */
  public boolean isWrapperFor(Class<?> aClass) throws SQLException {
    return delegate.isWrapperFor(aClass);
  }

  /** {@inheritDoc} */
  public <T> T unwrap(Class<T> tClass) throws SQLException {
    return delegate.unwrap(tClass);
  }

  private void startClock() {
    start = System.currentTimeMillis();
  }

  private void stopClock() {
    long stop = System.currentTimeMillis();
    long dur = stop - start;
    if (THRESHOLD > 0L && dur > THRESHOLD) {
    	log.warn("Slow SQLRs next " + (statement.getName()==null?"":statement.getName()+" ") + dur + " ms "
        + statement.getQueryString());
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
    String msg = s.getMessage() + " " + (se==null?"":se.getMessage()) + " " + tm
      + "[" + statement.getQueryString() + "]";
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
}
