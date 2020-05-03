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

  @Test(expected = InvalidArgumentsException.class)
  public void checkEmpty() throws InvalidArgumentsException {
    args1 = new String[] {};
    CommandLineParser.process(args1);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void checkNullOption() throws InvalidArgumentsException {
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

  @Test(expected = InvalidArgumentsException.class)
  public void checkNullValue() throws InvalidArgumentsException {
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

  @Test(expected = InvalidArgumentsException.class)
  public void addMultiTODO() throws InvalidArgumentsException {
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

  @Test(expected = InvalidArgumentsException.class)
  public void missingOption() throws InvalidArgumentsException {
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
  public void sortByPriority() throws InvalidArgumentsException {
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
  public void completeOne() throws InvalidArgumentsException {
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
  public void noAdd() throws InvalidArgumentsException {
    args1 = new String[] {
        "--csv-file", "input/todos.csv",
        "--completed",
        "--complete-todo", "1"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void noComplete() throws InvalidArgumentsException {
    args1 = new String[] {
        "--csv-file", "input/todos.csv"
    };
    CommandLineParser.process(args1);
  }

  @Test
  public void addMultiSort() throws InvalidArgumentsException {
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
  public void process() throws InvalidArgumentsException {
    CommandLineParser.process(args1);
  }

}