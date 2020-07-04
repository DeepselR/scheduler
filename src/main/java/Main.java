import java.util.Arrays;
import java.util.List;
import service.IScheduler;
import service.impl.Scheduler;
import task.Task;

public class Main {

  /**
   * The following is the example of how the scheduler may be used.
   */
  public static void main(String[] args) {

    List<Task> tasks = Arrays.asList(
        new Task("E", Arrays.asList("B")),
        new Task("D", Arrays.asList("A", "B")),
        new Task("A", Arrays.asList()),
        new Task("B", Arrays.asList("A")),
        new Task("C", Arrays.asList("D", "B")),
        new Task("F", Arrays.asList("E"))
    );

    IScheduler scheduler = new Scheduler();
    long start = System.currentTimeMillis();
    List<Task> sortedTasks = scheduler.schedule(tasks);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    for (Task t : sortedTasks) {
      System.out.println(t.getName());
    }
  }

}