import Exceptions.NegativeLengthException;
import java.util.Objects;

/**
 * ValidatorText is a Validator used for String input. It checks whether the input is no longer than
 * the number of lines multiplied by the number of characters allowed per line.
 */
public class ValidatorText extends Validator<String> {

  protected int numLine;
  protected int lineLength;


  /**
   * Constructs a ValidatorText with specified numLine and lineLength.
   *
   * @param numLine    an Integer value representing the line counts
   * @param lineLength an Integer value representing the length of each line
   * @throws NegativeLengthException if the input lineLength is negative, throw NegativeLengthException
   */
  public ValidatorText(int numLine, int lineLength)
      throws NegativeLengthException {
    this.numLine = nonNegativeLength(numLine);
    this.lineLength = nonNegativeLength(lineLength);
  }

  /**
   * Returns true if the input is valid according to specific rules, otherwise return false.
   *
   * @param input the provided input of Type String
   * @return true or false
   */
  @Override
  public boolean isValid(String input) {
    return input.length() <= (this.numLine * this.lineLength);
  }

  /**
   * Return a String representation of the ValidatorText.
   *
   * @return a String representing the ValidatorText
   */
  @Override
  public String toString() {
    return "ValidatorText{" +
        "numLine=" + this.numLine +
        ", lineLength=" + this.lineLength +
        '}';
  }

  /**
   * Checks whether two ValidatorText are equal.
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
    ValidatorText that = (ValidatorText) o;
    return this.numLine == that.numLine &&
        this.lineLength == that.lineLength;
  }

  /**
   * Return the hashcode for the ValidatorText.
   *
   * @return an Integer value representing the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.numLine, this.lineLength);
  }
}
