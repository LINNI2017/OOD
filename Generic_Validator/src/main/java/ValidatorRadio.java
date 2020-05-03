/**
 * ValidatorRadio is a Validator used for Boolean input. As long as the Boolean input is not null,
 * it is valid.
 */
public class ValidatorRadio extends Validator<Boolean> {

  /**
   * Constructs a ValidatorRadio.
   */
  public ValidatorRadio() {
  }

  /**
   * Returns true if the input is a Boolean value and is not null, otherwise return false.
   *
   * @param input the provided input of Type Boolean
   * @return true or false
   */
  @Override
  public boolean isValid(Boolean input) {
    return input != null;
  }

  /**
   * Return a String representation of the ValidatorRadio.
   *
   * @return a String representing the ValidatorRadio
   */
  @Override
  public String toString() {
    return "ValidatorRadio{}";
  }

  /**
   * Return the hashcode for the ValidatorRadio.
   *
   * @return an Integer value representing the hashcode
   */

  @Override
  public int hashCode() {
    return 37;
  }

  /**
   * Checks whether two ValidatorRadio are equal.
   *
   * @param o the object to check with
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    return o != null && this.getClass() == o.getClass();
  }
}
