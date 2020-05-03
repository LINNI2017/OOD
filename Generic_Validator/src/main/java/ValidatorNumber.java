import Exceptions.InvalidBoundsException;
import Exceptions.NegativeLengthException;
import java.util.Objects;

/**
 * ValidatorNumber is a Validator used for String input of numeric fields. It determines whether or
 * not the text entered by a user can be converted to the appropriate numeric format.
 */
public class ValidatorNumber extends Validator<String> {

  protected int minValue;
  protected int maxValue;
  protected int maxDecimalLength;

  /**
   * Constructs a ValidatorNumber with specified minValue, maxValue and maxDecimalLength.
   * @param minValue an Integer value representing the minimum value
   * @param maxValue an Integer value representing the maximum value
   * @param maxDecimalLength an Integer value representing the max length of digits allowed
   * @throws NegativeLengthException if the maxDecimalLength is negative, throw NegativeLengthException
   * @throws InvalidBoundsException if the minValue is bigger than the maxValue, throw InvalidBoundsException
   */
  public ValidatorNumber(int minValue, int maxValue, int maxDecimalLength)
      throws NegativeLengthException, InvalidBoundsException {
    validBounds(minValue, maxValue);
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.maxDecimalLength = nonNegativeLength(maxDecimalLength);
  }

  /**
   * Determines whether the input is valid or not, according to specific rules.
   *
   * @param input the provided input of Type String
   * @return true or false
   */
  @Override
  public boolean isValid(String input) {
    int digit = countDigit(input);
    int dotIndex = input.indexOf('.');
    int decimalDigit = 0;
    if (dotIndex >= 0) {
      decimalDigit = countDigit(input.substring(dotIndex + 1));
      digit++;
    }
    return digit == input.length() &&
        decimalDigit <= this.maxDecimalLength &&
        Double.parseDouble(input) >= this.minValue &&
        Double.parseDouble(input) <= this.maxValue;
  }

  /**
   * Checks whether two ValidatorNumber are equal.
   *
   * @param o the object to check with
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    ValidatorNumber that = (ValidatorNumber) o;
    return this.minValue == that.minValue &&
        this.maxValue == that.maxValue &&
        this.maxDecimalLength == that.maxDecimalLength;
  }

  /**
   * Return the hashcode for the ValidatorNumber.
   *
   * @return an Integer value representing the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.minValue, this.maxValue, this.maxDecimalLength);
  }

  /**
   * Return a String representation of the ValidatorNumber.
   *
   * @return a String representing the ValidatorNumber
   */
  @Override
  public String toString() {
    return "ValidatorNumber{" +
        "minValue=" + this.minValue +
        ", maxValue=" + this.maxValue +
        ", maxDecimalLength=" + this.maxDecimalLength +
        '}';
  }
}
