import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReservationsSystemTest {
  ReservationsSystem rs;

  @Before
  public void setUp() throws Exception {
    rs = new ReservationsSystem();
  }

  @Test
  public void validYN() {
    assertTrue(ReservationsSystem.validYN("y"));
    assertFalse(ReservationsSystem.validYN("n"));
    assertEquals(null, ReservationsSystem.validYN("nooo"));
  }

  @Test
  public void negativeNumeric() {
    assertTrue(ReservationsSystem.negativeNumeric("-2"));
    assertFalse(ReservationsSystem.negativeNumeric("2"));
  }

  @Test
  public void getSeatNum() {
    assertEquals(ReservationsSystem.getSeatNum("reserve   7"), 7, 0);
    assertEquals(ReservationsSystem.getSeatNum("reserve  fdvd7"), 7, 0);
    assertEquals(ReservationsSystem.getSeatNum("reserve  fdvd"), 0, 0);
  }
}
