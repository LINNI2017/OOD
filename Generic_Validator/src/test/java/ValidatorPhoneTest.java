import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidatorPhoneTest {
  private Validator<String> phone;
  private Validator<String> phone2;
  private Validator<String> phone3;

  @Before
  public void setUp() throws Exception {
    phone = new ValidatorPhone(5);
    phone2 = new ValidatorPhone(5);
    phone3 = new ValidatorPhone(6);
  }

  @Test
  public void isValid() {
    assertTrue(phone.isValid("12345"));
  }

  @Test
  public void invalidLength() {
    assertFalse(phone.isValid("1"));
    assertFalse(phone.isValid("12"));
    assertFalse(phone.isValid("123"));
    assertFalse(phone.isValid("1234"));
    assertFalse(phone.isValid("123456"));
  }

  @Test
  public void invalidLetter() {
    assertFalse(phone.isValid("aaaaa"));
  }

  @Test
  public void invalidMixed() {
    assertFalse(phone.isValid("aa123"));
    assertFalse(phone.isValid("12345aa"));
  }

  @Test
  public void testEquals() {
    assertTrue(phone.equals(phone));
    assertTrue(phone.equals(phone2));
  }

  @Test
  public void testNotEquals() {
    assertFalse(phone.equals(phone3));
    assertFalse(phone.equals(12));
    assertFalse(phone.equals(null));
  }

  @Test
  public void testEqualHashCode() {
    assertTrue(phone.hashCode() == phone.hashCode());
    assertTrue(phone.hashCode() == phone2.hashCode());
  }

  @Test
  public void testNotEqualHashCode() {
    assertTrue(phone.hashCode() != phone3.hashCode());
  }

  @Test
  public void testToString() {
    assertTrue(phone.toString().equals("ValidatorPhone{exactLength=5}"));
  }
}