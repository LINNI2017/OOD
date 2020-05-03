package todoauto.controller;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * CommandLineParser can deal with user input command line arguments and set correct info into
 * a map which can be used later.
 */
public class CommandLineParser {
  public static final Integer MIN_ARG = 2;
  public static final String CSV_FILE = "csv-file";
  public static final String ADD_TODO = "add-todo";
  public static final String TODO_TEXT = "todo-text";
  public static final String DUE = "due";
  public static final String PRIORITY = "priority";
  public static final String CATEGORY = "category";
  public static final String COMPLETE_TODO = "complete-todo";
  public static final String DISPLAY = "display";
  public static final String SHOW_CATEGORY = "show-category";
  public static final String SORT_BY_DATE = "sort-by-date";
  public static final String SORT_BY_PRIORITY = "sort-by-priority";
  public static final String ABSOLUTE_PATH = System.getProperty("user.dir");
  public static final String ARG_PREFIX = "--";

  /**
   * Constructor to construct a CommandLineParser
   */
  protected CommandLineParser() {
  }

  private static boolean multiTodo(String s) {
    boolean multi = s.equals(TODO_TEXT);
    if (multi) {
      System.out.println("Usage Error: Only support for adding one new todo.");
    }
    return multi;
  }

  private static boolean multiSort(Map<String, String> argMap) {
    boolean multi = argMap.containsKey(SORT_BY_DATE) &&
                    argMap.containsKey(SORT_BY_PRIORITY);
    if (multi) {
      System.out.println("Usage Error: Cannot sort by multiple fields, "
          + "but only sort by date is applied.");
    }
    return multi;
  }

   /**
    * Method to process command line arguments
    * @param args an array of String representing command line arguments
    * @return a map, with input flag as keys, and the following input value as value
    */
  public static Map<String, String> process(String[] args) {

    Map<String, String> argMap = new TreeMap<>();
    if (args == null || invalidLength(args.length)) {
      return null;
    }
    String pre = null;
    for (String s : args) {
      if (s == null) {
        continue;
      }
      if (s.startsWith(ARG_PREFIX)) {  // arg option
        s = s.replace(ARG_PREFIX, "");
        if (!argMap.containsKey(s)) {
          argMap.put(s, null);
        } else if (multiTodo(s)) {
          return null;
        }
        pre = s;
      } else {  // arg value
        if (checkNullOption(argMap, pre)) {
          return null;
        }
        if (pre.equals(CSV_FILE)) {
          s = ABSOLUTE_PATH + File.separator + s;
        } else if (pre.equals(COMPLETE_TODO)) {
          if (argMap.get(COMPLETE_TODO) != null) {
            s = argMap.get(COMPLETE_TODO) + "," + s;
          }
        }
        argMap.put(pre, s);
      }
    }
    if (checkNullValue(argMap, CSV_FILE)) {
      return null;
    }
    if (argMap.containsKey(ADD_TODO) && (checkNullOption(argMap, TODO_TEXT) ||
        checkNullValue(argMap, TODO_TEXT))) {
        return null;
    }
    if (checkNullValue(argMap, DUE)) {
      return null;
    }
    if (argMap.containsKey(PRIORITY) &&
        checkNullValue(argMap, PRIORITY)) {
        return null;
    }
    if (checkNullValue(argMap, CATEGORY)) {
      return null;
    }
    if (argMap.containsKey(COMPLETE_TODO) &&
        checkNullValue(argMap, COMPLETE_TODO)) {
        return null;
    }
    if (argMap.containsKey(DISPLAY)) {
      if (checkNullValue(argMap, SHOW_CATEGORY)) {
        return null;
      }
      if (multiSort(argMap)) {
        return null;
      }
    }
    return argMap;
  }

  /**
   * Method to check if the number of arguments is smaller than minimum
   * threshold of number of arguments.
   * @param length - an integer represents the number of arguments
   */
  private static boolean invalidLength(int length) {
    boolean invalid = (length < MIN_ARG);
    if (invalid) {
      System.out.println(
          "Usage Error: At least one option and a value must be provided.");
    }
    return invalid;
  }

   /**
    * Check whether provides correct flag
    * @param argMap  - the map of flag-info pairs
    * @param type - the flag
    */
  private static boolean checkNullOption(Map<String, String> argMap,
      String type) {
    String typeValue = type;
    if (typeValue == null) {
      typeValue = "an";
    }
    boolean isNull = (type == null || !argMap.containsKey(type));
    if (isNull) {
      System.out.println(
          String.format("Usage Error: Missing %s option.", typeValue));
    }
    return isNull;
  }

   /**
    * Check whether provides info for corresponding flag
    * @param argMap  - the map of flag-info pairs
    * @param type - the flag
    */
  private static boolean checkNullValue(Map<String, String> argMap, String type) {
    boolean isNull = argMap.containsKey(type) && argMap.get(type) == null;
    if (isNull) {
      System.out.println(String.format("Usage Error: Missing %s value.", type));
    }
    return isNull;
  }

  @Override
  public boolean equals(Object o) {
    return o != null && this.getClass() == o.getClass();
  }

  @Override
  public int hashCode() {
    return 37;
  }

  @Override
  public String toString() {
    return "CommandLineParser";
  }
}
