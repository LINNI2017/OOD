import Exceptions.InvalidValueException;
import java.util.Objects;

/**
 * Field is an object tracking the label, value, whether required or not of the input. It also has a
 * validator that validates input info.
 *
 * @param <T> Field of Type T
 */
public class Field<T> {

  protected String label;
  protected T value;
  protected boolean required;
  protected Validator<T> validator;

  /**
   * Constructs a Field object with specified label, value, required, and validator.
   *
   * @param label     a String representing the field, e.g. "username", "password"
   * @param value     a T type variable representing the input
   * @param required  a Boolean representing whether it is a required field or not
   * @param validator a Validator of type T, representing the validator the client specifies
   */
  public Field(String label, T value,
      boolean required, Validator<T> validator) {
    this.label = label;
    this.value = value;
    this.required = required;
    this.validator = validator;
  }

  /**
   * Update the ​Field​’s ​value​ if the ​input​ is valid according to the ​validator​
   *
   * @param input user input of type T
   * @throws InvalidValueException If ​input​ is not valid, throw an IllegalArgumentException and
   *                               ​do not update​ ​value​.
   */
  public void updateValue(T input) throws InvalidValueException {
    if (!this.validator.isValid(input)) {
      throw new InvalidValueException("The given input is invalid.");
    }
    this.value = input;
  }

  /**
   * Return true if the ​Field​ has been filled out(if it is a required fields, filled with valid
   * input), otherwise return false
   *
   * @return true or false
   */
  public boolean isFilled() {
    return !this.required || this.validator.isValid(this.value);
  }

  /**
   * Return the value of the Field
   *
   * @return the value of Type T
   */
  public T getValue() {
    return this.value;
  }

  /**
   * Checks whether two Fields are equal.
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
    Field<?> that = (Field<?>) o;
    return this.required == that.required &&
        this.label.equals(that.label) &&
        this.value.equals(that.value) &&
        this.validator.equals(that.validator);
  }

  /**
   * Return the hashcode for the Field.
   *
   * @return an Integer value representing the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.label, this.value, this.required, this.validator);
  }

  /**
   * Return a String representation of the Validator.
   *
   * @return a String representing the Validator
   */
  @Override
  public String toString() {
    return "Field{" +
        "label='" + this.label + '\'' +
        ", value=" + this.value +
        ", required=" + this.required +
        ", validator=" + this.validator +
        '}';
  }
}
