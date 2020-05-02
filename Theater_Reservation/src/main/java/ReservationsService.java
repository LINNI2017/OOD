/**
 * A class represents a reservation service.
 */
public class ReservationsService {

  /**
   * Constructs a ReservationsService object.
   */
  protected ReservationsService() {
  }

  /**
   * Return the sequence number of the available row in the given theater
   * satisfied with the required seat number and wheelchair accessibility.
   * @param theater - a theater object
   * @param seatNum - a number of required seats
   * @param isWheel - a boolean indicates the wheelchair accessibility
   * @return a sequence number of the available row
   */
  public static int checkAvaRow(Theater theater, int seatNum, boolean isWheel) {
  	return theater.checkAvaRow(seatNum, isWheel);
  }

  /**
   * Return the string represents the theater map.
   * @param theater - a theater object
   * @return a string of theater map representation
   */
  public static String getSeatMap(Theater theater) {
  	return theater.toString();
  }

  /**
   * Add the given wheelchair row to the given theater.
   * @param theater - a theater object
   * @param wheelRow - the wheelchair row number to add
   */
  public static void addWheelRow(Theater theater, int wheelRow)
      throws FailToBuildTheaterException {
    theater.addWheelRow(wheelRow);
  }

  /**
   * Returns true if the given object is equal to this object.
   * @param o - an object to compare
   * @return a boolean indicates whether the given object is
   * equal to this object
   */
  @Override
  public boolean equals(Object o) {
    return o != null && this.getClass() == o.getClass();
  }

  /**
   * Return an int to represent the hashcode of this object
   * @return a hash code int of this object
   */
  @Override
  public int hashCode() {
    return 41;
  }

  /**
   * Returns a String represents the object.
   * @return a String representation of the object
   */
  @Override
  public String toString() {
    return "ReservationsService";
  }
}
