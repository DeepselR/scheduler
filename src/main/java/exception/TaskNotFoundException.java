package exception;

/**
 * This exception is thrown by {@link service.impl.Scheduler} to indicate that scheduled task has a dependency which is
 * not in the original list.
 */
public class TaskNotFoundException extends RuntimeException {

  /**
   * Error message.
   */
  private static final String MESSAGE = "The task with name %s was not found.";

  /**
   * Constructor.
   *
   * @param taskName name of task
   */
  public TaskNotFoundException(String taskName) {
    super(String.format(MESSAGE, taskName));
  }
}
