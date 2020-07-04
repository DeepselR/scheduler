package exception;

/**
 * This exception is thrown by {@link service.impl.Scheduler} to indicate that list of tasks has cyclic dependence.
 */
public class CyclicDependenceException extends RuntimeException {

  /**
   * Error message.
   */
  private static final String MESSAGE = "Cyclic dependence detected.";

  /**
   * Constructor.
   */
  public CyclicDependenceException() {
    super(MESSAGE);
  }

}
