package todoauto.view;

import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import todoauto.controller.InvalidArgumentsException;

public class MainTest {
  private String[] args;
  private Main m;

  @Before
  public void setUp() throws Exception {
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
    m = new Main();
  }

  @Test
  public void main() throws ParseException, InvalidArgumentsException {
    Main.main(args);
  }
}