package todoauto.controller;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * CommandLineParser can deal with user input command line arguments and set correct info into
 * a map which can be used later.
 */
public class CommandLineParser {
  public static final String CSV_FILE = "csv-file";
  public static final String ADD_TODO = "add-todo";
  public static final String TODO_TEXT = "todo-text";
  public static final String COMPLETED = "completed";
  public static final String DUE = "due";
  public static final String PRIORITY = "priority";
  public static final String CATEGORY = "category";
  public static final String COMPLETE_TODO = "complete-todo";
  public static final String DISPLAY = "display";
  public static final String SHOW_INCOMPLETE = "show-incomplete";
  public static final String SHOW_CATEGORY = "show-category";
  public static final String SORT_BY_DATE = "sort-by-date";
  public static final String SORT_BY_PRIORITY = "sort-by-priority";
  public static final String ABSOLUTE_PATH = System.getProperty("user.dir");
  public static final String ARG_PREFIX = "--";

  /**
   * Constructor to construct a CommandLineParser
   */
  public CommandLineParser() {
  }

   /**
    * Method to process command line arguments
    * @param args an array of String representing command line arguments
    * @return a map, with input flag as keys, and the following input value as value
    * @throws InvalidArgumentsException when argument length is 0
    */
  public static Map<String, String> process(String[] args)
      throws InvalidArgumentsException {

    Map<String, String> argMap = new TreeMap<>();
    checkEmpty((args.length));
    String pre = null;
    for (String s : args) {
      if (s.startsWith(ARG_PREFIX)) {  // arg option
        s = s.replace(ARG_PREFIX, "");
        if (!argMap.containsKey(s)) {
          argMap.put(s, null);
        } else if (s.equals(TODO_TEXT)) {
          throw new InvalidArgumentsException("Only support for adding one new todo.");
        }
        pre = s;
      } else {  // arg value
        checkNullOption(argMap, pre);
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
    checkNullValue(argMap, CSV_FILE);
    if (argMap.containsKey(ADD_TODO)) {
      checkNullOption(argMap, TODO_TEXT);
      checkNullValue(argMap, TODO_TEXT);
    }
    checkNullValue(argMap, DUE);
    if (argMap.containsKey(PRIORITY)) {
      checkNullValue(argMap, PRIORITY);
    }
    checkNullValue(argMap, CATEGORY);
    if (argMap.containsKey(COMPLETE_TODO)) {
      checkNullValue(argMap, COMPLETE_TODO);
    }
    if (argMap.containsKey(DISPLAY)) {
      checkNullValue(argMap, SHOW_CATEGORY);
      if (argMap.containsKey(SORT_BY_DATE) &&
          argMap.containsKey(SORT_BY_PRIORITY)) {
        System.out.println("Cannot sort by multiple fields, "
            + "but only sort by date is applied");
      }
    }
    return argMap;
  }

  /**
   * Method to check if parameter equals to 0
   * @param length - an int
   * @throws InvalidArgumentsException when the length of argument equals to 0
   */
  private static void checkEmpty(int length) throws InvalidArgumentsException {
    if (length == 0) {
      throw new InvalidArgumentsException(
          "At least one option must be provided.");
    }
  }

   /**
    * Check whether provides correct flag
    * @param argMap  - the map of flag-info pairs
    * @param type - the flag
    * @throws InvalidArgumentsException when no info provided
    */
  private static void checkNullOption(Map<String, String> argMap,
      String type) throws InvalidArgumentsException {
    String typeValue = type;
    if (typeValue == null) {
      typeValue = "an";
    }
    if (type == null || !argMap.containsKey(type)) {
      String error = String.format("Missing %s option", typeValue);
      throw new InvalidArgumentsException(error);
    }
  }

   /**
    * Check whether provides info for corresponding flag
    * @param argMap  - the map of flag-info pairs
    * @param type - the flag
    * @throws InvalidArgumentsException when no info provided
    */
  private static void checkNullValue(Map<String, String> argMap,
      String type)
      throws InvalidArgumentsException {
    if (argMap.containsKey(type) && argMap.get(type) == null) {
      String error = String.format("Missing %s value", type);
      throw new InvalidArgumentsException(error);
    }
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
