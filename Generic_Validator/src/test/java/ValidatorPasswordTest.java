import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidatorPasswordTest {
  private Validator<String> password1;
  private Validator<String> password1a;
  private Validator<String> password1b;
  private Validator<String> password2;
  private Validator<String> password2a;
  private Validator<String> password3;
  private Validator<String> password3a;
  private Validator<String> password4;
  private Validator<String> password4a;
  private Validator<String> password4b;
  private Validator<String> password4c;

  @Before
  public void setUp() throws Exception {
    password1 = new ValidatorPassword(1, 2);
    password1a = new ValidatorPassword(2, 2);
    password1b = new ValidatorPassword(1, 1);
    password2 = new ValidatorPassword(1, 4, 2);
    password2a = new ValidatorPassword(1, 4, 3);
    password3 = new ValidatorPassword(1, 4, 1, 2);
    password3a = new ValidatorPassword(1, 4, 1, 1);
    password4 = new ValidatorPassword(1, 5, 1, 1, 2);
    password4a = new ValidatorPassword(1, 5, 1, 1, 1);
    password4b = new ValidatorPassword(1, 5, 1, 0, 2);
    password4c = new ValidatorPassword(1, 5, 1, 1, 2);
  }

  @Test
  public void isValid() {
    assertTrue(password1.isValid("a"));
    assertTrue(password1.isValid("aa"));
  }

  @Test
  public void validLowerLength() {
    assertTrue(password2.isValid("aa"));
    assertTrue(password2.isValid("aaaa"));
  }

  @Test
  public void invalidLowerLength() {
    assertFalse(password2.isValid("Aa"));
    assertFalse(password2.isValid("AAAA"));
  }

  @Test
  public void invalidLength() {
    assertFalse(password1.isValid(""));
    assertFalse(password1.isValid("aaa"));
  }

  @Test
  public void validUpperLength() {
    assertTrue(password3.isValid("AAa"));
    assertTrue(password3.isValid("AAAa"));
  }

  @Test
  public void invalidUpperLength() {
    assertFalse(password3.isValid("Aa"));
    assertFalse(password3.isValid("Aaa"));
  }

  @Test
  public void validDigitLength() {
    assertTrue(password4.isValid("Aa12"));
    assertTrue(password4.isValid("Aa123"));
  }

  @Test
  public void invalidDigitLength() {
    assertFalse(password4.isValid("Aa1"));
    assertFalse(password4.isValid("Aa"));
  }

  @Test
  public void invalidSpace() {
    assertFalse(password4.isValid("Aa1 2"));
    assertFalse(password4.isValid("Aa123 "));
  }

  @Test
  public void testEquals() {
    assertTrue(password1.equals(password1));
    assertTrue(password2.equals(password2));
    assertTrue(password3.equals(password3));
    assertTrue(password4.equals(password4));
    assertTrue(password4.equals(password4c));
  }

  @Test
  public void testNotEquals() {
    assertFalse(password1.equals(null));
    assertFalse(password1.equals(12));
    assertFalse(password1.equals(password1a));
    assertFalse(password1.equals(password1b));
    assertFalse(password2.equals(password2a));
    assertFalse(password3.equals(password3a));
    assertFalse(password4.equals(password4a));
    assertFalse(password4.equals(password4b));
  }

  @Test
  public void testEqualHashCode() {
    assertTrue(password1.hashCode() == password1.hashCode());
  }

  @Test
  public void testNotEqualHashCode() {
    assertTrue(password1.hashCode() != password2.hashCode());
    assertTrue(password1.hashCode() != password3.hashCode());
    assertTrue(password1.hashCode() != password4.hashCode());
  }

  @Test
  public void testToString() {
    assertTrue(password1.toString().equals(
        "ValidatorPassword{minLength=1, maxLength=2, minLowerLength=0, "
            + "minUpperLength=0, minDigitLength=0}"));
  }
}