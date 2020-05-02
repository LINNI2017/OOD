import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReservationsServiceTest {
  private ReservationsService rse;
  private Theater t;

  @Before
  public void setUp() throws Exception {
    rse = new ReservationsService();
    t = new Theater("Roxy", 15, 10, 1);
  }

  @Test
  public void checkAvaRow() {
    assertEquals(ReservationsService.checkAvaRow(t, 5, false), 8, 0);
  }

  @Test
  public void getSeatMap() {
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
        + "15 _ _ _ _ _ _ _ _ _ _\n",
        ReservationsService.getSeatMap(t));
  }

  @Test
  public void addWheelRow() throws FailToBuildTheaterException {
    ReservationsService.addWheelRow(t, 2);
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
        + "15 _ _ _ _ _ _ _ _ _ _\n",
    ReservationsService.getSeatMap(t));
  }

  @Test
  public void testEquals() {
    assertTrue(rse.equals(new ReservationsService()));
    assertFalse(rse.equals(null));
    assertFalse(rse.equals(12));
  }

  @Test
  public void testHashCode() {
    assertEquals(41, rse.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("ReservationsService", rse.toString());
  }
}