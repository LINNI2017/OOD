import java.util.Objects;


/**
 * ValidatorPhone is a Validator used for String input. It checks whether the input contains only
 * digits and has a specified length supplied by client code. The length must match exactly.
 */
public class ValidatorPhone extends Validator<String> {

  protected int exactLength;

  /**
   * Constructs a ValidatorPhone with specified length.
   *
   * @param exactLength an Integer value representing the required length
   */
  public ValidatorPhone(int exactLength) {
    this.exactLength = exactLength;
  }

  /**
   * Determines whether the input is valid or not, according to specific rules.
   *
   * @param input the provided input of Type String
   * @return true or false
   */
  @Override
  public boolean isValid(String input) {
    return countDigit(input) == this.exactLength &&
        input.length() == this.exactLength;
  }

  /**
   * Checks whether two ValidatorPhone are equal.
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
    ValidatorPhone that = (ValidatorPhone) o;
    return this.exactLength == that.exactLength;
  }

  /**
   * Return the hashcode for the ValidatorPhone.
   *
   * @return an Integer value representing the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.exactLength);
  }

  /**
   * Return a String representation of the ValidatorPhone.
   *
   * @return a String representing the ValidatorPhone
   */
  @Override
  public String toString() {
    return "ValidatorPhone{" +
        "exactLength=" + this.exactLength +
        '}';
  }
}
