package todoauto.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommandLineParserTest {
  private CommandLineParser clp;
  private String[] args1;

  @Before
  public void setUp() throws Exception {
    clp = new CommandLineParser();
    args1 = new String[] {
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
  }

  @Test
  public void testEquals() {
    assertTrue(clp.equals(clp));
    assertFalse(clp.equals(null));
    assertFalse(clp.equals(12));
  }

  @Test
  public void testHashCode() {
    assertEquals(37, clp.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CommandLineParser", clp.toString());
  }

  @Test
  public void checkNullOption() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void nullArg() {
    args1 = new String[] {null, null, null};
    CommandLineParser.process(args1);
  }

  @Test
  public void checkNullValue() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void addMultiTODO() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--todo-text", "testing!!!!!",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingOption() {
    args1 = new String[] {
        "input/todos.csv",
        "--add-todo",
        "--completed",
        "--todo-text", "testing!!!!!",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingCSV() {
    args1 = new String[] {
        "--csv-file",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--display",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingDue() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--display",
        "--due",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingPriority() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority",
        "--category", "school",
        "--complete-todo", "1",
        "--display",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingCategory() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category",
        "--complete-todo", "1",
        "--display",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingShowCategory() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--display",
        "--show-category",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void missingCompleteTodoId() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo",
        "--display",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void sortByPriority() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--display",
        "--sort-by-priority"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void completeOne() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--display",
        "--sort-by-date"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void noAdd() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--completed",
        "--complete-todo", "1"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void noComplete() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void addMultiSort() {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--add-todo",
        "--todo-text", "testing!!!!!",
        "--completed",
        "--priority", "1",
        "--category", "school",
        "--complete-todo", "1",
        "--complete-todo", "2",
        "--display",
        "--sort-by-date",
        "--sort-by-priority",
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void process() {
    CommandLineParser.process(args1);
  }

}