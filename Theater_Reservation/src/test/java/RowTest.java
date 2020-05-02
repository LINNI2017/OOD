import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

public class RowTest {
  private Row r1;
  private Row r2;

  @Before
  public void setUp() throws Exception {
    r1 = new Row(10, 1, true);
    r2 = new Row(11, 2, false);
  }

  @Test
  public void avaNum() {
    assertEquals(r1.avaNum(), 10, 0);
    r1.fillRow(5, "jennie");
    assertEquals(r1.avaNum(), 5, 0);
    r1.fillRow(5, "jennie");
    assertEquals(r1.avaNum(), 0, 0);
    r1.fillRow(5, "jennie");
    assertEquals(r1.avaNum(), 0, 0);
  }

  @Test
  public void addSeat() throws InvalidSeatNameException {
    assertEquals(r1.avaNum(), 10, 0);
    r1.addSeat(true);
    assertEquals(r1.avaNum(), 10, 0);
  }

  @Test
  public void setRow() {
    assertTrue(r1.isWheel());
    r1.setRow(false);
    assertFalse(r1.isWheel());
  }

  @Test
  public void testToString() throws InvalidSeatNameException {
    assertEquals("1  _ _ _ _ _ _ _ _ _ _\n", new Row(10, 0, false).toString());
    assertEquals("10 = = = = = = = = = =\n", new Row(10, 9, true).toString());
  }

  @Test
  public void testEquals() throws InvalidSeatNameException {
    // new Row(10, 1, false)
    assertTrue(r1.equals(r1));
    assertTrue(r1.equals(new Row(10, 1, true)));
    assertFalse(r1.equals(null));
    assertFalse(r1.equals(r2));
    assertFalse(r1.equals(12));
    assertFalse(r1.equals(new Row(10, 2, true)));
    assertFalse(r1.equals(new Row(11, 1, true)));
    assertFalse(r1.equals(new Row(10, 1, false)));
  }

  @Test
  public void testHashCode() {
    assertEquals(r1.hashCode(), r1.hashCode());
  }
}
