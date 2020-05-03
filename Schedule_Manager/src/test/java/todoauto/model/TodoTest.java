package todoauto.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
  private Todo t1;
  private Todo t2;
  private Todo t3;
  private Todo t4;
  private Todo t5;
  private Todo t6;
  private Todo t7;
  private String[] infoList1;
  private String[] infoList2;
  private String[] infoList3;
  private String[] infoList4;
  private String[] infoList6;
  private String[] infoList7;
  private List<String> headerList1;
  private List<String> headerList2;
  private List<String> headerList3;
  private List<String> headerList4;

  @Before
  public void setUp() throws Exception {
    headerList1 = Arrays.asList(new String[] {"id", "text", "completed", "due", "priority", "category"});
    headerList2 = Arrays.asList(new String[] {"id", "text", "completed", "due", "priority", "participant"});
    headerList3 = Arrays.asList(new String[] {});
    headerList4 = Arrays.asList(new String[] {"A", "B", "C", "due", "completed", "priority"});
    infoList1 = new String[] {"9","testing!!!!!","true","02/02/2020","1","school"};
    infoList2 = new String[] {"8","testing!!!!!","true","?","3","john"};
    infoList3 = new String[] {};
    infoList4 = new String[] {"a", "b", "c", "02/02/2020", "true", "1"};
    infoList6 = new String[] {"9","testing!!!!!","blabla","02/02/2020","1","school"};
    infoList7 = new String[] {"9","testing!!!!!","true","blabla","-1","school"};
    t1 = new Todo(headerList1, infoList1);
    t2 = new Todo(headerList2, infoList2);
    t3 = new Todo(headerList3, infoList3);
    t4 = new Todo(headerList4, infoList4);
    t5 = new Todo(headerList1, infoList1);
    t6 = new Todo(headerList1, infoList6);
    t7 = new Todo(headerList1, infoList7);
  }

  @Test
  public void getValue() {
    assertEquals("9", t1.getValue("id"));
    assertEquals(null, t1.getValue("IMPOSSIBLE"));
  }

  @Test
  public void getHeaderList() {
    for (int i = 0; i < headerList1.size(); i++) {
      assertEquals(headerList1.get(i), t1.getHeaderList().get(i));
    }
  }

  @Test
  public void getPriority() {
    assertEquals(1, t4.getPriority(), 0);
  }

  @Test
  public void testEquals() {
    assertTrue(t1.equals(t1));
    assertTrue(t1.equals(t5));
    assertFalse(t1.equals(t2));
    assertFalse(t1.equals(t3));
    assertFalse(t1.equals(t4));
    assertFalse(t1.equals(t6));
    assertFalse(t1.equals(t7));
    assertFalse(t1.equals(null));
    assertFalse(t1.equals(12));
  }

  @Test
  public void testHashCode() {
    assertEquals(2029527369, t1.hashCode());
  }

  @Test
  public void compareTo() {
    assertTrue(t1.compareTo(t2) > 0);
  }

  @Test
  public void emptyString() {
    assertEquals("", t3.toString());
  }
}