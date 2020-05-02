import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeatTest {
  private Seat s1;

  @Before
  public void setUp() throws Exception {
    s1 = new Seat("A", false);
  }

  @Test
  public void setWheel() {
    assertFalse(s1.isWheel());
    s1.setWheel(true);
    assertTrue(s1.isWheel());
  }

  @Test
  public void getName() {
    assertEquals("A", s1.getName());
  }

  @Test
  public void getOccupant() {
    assertEquals(null, s1.getOccupant());
  }

  @Test
  public void setOccupant() {
    assertEquals(null, s1.getOccupant());
    s1.setOccupant("jennie");
    assertEquals("jennie", s1.getOccupant());
  }

  @Test
  public void setValidName() throws InvalidSeatNameException {
    assertEquals("A", s1.getName());
    s1.setName("Z");
    assertEquals("Z", s1.getName());
  }

  @Test(expected = InvalidSeatNameException.class)
  public void setInvalidName1() throws InvalidSeatNameException {
    s1.setName("ZZZ");
  }

  @Test(expected = InvalidSeatNameException.class)
  public void setInvalidName2() throws InvalidSeatNameException {
    s1.setName("1");
  }

  @Test(expected = InvalidSeatNameException.class)
  public void setInvalidName3() throws InvalidSeatNameException {
    s1.setName("a");
  }

  @Test(expected = InvalidSeatNameException.class)
  public void setInvalidName4() throws InvalidSeatNameException {
    s1.setName(".");
  }

  @Test
  public void testToString() throws InvalidSeatNameException {
    assertEquals("_", s1.toString());
    assertEquals("=", new Seat("A", true).toString());
    s1.setOccupant("jennie");
    assertEquals("X", s1.toString());
  }
}