/**
 * A class represents a seat.
 */
public class Seat {
  private String name;
  private String occupant;
  private boolean isWheel;
  private static final String A = "A";
  private static final String Z = "Z";
  private static final String NORMAL_CHAIR = "_";
  private static final String OCCUPY = "X";
  private static final String WHEEL_CHAIR = "=";

  /**
   * Constructs a Seat object with the given name and
   * a boolean indication of wheelchair accessibility.
   * @param name - the seat name
   * @param isWheel - an indication of wheelchair accessibility
   */
  public Seat(String name, boolean isWheel) throws InvalidSeatNameException {
    this.name = validName(name);
    this.occupant = null;
    this.isWheel = isWheel;
  }

  /**
   * Return true if this seat is wheelchair accessible
   * @return a boolean indication of wheelchair accessible
   */
  public boolean isWheel() {
    return this.isWheel;
  }

  /**
   * Return a valid seat name.
   * @param name - a string of seat name to check
   * @return a valid seat name
   */
  private String validName(String name) throws InvalidSeatNameException {
    if (name.length() == 1 && name.compareTo(A) >= 0 && name.compareTo(Z) <= 0) {
      return name;
    } else {
      throw new InvalidSeatNameException("Invalid seat name, should be a letter.");
    }
  }

  /**
   * Set this seat wheelchair accessibility with the given indication.
   * @param isWheel - a boolean indicates the seat wheelchair accessibility
   */
  public void setWheel(boolean isWheel) {
    this.isWheel = isWheel;
  }

  /**
   * Return the seat name.
   * @return a String represents the seat name
   */
  public String getName() {
    return name;
  }

  /**
   * Return the occupant name.
   * @return a String represents the occupant name
   */
  public String getOccupant() {
    return occupant;
  }

  /**
   * Set this seat with the given occupant name.
   * @param occupant - a String represents the occupant name
   */
  public void setOccupant(String occupant) {
    this.occupant = occupant;
  }

  /**
   * Set this seat with the given seat name.
   * @param name - a String represents the seat name
   */
  public void setName(String name) throws InvalidSeatNameException {
    this.name = validName(name);
  }

  /**
   * Return a String represents the seat.
   * @return a String of seat representation
   */
  public String toString() {
    if (this.occupant != null) {
      return OCCUPY;
    } else {
      return this.isWheel ? WHEEL_CHAIR : NORMAL_CHAIR;
    }
  }
}
