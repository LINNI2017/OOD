/**
 * This class represents an exception to throw
 * if the seat name is invalid.
 */
public class InvalidSeatNameException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.  The cause is
   * not initialized, and may subsequently be initialized by a call to {@link
   * #initCause}.
   *
   * @param message the detail message. The detail message is saved for later
   *                retrieval by the {@link #getMessage()} method.
   */
  public InvalidSeatNameException(String message) {
    super(message);
  }
}
