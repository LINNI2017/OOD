import java.util.ArrayList;
import java.util.Objects;

/**
 * A class represents a row.
 */
public class Row extends ArrayList<Seat> {
  private int num;
  private boolean isWheel;
  private int colNum;
  private static final int FIRST_SEAT = (int) 'A';

  /**
   * Constructs a Row object with the given capacity,
   * the sequence number, and wheelchair accessibility.
   * @param initialCapacity the initial capacity of the list
   * @param num - the sequence number of the row
   * @param isWheel - a boolean indication of wheelchair accessibility
   */
  public Row(int initialCapacity, int num, boolean isWheel)
      throws InvalidSeatNameException {
    super(initialCapacity);
    this.num = num;
    this.isWheel = isWheel;
    this.colNum = initialCapacity;
    addSeat(this.isWheel);
  }

  /**
   * Return true if this row is wheelchair accessible.
   * @return true if this row is wheelchair accessible
   */
  public boolean isWheel() {
    return this.isWheel;
  }

  /**
   * Return the number of available seats in this row.
   * @return the number of available seats in this row
   */
  protected int avaNum() {
    return (int) this.stream()
        .filter(x -> x.getOccupant() == null)
        .count();
  }

  /**
   * Add the seats to this row.
   * @param isWheel - a boolean indication of wheelchair accessibility
   */
  protected void addSeat(boolean isWheel) throws InvalidSeatNameException {
    while (this.size() < this.colNum) {
      String seatName = Character.toString((char) (this.size() + FIRST_SEAT));
      this.add(this.size(), new Seat(seatName, isWheel));
    }
  }

  /**
   * Fill this seat with the number of seats occupied by the given occupant.
   * @param seatNum - the number of seats to fill
   * @param occupant - the occupant occupied
   */
  protected void fillRow(int seatNum, String occupant) {
    int filled = 0;
    for (int i = 0; i < this.colNum; i++) {
      if (this.get(i).getOccupant() == null) {
        this.get(i).setOccupant(occupant);
        filled++;
      }
      if (filled == seatNum) {
        break;
      }
    }
  }

  /**
   * Set this row with indication of wheelchair accessibility.
   * @param isWheel - an indication of wheelchair accessibility
   */
  protected void setRow(boolean isWheel) {
    for (int i = 0; i < this.colNum; i++) {
      this.get(i).setWheel(isWheel);
    }
    this.isWheel = isWheel;
  }

  /**
   * Return a String represents this row.
   * @return a String represents this row
   */
  @Override
  public String toString() {
    String result = Integer.toString(num + 1);
    if (num < 9) {
      result += " ";
    }
    for (int i = 0; i < this.size(); i++) {
      result += " " + this.get(i).toString();
    }
    result += "\n";
    return result;
  }

  /**
   * Return true if the given row is equal to this row.
   * @param o - an object to compare with
   * @return a boolean indicates the given row is equal to this row
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Row seats = (Row) o;
    return this.num == seats.num &&
        this.isWheel == seats.isWheel &&
        this.colNum == seats.colNum;
  }

  /**
   * Return an int represents the hash code of this row.
   * @return an int represents the hash code of this row
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), num, isWheel, colNum);
  }

  /**
   * Return the sequence number of this row.
   * @return an int represents the sequence number of this row
   */
  public int getNum() {
    return num;
  }
}
