package service.impl;

import exception.CyclicDependenceException;
import exception.TaskNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import service.IScheduler;
import task.Task;

/**
 * Implementation of service {@link IScheduler}.
 */
public class Scheduler implements IScheduler {

  @Override
  public List<Task> schedule(List<Task> tasks) {
    return sort(tasks);
  }

  /**
   * Sort list of tasks {@param tasks} according to their dependencies.
   *
   * @param tasks list of tasks
   * @return sorted list
   */
  private List<Task> sort(List<Task> tasks) {
    Set<String> pending = new LinkedHashSet<>();
    Set<String> visited = new LinkedHashSet<>();
    Stack<Task> order = new Stack<>();
    for (Task task : tasks) {
      if (!visited.contains(task.getName())) {
        visit(task, tasks, pending, visited, order);
      }
    }
    return new ArrayList<>(order);
  }

  /**
   * Method recursively visits task dependencies to determine the ordinal placement.
   *
   * @param task    pending task
   * @param tasks   list of tasks
   * @param pending set of pending tasks
   * @param visited set of visited tasks
   * @param order   stack for storing order
   */
  private void visit(Task task, List<Task> tasks, Set<String> pending, Set<String> visited, Stack<Task> order) {
    if (!pending.contains(task.getName())) {
      pending.add(task.getName());
    } else {
      throw new CyclicDependenceException();
    }
    for (String name : task.getPredecessors()) {
      if (!visited.contains(name)) {
        visit(getTaskByName(name, tasks), tasks, pending, visited, order);
      }
    }
    visited.add(task.getName());
    pending.remove(task.getName());
    order.push(task);
  }

  /**
   * Method returns {@link Task} by name.
   *
   * @param name  task name
   * @param tasks list of tasks
   * @return {@link Task}
   */
  private Task getTaskByName(String name, List<Task> tasks) {
    for (Task task : tasks) {
      if (name.equals(task.getName())) {
        return task;
      }
    }
    throw new TaskNotFoundException(name);
  }
}
