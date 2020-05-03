import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ValidatorCheckTest {
  private Validator<Boolean> check;
  private Validator<Boolean> check2;

  @Before
  public void setUp() throws Exception {
    check = new ValidatorCheck();
    check2 = new ValidatorCheck();
  }

  @Test
  public void isValid() {
    assertTrue(check.isValid(true));
  }

  @Test
  public void nullIsValid() {
    assertTrue(check.isValid(null));
  }

  @Test
  public void falseIsValid() {
    assertTrue(check.isValid(false));
  }

  @Test
  public void testToString() {
    assertTrue(check.toString().equals("ValidatorCheck{}"));
  }

  @Test
  public void testHashCode() {
    assertTrue(check.hashCode() == check.hashCode());
    assertTrue(check.hashCode() == check2.hashCode());
  }

  @Test
  public void testEquals() {
    assertTrue(check.equals(check));
    assertTrue(check.equals(check2));
  }

  @Test
  public void testNotEquals() {
    assertFalse(check.equals(null));
    assertFalse(check.equals(12));
  }
}
