import java.util.Objects;

/**
 * ValidatorPassword is a Validator used for String input. It checkes whether the input meets the
 * length requirements and contain at least the minimum number of each character type. Additionally,
 * a valid password cannot contain a space.
 */
public class ValidatorPassword extends Validator<String> {

  protected int minLength;
  protected int maxLength;
  protected int minLowerLength;
  protected int minUpperLength;
  protected int minDigitLength;

  /**
   * Constructs a ValidatorPassword with specified minLength, and maxLength.
   *
   * @param minLength an Integer value representing the minimum length
   * @param maxLength an Integer value representing the maximum length
   */
  public ValidatorPassword(int minLength, int maxLength) {
    this(minLength, maxLength, 0, 0, 0);
  }

  /**
   * Constructs a ValidatorPassword with specified minLength, maxLength, and minLowerLength.
   *
   * @param minLength      an Integer value representing the minimum length
   * @param maxLength      an Integer value representing the maximum length
   * @param minLowerLength an Integer value representing the minimum length of lowercase letter
   */
  public ValidatorPassword(int minLength, int maxLength,
      int minLowerLength) {
    this(minLength, maxLength, minLowerLength, 0, 0);
  }


  /**
   * Constructs a ValidatorPassword with specified minLength, maxLength, minLowerLength, and
   * minUpperLength.
   *
   * @param minLength      an Integer value representing the minimum length
   * @param maxLength      an Integer value representing the maximum length
   * @param minLowerLength an Integer value representing the minimum length of lowercase letter
   * @param minUpperLength an Integer value representing the minimum length of uppercase letter
   */
  public ValidatorPassword(int minLength, int maxLength,
      int minLowerLength, int minUpperLength) {
    this(minLength, maxLength, minLowerLength, minUpperLength, 0);
  }


  /**
   * Constructs a ValidatorPassword with specified minLength, maxLength, minLowerLength,
   * minUpperLength, and minDigitLength.
   *
   * @param minLength      an Integer value representing the minimum length
   * @param maxLength      an Integer value representing the maximum length
   * @param minLowerLength an Integer value representing the minimum length of lowercase letter
   * @param minUpperLength an Integer value representing the minimum length of uppercase letter
   * @param minDigitLength an Integer value representing the minimum length of digital letter
   */
  public ValidatorPassword(int minLength, int maxLength,
      int minLowerLength, int minUpperLength, int minDigitLength) {
    this.minLength = minLength;
    this.maxLength = maxLength;
    this.minLowerLength = minLowerLength;
    this.minUpperLength = minUpperLength;
    this.minDigitLength = minDigitLength;
  }


  /**
   * Determines whether the input is valid or not, according to specific rules.
   *
   * @param input the provided input of Type String
   * @return true or false
   */
  @Override
  public boolean isValid(String input) {
    return input.length() >= this.minLength && input.length() <= this.maxLength &&
        countLower(input) >= this.minLowerLength &&
        countUpper(input) >= this.minUpperLength &&
        countDigit(input) >= this.minDigitLength &&
        !input.contains(" ");
  }

  /**
   * Checks whether two ValidatorPassword are equal.
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
    ValidatorPassword that = (ValidatorPassword) o;
    return this.minLength == that.minLength &&
        this.maxLength == that.maxLength &&
        this.minLowerLength == that.minLowerLength &&
        this.minUpperLength == that.minUpperLength &&
        this.minDigitLength == that.minDigitLength;
  }

  /**
   * Return the hashcode for the ValidatorPassword.
   *
   * @return an Integer value representing the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.minLength, this.maxLength, this.minLowerLength,
        minUpperLength, minDigitLength);
  }

  /**
   * Return a String representation of the ValidatorPassword.
   *
   * @return a String representing the ValidatorPassword
   */
  @Override
  public String toString() {
    return "ValidatorPassword{" +
        "minLength=" + this.minLength +
        ", maxLength=" + this.maxLength +
        ", minLowerLength=" + this.minLowerLength +
        ", minUpperLength=" + this.minUpperLength +
        ", minDigitLength=" + this.minDigitLength +
        '}';
  }
}
