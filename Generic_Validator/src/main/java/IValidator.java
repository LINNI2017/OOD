/**
 * IValidator stores requirements for user input and provides a method, ​boolean isValid(input)​,
 * that determines if the provided input meets the requirements.
 *
 * @param <T> validator of Type T
 */
public interface IValidator<T> {

  /**
   * Determines if the provided input meets the requirements. Return true if all requirements are
   * met, otherwise return false.
   *
   * @param input the provided input of Type T
   * @return true or false
   */
  boolean isValid(T input);

  /**
   * Checks whether two Validators are equal.
   *
   * @param o the object to check with
   * @return true or false
   */
  boolean equals(Object o);

  /**
   * Return the hashcode for the Validator.
   *
   * @return an Integer value representing the hashcode
   */
  int hashCode();

  /**
   * Return a String representation of the Validator.
   *
   * @return a String representing the Validator
   */
  String toString();
}
