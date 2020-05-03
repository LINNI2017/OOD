import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidatorTextTest {
  private Validator<String> text;
  private Validator<String> text2;
  private Validator<String> text3;
  private Validator<String> text4;

  @Before
  public void setUp() throws Exception {
    text = new ValidatorText(2, 2);
    text2 = new ValidatorText(2, 2);
    text3 = new ValidatorText(3, 2);
    text4 = new ValidatorText(2, 3);
  }

  @Test
  public void isNotValid() {
    assertFalse(text.isValid("hello"));
  }

  @Test
  public void isValid() {
    assertTrue(text.isValid(""));
    assertTrue(text.isValid("h"));
    assertTrue(text.isValid("he"));
    assertTrue(text.isValid("hel"));
    assertTrue(text.isValid("hell"));
  }

  @Test
  public void testToString() {
    assertTrue(text.toString().equals("ValidatorText{numLine=2, lineLength=2}"));
  }

  @Test
  public void testEquals() {
    assertTrue(text.equals(text));
    assertTrue(text.equals(text2));
  }

  @Test
  public void testNotEquals() {
    assertFalse(text.equals(null));
    assertFalse(text.equals(12));
    assertFalse(text.equals(text3));
    assertFalse(text.equals(text4));
  }

  @Test
  public void testEqualHashCode() {
    assertTrue(text.hashCode() == text.hashCode());
    assertTrue(text.hashCode() == text2.hashCode());
  }

  @Test
  public void testNotEqualHashCode() {
    assertTrue(text.hashCode() != text3.hashCode());
    assertTrue(text.hashCode() != text4.hashCode());
  }
}