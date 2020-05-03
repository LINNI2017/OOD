package todoauto.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DateSorterTest {
  private Todo t1;
  private Todo t2;
  private String[] infoList1;
  private String[] infoList2;
  private List<String> headerList1;

  @Before
  public void setUp() throws Exception {
    headerList1 = Arrays.asList(new String[] {"id", "text", "completed", "due", "priority", "category"});
    infoList1 = new String[] {"9","testing!!!!!","true","aa/02/2020/0202","1","school"};
    infoList2 = new String[] {"9","testing!!!!!","true","02/03/2020","1","school"};
    t1 = new Todo(headerList1, infoList1);
    t2 = new Todo(headerList1, infoList2);
  }

  @Test
  public void compare() throws ParseException {
    assertTrue(new DateSorter().compare(t1, t2) > 0);
  }
}