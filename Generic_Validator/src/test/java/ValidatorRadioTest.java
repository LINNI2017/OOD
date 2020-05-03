import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidatorRadioTest {
  private Validator<Boolean> radio;
  private Validator<Boolean> radio2;

  @Before
  public void setUp() throws Exception {
    radio = new ValidatorRadio();
    radio2 = new ValidatorRadio();
  }

  @Test
  public void isValid() {
    assertTrue(radio.isValid(true));
  }

  @Test
  public void falseIsValid() {
    assertTrue(radio.isValid(false));
  }

  @Test
  public void nullIsNotValid() {
    assertFalse(radio.isValid(null));
  }

  @Test
  public void testToString() {
    assertTrue(radio.toString().equals("ValidatorRadio{}"));
  }

  @Test
  public void testHashCode() {
    assertTrue(radio.hashCode() == radio.hashCode());
    assertTrue(radio.hashCode() == radio2.hashCode());
  }

  @Test
  public void testEquals() {
    assertTrue(radio.equals(radio));
    assertTrue(radio.equals(radio2));
  }

  @Test
  public void testNotEquals() {
    assertFalse(radio.equals(null));
    assertFalse(radio.equals(12));
  }
}