package service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import service.IScheduler;
import task.Task;

public class SchedulerTests {

  private final IScheduler scheduler = new Scheduler();

  @Test
  void mainTestScheduler() {
    List<Task> tasks = Arrays.asList(
        new Task("E", Arrays.asList("B")),
        new Task("D", Arrays.asList("A", "B")),
        new Task("A", Arrays.asList()),
        new Task("B", Arrays.asList("A")),
        new Task("C", Arrays.asList("D", "B")),
        new Task("F", Arrays.asList("E"))
    );

    List<Task> sortedTasks = scheduler.schedule(tasks);
    String sortedTaskNames = sortedTasks.stream().map(Task::getName).collect(Collectors.joining(","));
    assertEquals("A,B,E,D,C,F", sortedTaskNames);
  }

  @Test
  void testScheduler() {
    List<Task> tasks = Arrays.asList(
        new Task("2", Arrays.asList("11")),
        new Task("3", Arrays.asList()),
        new Task("5", Arrays.asList()),
        new Task("7", Arrays.asList()),
        new Task("8", Arrays.asList("3", "7")),
        new Task("9", Arrays.asList("8", "11")),
        new Task("10", Arrays.asList("3", "11")),
        new Task("11", Arrays.asList("5", "7"))
    );
    List<Task> sortedTasks = scheduler.schedule(tasks);
    String sortedTaskNames = sortedTasks.stream().map(Task::getName).collect(Collectors.joining(","));
    assertEquals("5,7,11,2,3,8,9,10", sortedTaskNames);
  }

}
