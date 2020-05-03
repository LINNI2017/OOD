package todoauto.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Todo is an object representing sth to do, with specified infomation.
 */
public class Todo implements Comparable<Todo> {

  // {
  //   "id": "1",
  //   "text": "Finish HW9",
  //   "completed": "false",
  //   "due": "3/22/2020",
  //   "priority": "1",
  //   "category": "school"
  // }
  private Map<String, String> todoMap;
  // ["id", "text", "completed", "due", "priority", "category"]
  private List<String> headerList;

  public static final String ID = "id";
  public static final String NA = "?";
  public static final String DUE = "due";
  public static final String PRIORITY = "priority";
  public static final String COMPLETED = "completed";
  public static final int PRIORITY_MIN = 1;
  public static final int PRIORITY_MAX = 3;
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("M/d/yyyy");

  /**
   * Constructs a Todo object
   * @param headerList - a list of file header
   * @param infoList - a string array of information of the todo
   * @throws ParseException when date format is incorrect
   */
  public Todo(List<String> headerList, String[] infoList) throws ParseException {
    this.headerList = copyList(headerList);
    this.todoMap = new HashMap<>();
    processInfo(infoList);
    validateInfo();
  }

  /**
   * return a copy of the list
   * @param lst original list
   * @return list copy
   */
  private List<String> copyList(List<String> lst) {
    List<String> copy = new ArrayList<>();
    for (String s : lst) {
      copy.add(s);
    }
    return copy;
  }

  /**
   * check whether the date info is of correct format
   */
  private void validateDate() {
    if (!this.todoMap.containsKey(DUE)) {
      this.todoMap.put(DUE, NA);
    } else if (!this.todoMap.get(DUE).equals(NA)) {
      String due = this.todoMap.get(DUE);
      Pattern p = Pattern.compile("\\d*\\/\\d*\\/\\d*");
      Matcher m = p.matcher(due);
      if (!m.find()) {
        this.todoMap.replace(DUE, NA);
      }
    }
  }

  /**
   * check whether the priority info is correct
   */
  private void validatePriority() {
    // x -> 3
    // ? -> 3
    // 5 -> 3
    // -1 -> 3
    if (!this.todoMap.containsKey(PRIORITY) ||
        (this.todoMap.get(PRIORITY)
            .compareTo(Integer.toString(PRIORITY_MAX)) > 0 ||
            this.todoMap.get(PRIORITY)
                .compareTo(Integer.toString(PRIORITY_MIN)) < 0)) {
      this.todoMap.put(PRIORITY, Integer.toString(PRIORITY_MAX));
    }
  }

  /**
   * check whether the completed info is correct
   */
  private void validateCompleted() {
    // ? -> false
    // true -> true
    // false -> false
    // xxx -> false
    if (!this.todoMap.containsKey(COMPLETED) ||
        this.todoMap.get(COMPLETED).equals(NA) ||
        (!this.todoMap.get(COMPLETED).equals(Boolean.toString(true)) &&
            !this.todoMap.get(COMPLETED).equals(Boolean.toString(false)))) {
      this.todoMap.put(COMPLETED, Boolean.toString(false));
    }
  }

  /**
   * check whether the info given is correct
   */
  private void validateInfo() {
    validateDate();
    validatePriority();
    validateCompleted();
  }

  /**
   * put the info into the underlying map of todo
   * @param infoList - string array of info
   */
  private void processInfo(String[] infoList) {
    for (int i = 0; i < headerList.size(); i++) {
      // key: header, value: info
      if (i < infoList.length) {
        todoMap.put(headerList.get(i), infoList[i]);
      } else {  // for missing col
        todoMap.put(headerList.get(i), NA);
      }

    }
  }

  /**
   * return the value of specified type(key)
   * @param type whose corresponding value we want
   * @return the value of the given type
   */
  public String getValue(String type) {
    if (this.todoMap.containsKey(type)) {
      return this.todoMap.get(type);
    } else {
      return null;
    }
  }

  /**
   * Set the specific todo status to be completed
   * @return a newly status-set todo object
   * @throws ParseException when date format is incorrect
   */
  public Todo complete() throws ParseException {
    String[] infoList = new String[headerList.size()];
    for (int i = 0; i < infoList.length; i++) {
      infoList[i] = todoMap.get(headerList.get(i));
      if (headerList.get(i).equals(COMPLETED)) {
        infoList[i] = Boolean.toString(true);
      }
    }
    return new Todo(copyList(this.headerList), infoList);
  }

  /**
   * return a copy of the headerlist
   * @return a copy of the headerlist
   */
  public List<String> getHeaderList() {
    return copyList(this.headerList);
  }

  /**
   * return the completed status as a boolean
   * @return a boolean
   */
  public boolean getCompleted() {
    return Boolean.parseBoolean(this.getValue(COMPLETED));
  }

  /**
   * @return the due date as a Date objcet
   * @throws ParseException when date format is incorrect
   */
  public Date getDue() throws ParseException {
    if (this.getValue(DUE).equals(NA)) {
      return null;
    }
    return DATE_FORMAT.parse(String.valueOf(this.getValue(DUE)));
  }

  /**
   * return the priority as an Integer
   * @return an Integer
   */
  public Integer getPriority() {
    return Integer.valueOf(this.getValue(PRIORITY));
  }

  @Override
  public String toString() {
    String result = "";
    if (this.headerList.isEmpty()) {
      return result;
    }
    for (String s : this.headerList) {
      result += String.format("\"%s\",", this.todoMap.get(s));
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Todo other = (Todo) o;
    if (this.todoMap.keySet().size() != other.todoMap.keySet().size()) {
      return false;
    }
    for (String s : this.todoMap.keySet()) {
      if (!other.todoMap.containsKey(s)) {
        return false;
      }
      if (this.todoMap.get(s) != other.todoMap.get(s)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.todoMap);
  }


  /**
   * the Todo element will be compared as to there ID
   * @param o - another Todo to compared with
   * @return an int
   */
  @Override
  public int compareTo(Todo o) {
    return this.getValue(ID).compareTo(o.getValue(ID));
  }
}
