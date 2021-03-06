package todoauto.controller;

import static org.junit.Assert.*;

import com.sun.tools.jdeprscan.CSV;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import todoauto.model.Todo;

public class CSVProcessorTest {
  private CSVProcessor csvp;
  private String[] args;
  private Map<String, String> argMap;
  private String tempFolderPath;
  private List<String> headerList;
  private String[] infoList;
  private List<Todo> todoList;

  @Before
  public void setUp() throws Exception {
    csvp = new CSVProcessor();
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    tempFolderPath = tempFolder.getRoot().getPath();
    headerList = Arrays.asList("id", "text", "completed", "due", "priority", "category");
    infoList = new String[] {"9","testing!!!!!","true","02/02/2020","1","school"};
    todoList = new ArrayList<>();
    todoList.add(new Todo(headerList, infoList));
  }

  @Test
  public void completeTODOInvalidIdx() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "0",
        "--complete-todo", "9999",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODOWithoutCate() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void showIncomplete() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--show-incomplete",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODONoText() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODONoDue() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void showCategoryWithIncomplete() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--due", "02/02/2020",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--show-incomplete",
        "--show-category", "grocery",
        "--sort-by-priority"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void showCategory() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--due", "02/02/2020",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--show-category", "school"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void sortByPriority() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--due", "02/02/2020",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-priority"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void noWrite() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--display"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void noSort() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--due", "02/02/2020",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--category", "school"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void noDisplay() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--due", "02/02/2020",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODOHasDue() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--due", "02/02/2020",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODONoPriority() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODONoCompleted() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void catchReadException() throws ParseException {
    args = new String[] {
        "--csv-file", tempFolderPath + File.separator + "impossible.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void catchWriteException() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
    argMap.replace("csv-file", "impossible" + File.separator + "impossible.csv");
    CSVProcessor.writeTodo(argMap, todoList, headerList);
  }

  @Test
  public void readFileNoQuote() {
    args = new String[] {
        "--csv-file", "input/todos_no_quote.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    todoList = new ArrayList<>();
    headerList = new ArrayList<>();
    argMap = CommandLineParser.process(args);
    CSVProcessor.readTodo(argMap, todoList, headerList);
    argMap.replace("csv-file", tempFolderPath + File.separator + "todos_no_quote.csv");
    CSVProcessor.writeTodo(argMap, todoList, headerList);
  }

  @Test
  public void readFileMissCol() {
    args = new String[] {
        "--csv-file", "input/todos_miss_col.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    todoList = new ArrayList<>();
    headerList = new ArrayList<>();
    argMap = CommandLineParser.process(args);
    CSVProcessor.readTodo(argMap, todoList, headerList);
    argMap.replace("csv-file", tempFolderPath + File.separator + "todos_miss_quote.csv");
    CSVProcessor.writeTodo(argMap, todoList, headerList);
  }

  @Test
  public void addTODOWithFull() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "due", "?",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--category", "school",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void addTODOWithNullText() throws ParseException {
    args = new String[] {
        "--csv-file", "input/todos.csv",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }

  @Test
  public void process() throws ParseException {
    CSVProcessor.process(argMap);
  }

  @Test
  public void invalidIdComplete() throws ParseException {
    CSVProcessor.completeTodo(argMap, Arrays.asList(
        new Todo(headerList, new String[]{"-1", "text", "completed", "due", "priority", "category"})));
  }

  @Test
  public void invalidIdComplete2() throws ParseException {
    CSVProcessor.completeTodo(argMap, Arrays.asList(
        new Todo(headerList, new String[]{"1000", "text", "completed", "due", "priority", "category"})));
  }

  @Test
  public void emptyResultShow() {
    CSVProcessor.showTodo(argMap, new ArrayList<>());
  }

  @Test
  public void testEquals() {
    assertTrue(csvp.equals(csvp));
    assertFalse(csvp.equals(null));
    assertFalse(csvp.equals(12));
  }

  @Test
  public void testHashCode() {
    assertEquals(41, csvp.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CSVProcessor", csvp.toString());
  }
}