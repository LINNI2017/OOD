import static org.junit.Assert.*;

import Exceptions.InvalidBoundsException;
import Exceptions.NegativeLengthException;
import org.junit.Before;
import org.junit.Test;

public class ValidatorNumberTest {
  private Validator<String> number;
  private Validator<String> number2;
  private Validator<String> number3;
  private Validator<String> number4;
  private Validator<String> number5;

  @Before
  public void setUp() throws Exception {
    number = new ValidatorNumber(2, 10, 3);
    number2 = new ValidatorNumber(2, 10, 3);
    number3 = new ValidatorNumber(1, 10, 3);
    number4 = new ValidatorNumber(2, 11, 3);
    number5 = new ValidatorNumber(2, 10, 4);

  }

  @Test(expected = InvalidBoundsException.class)
  public void testInvalidBoundException()
      throws InvalidBoundsException, NegativeLengthException {
    number = new ValidatorNumber(3, 1, 3);
  }

  @Test(expected = NegativeLengthException.class)
  public void testNegativeLengthException()
      throws InvalidBoundsException, NegativeLengthException {
    number = new ValidatorNumber(1, 3, -3);
  }

  @Test
  public void isValid() {
    assertTrue(number.isValid("2.3"));
    assertTrue(number.isValid("2.33"));
    assertTrue(number.isValid("2.333"));
  }

  @Test
  public void invalidDecimalLength() {
    assertFalse(number.isValid("2.3333"));
  }

  @Test
  public void invalidSmaller() {
    assertFalse(number.isValid("1.3"));
  }

  @Test
  public void invalidLarger() {
    assertFalse(number.isValid("11.3"));
  }

  @Test
  public void validInteger() {
    assertTrue(number.isValid("2"));
    assertTrue(number.isValid("10"));
    assertTrue(number.isValid("2."));
    assertTrue(number.isValid("10."));
  }

  @Test
  public void invalidWithSymbol() {
    assertFalse(number.isValid("$2.3"));
    assertFalse(number.isValid("2$.3"));
    assertFalse(number.isValid("2.$3"));
    assertFalse(number.isValid("2.3$"));
    assertFalse(number.isValid("2.3."));
  }

  @Test
  public void testEquals() {
    assertTrue(number.equals(number));
    assertTrue(number.equals(number2));
  }

  @Test
  public void testNotEquals() {
    assertFalse(number.equals(number3));
    assertFalse(number.equals(number4));
    assertFalse(number.equals(number5));
    assertFalse(number.equals(null));
    assertFalse(number.equals(12));
  }

  @Test
  public void testEqualHashCode() {
    assertTrue(number.hashCode() == number.hashCode());
    assertTrue(number.hashCode() == number2.hashCode());
  }

  @Test
  public void testNotEqualHashCode() {
    assertTrue(number.hashCode() != number3.hashCode());
    assertTrue(number.hashCode() != number4.hashCode());
    assertTrue(number.hashCode() != number5.hashCode());
  }

  @Test
  public void testToString() {
    assertTrue(number.toString().equals(
        "ValidatorNumber{minValue=2, maxValue=10, maxDecimalLength=3}"));
  }
}