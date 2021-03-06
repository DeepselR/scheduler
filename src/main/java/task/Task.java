package task;

import java.util.List;

/**
 * The task class represents a certain activities that must be done as the part of the project planning.
 */
public class Task {

  /**
   * Unique name of the activity.
   */
  private String name;

  /**
   * A list of names of the activitiest that must be compelte in order to be able to start the current activity.
   */
  private List<String> predecessors;

  public Task(String name, List<String> predecessors) {
    this.name = name;
    this.predecessors = predecessors;
  }

  public String getName() {
    return name;
  }

  public List<String> getPredecessors() {
    return predecessors;
  }
}