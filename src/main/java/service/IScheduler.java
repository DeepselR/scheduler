package service;

import java.util.List;
import task.Task;

/**
 * A scheduler interface is intended to process a random list of tasks with the information of their predecessors
 * and return a list of the same tasks but in order they may be executed according to their dependencies.
 */
public interface IScheduler {

   List<Task> schedule(List<Task> tasks);

}