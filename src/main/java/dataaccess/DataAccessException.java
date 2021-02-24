package dataaccess;

/**
 * Exception class to handle what is thrown by the data access classes
 */
public class DataAccessException extends Exception {
  /**
   * Message to take when an exception is thrown
   * @param message
   */
  DataAccessException(String message)
  {
    super(message);
  }

  DataAccessException()
  {
    super();
  }
}
