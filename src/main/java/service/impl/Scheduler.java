package service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import service.IScheduler;
import task.Task;

public class Scheduler implements IScheduler {

  @Override
  public List<Task> schedule(List<Task> tasks) {
    return sort(tasks);
  }

  private List<Task> sort(List<Task> tasks) {
    Set<String> visited = new LinkedHashSet<>();
    Stack<Task> order = new Stack<>();
    for (Task task : tasks) {
      if (!visited.contains(task.getName())) {
        visit(task, tasks, visited, order);
      }
    }
    return new ArrayList<>(order);
  }

  private void visit(Task task, List<Task> tasks, Set<String> visited, Stack<Task> order) {
    visited.add(task.getName());
    for (String name : task.getPredecessors()) {
      if (!visited.contains(name)) {
        visit(getTaskByName(name, tasks), tasks, visited, order);
      }
    }
    order.push(task);
  }

  private Task getTaskByName(String name, List<Task> tasks) {
    Task desiredTask = tasks.stream()
        .filter(task -> name.equals(task.getName()))
        .findFirst()
        .orElse(null);
    if (Objects.isNull(desiredTask)) {
      throw new RuntimeException("The task with name " + name + " was not found.");
    }
    return desiredTask;
  }
}
