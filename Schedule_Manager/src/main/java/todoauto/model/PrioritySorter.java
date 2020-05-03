package todoauto.model;

import java.util.Comparator;

/**
 * PrioritySorter is an object that can compare two Todos by priority
 */
public class PrioritySorter implements Comparator<Todo> {


  /**
   * compare two Todos by priority
   * @param o1 first Todo
   * @param o2 second Todo
   * @return an int
   */
  @Override
  public int compare(Todo o1, Todo o2) {
//    if (o1.getPriority() == null) {
//      return -1;
//    }
//    if (o2.getPriority() == null) {
//      return 1;
//    }
    return -o1.getPriority().compareTo(o2.getPriority());
  }
}
