package com.hqsolution.hqserver.app.exception;

import java.sql.SQLException;

/**
 * Represents a duplicate key database exception
 */
public class DuplicateKeyException extends SQLException {
  private static final long serialVersionUID = 1L;

  /**
   * Constructs an <code>SQLException</code> object;
   * the <code>reason</code> field defaults to null,
   * the <code>SQLState</code> field defaults to <code>null</code>, and
   * the <code>vendorCode</code> field defaults to 0.
   */
  public DuplicateKeyException() {
    super();
  }

  /**
   * Constructs an <code>SQLException</code> object with a reason;
   * the <code>SQLState</code> field defaults to <code>null</code>, and
   * the <code>vendorCode</code> field defaults to 0.
   *
   * @param reason a description of the exception
   */
  public DuplicateKeyException(String reason) {
    super(reason);
  }

  /**
   * Constructs an <code>SQLException</code> object with the given reason and
   * SQLState; the <code>vendorCode</code> field defaults to 0.
   *
   * @param reason   a description of the exception
   * @param sqlState an XOPEN or SQL 99 code identifying the exception
   */
  public DuplicateKeyException(String reason, String sqlState) {
    super(reason, sqlState);
  }

  /**
   * Constructs a fully-specified <code>SQLException</code> object.
   *
   * @param reason     a description of the exception
   * @param sqlState   an XOPEN or SQL 99 code identifying the exception
   * @param vendorCode a database vendor-specific exception code
   */
  public DuplicateKeyException(String reason, String sqlState, int vendorCode) {
    super(reason, sqlState, vendorCode);
  }
}
