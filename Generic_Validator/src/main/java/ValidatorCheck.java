/**
 * ValidatorCheck is a Validator that determines whether or not the button is checked.
 */
public class ValidatorCheck extends Validator<Boolean> {

  /**
   * Constructs a ValidatorCheck.
   */
  public ValidatorCheck() {
  }

  /**
   * Returns true if the input is a Boolean value or null, otherwise return false.
   *
   * @param input the provided input of Type Boolean
   * @return true or false
   */
  @Override
  public boolean isValid(Boolean input) {
    return true;
  }

  /**
   * Return a String representation of the ValidatorCheck.
   *
   * @return a String representing the ValidatorCheck
   */
  @Override
  public String toString() {
    return "ValidatorCheck{}";
  }

  /**
   * Return the hashcode for the ValidatorCheck.
   *
   * @return an Integer value representing the hashcode
   */
  @Override
  public int hashCode() {
    return 31;
  }

  /**
   * Checks whether two ValidatorChecks are equal.
   *
   * @param o the object to check with
   * @return true or false
   */
  @Override
  public boolean equals(Object o) {
    return o != null && this.getClass() == o.getClass();
  }
}
