import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A class represents a theater
 */
public class Theater {
  private String name;
  private int rowNum;
  private int colNum;
  protected List<Row> table;
  private List<Integer> wheelRows;

  /**
   * Constructs a Theater object with the given name,
   * the number of rows, the number of columns,
   * the wheelchair row.
   * @param name - a String of theater name
   * @param rowNum - an int of theater row number
   * @param colNum - an int of theater column number
   * @param wheelRow - an int of theater wheelchair row sequence number
   */
  public Theater(String name, int rowNum, int colNum, int wheelRow)
      throws FailToBuildTheaterException, InvalidSeatNameException {
    this.name = name;
    this.rowNum = rowNum;
    this.colNum = colNum;
    this.wheelRows = new ArrayList<>();
    this.table = new ArrayList<>();
    validRange(wheelRow);
    initTable();
    addWheelRow(wheelRow);
  }

  /**
   * Check the validity of the given wheel row,
   * if it is out of range or existed as wheel row,
   * throws an exception
   * @param wheelRow - an integer represents a wheel row to add
   * @throws FailToBuildTheaterException -
   * an exception to throw if fails to build a theater
   */
  private void validRange(int wheelRow) throws FailToBuildTheaterException {
    if (wheelRow > this.rowNum || wheelRow <= 0) {
      throw new FailToBuildTheaterException(
          "The given row is out of range.");
    } else if (this.wheelRows.contains(wheelRow - 1)) {
      throw new FailToBuildTheaterException(
          "The given row is already wheel accessible.");
    }
  }

  /**
   * Add the given wheelchair row to this theater.
   * @param wheelR - an int of theater wheelchair row sequence number
   */
  public void addWheelRow(int wheelR) throws FailToBuildTheaterException {
    validRange(wheelR);
    int wheelRow = wheelR - 1;
    this.wheelRows.add(wheelRow);
    this.table.get(wheelRow).setRow(true);
  }

  /**
   * Return the available row sequence number satisfied with
   * the given seat number and the wheelchair accessibility.
   * @param seatNum - the number of seats required
   * @param wheel - a boolean indication of wheelchair accessbility
   * @return an int of the sequence number of available row
   */
  protected int checkAvaRow(int seatNum, boolean wheel) {
    List<Integer> lst = new ArrayList<>();
    if (!wheel) {
      lst = IntStream.range(0, this.rowNum)
          .filter(x -> !this.wheelRows.contains(x))
          .filter(x -> this.table.get(x).avaNum() >= seatNum)
          .mapToObj(Integer::valueOf)
          .sorted(Comparator.comparingInt(x -> Math.abs(x - this.rowNum / 2)))
          .collect(Collectors.toList());
    }
    if (wheel || lst.isEmpty()) {
      lst = this.wheelRows
          .stream()
          .filter(x -> this.table.get(x).avaNum() >= seatNum)
          .sorted(Comparator.comparingInt(x -> Math.abs(x - this.rowNum / 2)))
          .collect(Collectors.toList());
    }
    if (lst.isEmpty()) {
      return -1;
    } else {
      return lst.get(0) + 1;
    }
  }

  /**
   * Fill the given row with the occupied seats and occupant.
   * @param avaR - an int represents the available row index
   * @param seatNum - an  int represents the number of seats required
   * @param occupant - a String of occupant name
   */
  protected void fillRow(int avaR, int seatNum, String occupant) {
    int avaRow = avaR - 1;
    if (avaRow >= 0) {
      this.table.get(avaRow).fillRow(seatNum, occupant);
    }
  }

  /**
   * Initialize the table with rows of non-wheelchair-accessible seats.
   */
  private void initTable() throws InvalidSeatNameException {
    for (int i = 0; i < this.rowNum; i++) {
      this.table.add(new Row(this.colNum, i, false));
    }
  }

  /**
   * Return the name of theater.
   * @return a String represents the theater name
   */
  public String getName() {
    return name;
  }

  /**
   * Return a String of this theater map.
   * @return a String representation of this theater map.
   */
  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < this.rowNum; i++) {
      result += this.table.get(i).toString();
    }
    return result;
  }

  /**
   * Return true if the given object is equal to this theater
   * with the same theater map.
   * @param o - an object to compare with
   * @return a boolean indicates the given object is equal to
   * this theater
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Theater other = (Theater) o;
    try {
      return this.getName().equals(other.getName()) &&
          this.getColNum() == other.getColNum() &&
          this.getRowNum() == other.getRowNum() &&
          this.getTable().toString().equals(other.getTable().toString());
    } catch (InvalidSeatNameException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Return a hash code of this theater.
   * @return an int represents a hash code of this theater.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.rowNum, this.colNum, this.table);
  }

  /**
   * Return the number of rows of this theater.
   * @return an int represents the row number of this theater
   */
  public int getRowNum() {
    return this.rowNum;
  }

  /**
   * Return the number of columns of this theater.
   * @return an int represents the column number of this theater
   */
  public int getColNum() {
    return this.colNum;
  }

  /**
   * Returns a copy of this theater map.
   * @return a list of rows of this theater map copy.
   */
  public List<Row> getTable() throws InvalidSeatNameException {
    List<Row> copy = new ArrayList<>();
    for (Row r : this.table) {
      copy.add(new Row(r.size(), r.getNum(), r.isWheel()));
    }
    return copy;
  }
}
