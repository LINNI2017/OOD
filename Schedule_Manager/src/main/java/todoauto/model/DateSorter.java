package todoauto.model;

import java.text.ParseException;
import java.util.Comparator;

/**
 * DateSorter is an object that can compare two Todos by date
 */
public class DateSorter implements Comparator<Todo> {


  /**
   * compare two Todos by date
   * @param o1 first Todo
   * @param o2 second Todo
   * @return an int
   */
  @Override
  public int compare(Todo o1, Todo o2) {
    try {
      // (date = null) > (date != null)
      if (o1.getDue() == null && o2.getDue() == null) {
        return 0;
      }
      if (o1.getDue() == null) {
        return 1;
      }
      if (o2.getDue() == null) {
        return -1;
      }
      return o1.getDue().compareTo(o2.getDue());
    } catch (ParseException e) {
      e.printStackTrace();
      // put all incorrect format in the bottom, as sort ascendingly
      return 1;
    }
  }
}
