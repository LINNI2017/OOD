import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;

public class TheaterTest {
  private Theater t1;
  private Theater t2;

  @Before
  public void setUp() throws Exception {
    t1 = new Theater("Odd", 15, 10, 1);
    t2 = new Theater("Odd", 15, 10, 1);
  }

  @Test(expected = FailToBuildTheaterException.class)
  public void failToBuildTheaterException()
      throws FailToBuildTheaterException, InvalidSeatNameException {
    t1 = new Theater("invalid", 15, 10, 0);
    System.out.println(t1.toString());
  }

  @Test
  public void checkAvaIfFull()
      throws FailToBuildTheaterException, InvalidSeatNameException {
    t1 = new Theater("Full", 1, 10, 1);
    t1.fillRow(1, 10, "occupant");
    assertEquals(-1, t1.checkAvaRow(1, true), 0);
  }

  @Test
  public void checkWheelAvaIfNormalFull()
      throws FailToBuildTheaterException, InvalidSeatNameException {
    t1 = new Theater("Full", 1, 10, 1);
    assertEquals(1,t1.checkAvaRow(10, false), 0);
  }

  @Test
  public void addSecondWheelRow() throws FailToBuildTheaterException {
    t1.addWheelRow(2);
    assertEquals("1  = = = = = = = = = =\n"
        + "2  = = = = = = = = = =\n"
        + "3  _ _ _ _ _ _ _ _ _ _\n"
        + "4  _ _ _ _ _ _ _ _ _ _\n"
        + "5  _ _ _ _ _ _ _ _ _ _\n"
        + "6  _ _ _ _ _ _ _ _ _ _\n"
        + "7  _ _ _ _ _ _ _ _ _ _\n"
        + "8  _ _ _ _ _ _ _ _ _ _\n"
        + "9  _ _ _ _ _ _ _ _ _ _\n"
        + "10 _ _ _ _ _ _ _ _ _ _\n"
        + "11 _ _ _ _ _ _ _ _ _ _\n"
        + "12 _ _ _ _ _ _ _ _ _ _\n"
        + "13 _ _ _ _ _ _ _ _ _ _\n"
        + "14 _ _ _ _ _ _ _ _ _ _\n"
        + "15 _ _ _ _ _ _ _ _ _ _\n", t1.toString());
  }

  @Test(expected = FailToBuildTheaterException.class)
  public void addExistWheelRow() throws FailToBuildTheaterException {
    t1.addWheelRow(1);
  }

  @Test(expected = FailToBuildTheaterException.class)
  public void addNegWheelRow() throws FailToBuildTheaterException {
    t1.addWheelRow(-1);
  }

  @Test(expected = FailToBuildTheaterException.class)
  public void addZeroWheelRow() throws FailToBuildTheaterException {
    t1.addWheelRow(0);
  }

  @Test(expected = FailToBuildTheaterException.class)
  public void addExceedWheelRow() throws FailToBuildTheaterException {
    t1.addWheelRow(20);
  }

  @Test
  public void oneWheelRow() {
    assertEquals(1, t1.checkAvaRow(5, true), 0);
  }

  @Test
  public void multiWheelRow() throws FailToBuildTheaterException {
    t1.addWheelRow(2);
    t1.addWheelRow(3);
    assertEquals(3, t1.checkAvaRow(5, true), 0);
  }

  @Test
  public void multiWheelRowFilled() throws FailToBuildTheaterException {
    t1.addWheelRow(2);
    t1.addWheelRow(3);
    t1.fillRow(3, 10, "jennie");
    assertEquals(2, t1.checkAvaRow(5, true), 0);
  }

  @Test
  public void multiRowFilled() {
    t1.fillRow(8, 10, "jennie");
    assertEquals(7, t1.checkAvaRow(5, false), 0);
  }

  @Test
  public void fillRow() {
    t1.fillRow(2, 5, "jennie");
    assertEquals("1  = = = = = = = = = =\n"
       + "2  X X X X X _ _ _ _ _\n"
       + "3  _ _ _ _ _ _ _ _ _ _\n"
       + "4  _ _ _ _ _ _ _ _ _ _\n"
       + "5  _ _ _ _ _ _ _ _ _ _\n"
       + "6  _ _ _ _ _ _ _ _ _ _\n"
       + "7  _ _ _ _ _ _ _ _ _ _\n"
       + "8  _ _ _ _ _ _ _ _ _ _\n"
       + "9  _ _ _ _ _ _ _ _ _ _\n"
       + "10 _ _ _ _ _ _ _ _ _ _\n"
       + "11 _ _ _ _ _ _ _ _ _ _\n"
       + "12 _ _ _ _ _ _ _ _ _ _\n"
       + "13 _ _ _ _ _ _ _ _ _ _\n"
       + "14 _ _ _ _ _ _ _ _ _ _\n"
       + "15 _ _ _ _ _ _ _ _ _ _\n", t1.toString());
  }

  @Test
  public void fillInvalidRow() {
    t1.fillRow(-1, 5, "jennie");
    assertEquals("1  = = = = = = = = = =\n"
      + "2  _ _ _ _ _ _ _ _ _ _\n"
      + "3  _ _ _ _ _ _ _ _ _ _\n"
      + "4  _ _ _ _ _ _ _ _ _ _\n"
      + "5  _ _ _ _ _ _ _ _ _ _\n"
      + "6  _ _ _ _ _ _ _ _ _ _\n"
      + "7  _ _ _ _ _ _ _ _ _ _\n"
      + "8  _ _ _ _ _ _ _ _ _ _\n"
      + "9  _ _ _ _ _ _ _ _ _ _\n"
      + "10 _ _ _ _ _ _ _ _ _ _\n"
      + "11 _ _ _ _ _ _ _ _ _ _\n"
      + "12 _ _ _ _ _ _ _ _ _ _\n"
      + "13 _ _ _ _ _ _ _ _ _ _\n"
      + "14 _ _ _ _ _ _ _ _ _ _\n"
      + "15 _ _ _ _ _ _ _ _ _ _\n", t1.toString());
  }

  @Test
  public void testToString() {
    assertEquals("1  = = = = = = = = = =\n"
       + "2  _ _ _ _ _ _ _ _ _ _\n"
       + "3  _ _ _ _ _ _ _ _ _ _\n"
       + "4  _ _ _ _ _ _ _ _ _ _\n"
       + "5  _ _ _ _ _ _ _ _ _ _\n"
       + "6  _ _ _ _ _ _ _ _ _ _\n"
       + "7  _ _ _ _ _ _ _ _ _ _\n"
       + "8  _ _ _ _ _ _ _ _ _ _\n"
       + "9  _ _ _ _ _ _ _ _ _ _\n"
       + "10 _ _ _ _ _ _ _ _ _ _\n"
       + "11 _ _ _ _ _ _ _ _ _ _\n"
       + "12 _ _ _ _ _ _ _ _ _ _\n"
       + "13 _ _ _ _ _ _ _ _ _ _\n"
       + "14 _ _ _ _ _ _ _ _ _ _\n"
       + "15 _ _ _ _ _ _ _ _ _ _\n", t1.toString());
  }

  @Test
  public void testEquals()
      throws FailToBuildTheaterException, InvalidSeatNameException {
    assertTrue(t1.equals(t1));
    assertFalse(t1.equals(12));
    assertFalse(t1.equals(null));
    assertFalse(t1.equals(new Theater("Oddd", 15, 10, 1)));
    assertFalse(t1.equals(new Theater("Odd", 16, 10, 1)));
    assertFalse(t1.equals(new Theater("Odd", 15, 9, 1)));
    assertFalse(t1.equals(new Theater("Odd", 15, 10, 2)));
    assertTrue(t1.equals(t2));
    t2.addWheelRow(2);
    assertFalse(t1.equals(t2));
  }

  @Test
  public void testHashCode() {
    assertEquals(t1.hashCode(), t1.hashCode());
  }

  @Test
  public void getRowNum() {
    assertEquals(15, t1.getRowNum(), 0);
  }

  @Test
  public void getColNum() {
    assertEquals(10, t1.getColNum(), 0);
  }

  @Test
  public void getTable() throws InvalidSeatNameException {
    assertEquals(t1.getRowNum(), t1.getTable().size(), 0);
  }
}