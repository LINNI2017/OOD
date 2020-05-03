import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import Exceptions.InvalidBoundsException;
import Exceptions.NegativeLengthException;

/**
 * Validator is an object that stores requirements for user input and provides a method, ​boolean
 * isValid(input)​, that determines if the provided input meets the requirements.
 *
 * @param <T> validator of Type T
 */
public abstract class Validator<T> implements IValidator<T> {

  /**
   * Constructs a Validator.
   */
  public Validator() {
  }

  /**
   * Counts the number of lowercase letter in the input string.
   *
   * @param input a String input
   * @return an Integer value representing the number of lowercase letter in the input String
   */
  protected int countLower(String input) {
    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      if (isLowerCase(input.charAt(i))) {
        count++;
      }
    }
    return count;
  }

  /**
   * Counts the number of uppercase letter in the input string.
   *
   * @param input a String input
   * @return an Integer value representing the number of uppercase letter in the input String
   */
  protected int countUpper(String input) {
    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      if (isUpperCase(input.charAt(i))) {
        count++;
      }
    }
    return count;
  }

  /**
   * Counts the number of digit in the input string.
   *
   * @param input a String input
   * @return an Integer value representing the number of digit in the input String
   */
  protected int countDigit(String input) {
    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      if (isDigit(input.charAt(i))) {
        count++;
      }
    }
    return count;
  }

  /**
   * Checks whether the input minValue and maxValue are legal. If not, throw
   * InvalidBoundsException.
   *
   * @param minValue an Integer value representing the minValue
   * @param maxValue an Integer value representing the maxValue
   * @throws InvalidBoundsException a defined Exception registering the corresponding error
   */
  protected void validBounds(int minValue, int maxValue)
      throws InvalidBoundsException {
    if (minValue > maxValue) {
      throw new InvalidBoundsException(
          "The given minimum and maximum bounds are invalid.");
    }
  }

  /**
   * Checks whether the given length is legal. If not, throw NegativeLengthException.
   *
   * @param value an Integer value representing the length
   * @return if value of length is legal, return it
   * @throws NegativeLengthException a defined Exception registering the corresponding error
   */
  protected int nonNegativeLength(int value) throws NegativeLengthException {
    if (value < 0) {
      throw new NegativeLengthException("The given length is negative.");
    }
    return value;
  }
}
