package todoauto.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import todoauto.model.DateSorter;
import todoauto.model.PrioritySorter;
import todoauto.model.Todo;

/**
 * CSVProcessor can process the info received from CommandLineParser,
 * read and write the CSV file, and display it in terminal
 */
public class CSVProcessor {

  private static final int TO_DO_COLUMN_NUM = 6;
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
  public static final int PRIORITY_MIN = 1;
  public static final int PRIORITY_MAX = 3;
  public static final String NA = "?";
  public static final String[] DEFAULT_HEADER_LIST
      = new String[]{"id", "text", "completed", "due", "priority", "category"};

  /**
   * Constructor of CSVProcessor
   */
  protected CSVProcessor() {
  }

  /**
   * Method to process the received info, read, write and display the csvfile to terminal
   * @param argMap - a flag-value pair
   * @throws ParseException when date format is incorrect
   */
  public static void process(Map<String, String> argMap) throws ParseException {
    List<Todo> todoList = new ArrayList<>();
    List<String> headerList = new ArrayList<>();
    // program stored data initialize
    if (readTodo(argMap, todoList, headerList)) {
      // program stored data append
      addTodo(argMap, todoList, headerList);
      //  program stored data rewrite
      if (completeTodo(argMap, todoList)) {
        // file stored data rewrite
        if (writeTodo(argMap, todoList, headerList)) {
          // program stored data print
          showTodo(argMap, todoList);
        }
      }
    }
  }

  /**
   * Method to read existed CSVfile, render the info into a Todolist
   * @param argMap - a flag-value pair
   * @param todoList - a list of Todos
   * @param headerList - a list of file header
   */
  protected static boolean readTodo(Map<String, String> argMap,
      List<Todo> todoList, List<String> headerList) {
    String filename = argMap.get(CSV_FILE);
    try (BufferedReader inputFile =
        new BufferedReader(new FileReader(filename))) {

      // set up header list
      String line = inputFile.readLine();
      for (String s : splitColumn(line)) {
        headerList.add(s);
      }

      while ((line = inputFile.readLine()) != null) {
        String[] infoList = splitColumn(line);
        Todo newTodo = new Todo(headerList, infoList);
        todoList.add(newTodo);
      }
      return true;
    } catch (IOException | ParseException ioe) {
      System.out.println("Usage Error: Something went wrong in opening/reading: "
          + ioe.getMessage());
      ioe.printStackTrace();
      return false;
    }
  }

  /**
   * Method to add a new Todo object into the Todolist
   * @param argMap - a flag-value pair
   * @param todoList - a list of Todos
   * @param headerList - a list of file header
   * @throws ParseException when date format is incorrect
   */
  private static void addTodo(Map<String, String> argMap,
      List<Todo> todoList, List<String> headerList)
      throws ParseException {
    if (argMap.containsKey(ADD_TODO)) {
      String[] infoList = new String[]{
          // index starts at 0, but id starts at 1
          Integer.toString(todoList.size() + 1),
          argMap.get(TODO_TEXT),
          argMap.containsKey(COMPLETED) ? Boolean.toString(true) : NA,
          argMap.getOrDefault(DUE, NA),
          argMap.getOrDefault(PRIORITY, NA),
          argMap.getOrDefault(CATEGORY, NA)
      };
      Todo newTodo = new Todo(headerList, infoList);
      todoList.add(newTodo);
    }
  }

  /**
   * Method to make the specified Todos (can be more than one) as completed
   * @param argMap - a flag-value pair
   * @param todoList - a list of Todos
   * @throws ParseException when date format is incorrect
   */
  protected static boolean completeTodo(Map<String, String> argMap, List<Todo> todoList)
      throws ParseException {
    if (argMap.containsKey(COMPLETE_TODO)) {
      String[] completeList = argMap.get(COMPLETE_TODO).split(",");
      for (String s : completeList) {
        // due to offset between array index and id
        // array index starts at 0, id starts at 1
        int id = Integer.parseInt(s) - 1;
        if (id >= 0 && id < todoList.size()) {
          todoList.set(id, todoList.get(id).complete());
        } else {
          System.out.println("Usage Error: Invalid ID for complete todo.");
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Method to write a list of（newly constructed）Todos to the csv file
   * @param argMap - a flag-value pair
   * @param todoList - a list of Todos
   * @param headerList - a list of file header
   */
  protected static boolean writeTodo(Map<String, String> argMap,
      List<Todo> todoList, List<String> headerList) {
    if (argMap.containsKey(ADD_TODO) || argMap.containsKey(COMPLETE_TODO)) {
      try (BufferedWriter outputFile =
          new BufferedWriter(new FileWriter(argMap.get(CSV_FILE)))) {
        // write the header
        outputFile.write(listToCSV(headerList) + System.lineSeparator());
        // write the value
        for (Todo t : todoList) {
          outputFile.write(t.toString() + System.lineSeparator());
        }
      } catch (IOException ioe) {
        System.out.println("Usage Error: Something went wrong in opening/writing: "
            + ioe.getMessage());
        ioe.printStackTrace();
        return false;
      }
    }
    return true;
  }

  /**
   * Method to display a list of Todos to terminal
   * @param argMap - a flag-value pair
   * @param todoList - a list of Todos
   */
  protected static void showTodo(Map<String, String> argMap, List<Todo> todoList) {
    if (argMap.containsKey(DISPLAY)) {
      List<Todo> display = new ArrayList<>();
      display = todoList;
      if (argMap.containsKey(SHOW_CATEGORY)) {
        getListOfCategory(display, argMap.get(SHOW_CATEGORY));
      }
      if (display.isEmpty()) {
        System.out.println("Usage Error: Empty result due to non-exist category.");
      }
      if (argMap.containsKey(SHOW_INCOMPLETE)) {
        getIncomplete(display);
      }
      if (argMap.containsKey(SORT_BY_DATE)) {
        display.sort(new DateSorter());
      } else if (argMap.containsKey(SORT_BY_PRIORITY)) {
        display.sort(new PrioritySorter());
      }
      printTodoList(display);
    }
  }

  /**
   * Method to get a list of Todos in the given category
   * @param display - a list of todos to be displayed
   * @param category - a string represents the required category
   */
  private static void getListOfCategory(List<Todo> display, String category) {
    int idx = 0;
    while (idx < display.size()) {
      if (!display.get(idx).getValue(CATEGORY).equals(category)) {
        display.remove(idx);
      } else {
        idx++;
      }
    }
  }

  /**
   * Method to get a list of incomplete Todos.
   * @param display - the list of Todos to be displayed
   */
  private static void getIncomplete(List<Todo> display) {
    int idx = 0;
    while (idx < display.size()) {
      if (display.get(idx).getCompleted()) {
        display.remove(idx);
      } else {
        idx++;
      }
    }
  }

  /**
   * Method to print the list of Todos to terminal
   * @param list the list to be printed
   */
  private static void printTodoList(List<Todo> list) {
    for (Object o : list) {
      System.out.println(o.toString());
    }
  }

  /**
   * Method to split a line of String
   * @param line the String to be split
   * @return an array of String containing the split result
   */
  private static String[] splitColumn(String line) {
    String procLine = line;
    if (procLine.startsWith("\"")) {
      procLine = procLine.substring(1);
    }
    if (procLine.endsWith("\"")) {
      procLine = procLine.substring(0, procLine.length() - 1);
    }
    String[] info;
    if (!procLine.contains("\"")) {
      info = procLine.split(",");
    } else {
      info = procLine.split(",\"");
    }
    for (int i = 0; i < info.length; i++) {
      info[i] = info[i].replaceAll("\"", "");
    }
    return info;
  }

  /**
   * Method to get a string of a list of info concatenated with comma
   * @param list list of info to be wrote
   * @return a string to be wrote into the csv file
   */
  private static String listToCSV(List<String> list) {
    String result = "";
    for (String s : list) {
      result += String.format("\"%s\",", s);
    }
    result = result.substring(0, result.length() - 1);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    return o != null && this.getClass() == o.getClass();
  }

  @Override
  public int hashCode() {
    return 41;
  }

  @Override
  public String toString() {
    return "CSVProcessor";
  }
}
