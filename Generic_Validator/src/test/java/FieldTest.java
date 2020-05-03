import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import Exceptions.InvalidValueException;
import org.junit.Before;
import org.junit.Test;

public class FieldTest {
  private Field<String> text;
  private Field<String> textA;
  private Field<String> textB;
  private Field<String> textC;
  private Field<String> textD;
  private Field<String> textE;
  private Field<String> textF;
  private Field<Boolean> radio;

  private Field<Boolean> radio2;
  private Field<String> text3;
  private Field<String> number;
  private Field<Boolean> check;
  private Field<String> phone;
  private Field<String> password;
  private Field<String> password2;
  private Field<String> password3;

  @Before
  public void setUp() throws Exception {
    text = new Field<>("Comment", "Hello", true, new ValidatorText(2, 3));
    textA = new Field<>("Comment", "Hello", true, new ValidatorText(2, 3));
    textB = new Field<>("Comment", "Hellooooo", false, new ValidatorText(2,3));
    textC = new Field<>("Comment", "Hello", false, new ValidatorText(2, 3));
    textD = new Field<>("Comment", "Hello", true, new ValidatorText(2, 4));
    textE = new Field<>("Comment", "Helloooo", true, new ValidatorText(2, 3));
    textF = new Field<>("Commentt", "Hello", true, new ValidatorText(2, 3));

    radio = new Field<>("Correct", true, true, new ValidatorRadio());
    radio2 = new Field<>("Correct", null, false, new ValidatorRadio());
    text3 = new Field<>("Comment", "Hellooooo", true, new ValidatorText(2,3));
    number = new Field<>("Price", "2.2", true, new ValidatorNumber(1, 4, 2));
    check = new Field<>("Apple", null, true, new ValidatorCheck());
    phone = new Field<>("Phone", "12345", true, new ValidatorPhone(5));
    password = new Field<>("Password", "ab123A", true, new ValidatorPassword(2, 7, 2, 1, 2));
    password2 = new Field<>("Password", "aaaaaa", true, new ValidatorPassword(2, 7, 2, 1, 2));
    password3 = new Field<>("Password", "ab123A", false, new ValidatorPassword(2, 7, 2, 1, 2));
  }

  @Test(expected = InvalidValueException.class)
  public void testInvalidValueException() throws InvalidValueException {
    text.updateValue("Hellooooo");
  }

  @Test
  public void updateValue() throws InvalidValueException {
    text.updateValue("Hi");
    assertEquals("Hi", text.getValue());
  }

  @Test
  public void isFilled() {
    assertTrue(text.isFilled());
    assertTrue(radio.isFilled());
    assertTrue(textB.isFilled());
    assertTrue(radio2.isFilled());
    assertTrue(number.isFilled());
    assertTrue(check.isFilled());
    assertTrue(phone.isFilled());
    assertTrue(password.isFilled());
    assertTrue(password3.isFilled());
  }

  @Test
  public void invalidFilled() {
    assertFalse(text3.isFilled());
    assertFalse(password2.isFilled());
  }

  @Test
  public void testEquals() {
    assertTrue(text.equals(text));
    assertTrue(text.equals(textA));
  }

  @Test
  public void testNotEquals() {
    assertFalse(text.equals(null));
    assertFalse(text.equals(12));
    assertFalse(text.equals(textB));
    assertFalse(text.equals(textC));
    assertFalse(text.equals(textD));
    assertFalse(text.equals(textE));
    assertFalse(text.equals(textF));
  }

  @Test
  public void testHashCode() {
    assertTrue(text.hashCode() == text.hashCode());
    assertTrue(text.hashCode() == textA.hashCode());
    assertTrue(text.hashCode() != textB.hashCode());
  }

  @Test
  public void testToString() {
    assertTrue(text.toString().equals("Field{label='Comment', value=Hello, required=true, validator=ValidatorText{numLine=2, lineLength=3}}"));
  }
}