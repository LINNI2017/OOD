import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class represents a reservation system.
 */
public class ReservationsSystem {
  private static final String RESERVE = "reserve";
  private static final String SHOW = "show";
  private static final String DONE = "done";
  private static final String YES = "y";
  private static final String NO = "n";

  /**
   * Constructs a ReservationsSystem object.
   */
  protected ReservationsSystem() {
  }

  /**
   * Return true if the given line starts with y,
   * otherwise return false.
   * @param line - a String to check
   * @return true if the given line starts with y
   */
  protected static Boolean validYN(String line) {
    String lineCopy = line.toLowerCase();
    if (lineCopy.equals(YES)) {
      return true;
    } else if (lineCopy.equals(NO)) {
      return false;
    }
    return null;
  }

  /**
   * Return true if the given line contains negative number,
   * otherwise false.
   * @param line - a String to check
   * @return true if the given line contains negative number
   */
  protected static boolean negativeNumeric(String line) {
    Pattern p = Pattern.compile("-\\d");
    Matcher m = p.matcher(line);
    return m.find();
  }

  /**
   * Return the sequence number of the seat in the given line.
   * @param line - a String to check
   * @return an int represents the seat number
   */
  protected static int getSeatNum(String line) {
    for (String s : line.split(" ")) {
      s = s.replaceAll("\\D", "");
      if (s.length() > 0) {
        return Integer.parseInt(s);
      }
    }
    return 0;
  }

  /**
   * The main function of the reservation system,
   * operates the system, and prompt user to type in,
   * processes and prints out result followed with.
   * @param args - a string array of argument
   */
  public static void main(String []args)
      throws FailToBuildTheaterException, InvalidSeatNameException {
    Scanner scanner = new Scanner(System.in);

    Theater theater = new Theater("Roxy", 15, 10, 1);
    ReservationsService.addWheelRow(theater, 2);
    ReservationsService.addWheelRow(theater, 14);
    ReservationsService.addWheelRow(theater, 15);
    
    while (true) {
      System.out.println("What would you like to do?");
      String operation = scanner.nextLine().toLowerCase();
      if (operation.startsWith(RESERVE)) {
        int seatNum;
        if (negativeNumeric(operation) ||
            (seatNum = getSeatNum(operation)) <= 0) {
          System.out.println(
              "Invalid seat number, should be a positive number.\n");
          continue;
        }

        Boolean isWheel = null;
        while (isWheel == null) {
          System.out.println("Do you need wheelchair accessible seats? Y/N");
          isWheel = validYN(scanner.nextLine());
        }

        int row = ReservationsService.checkAvaRow(theater, seatNum, isWheel);
        if (row > 0) {
          System.out.println("What’s your name?");
          String occupant = scanner.nextLine().replaceAll("[^\\w ]", "").trim();
          theater.fillRow(row, seatNum, occupant);
          System.out.println(String.format(
              "I’ve reserved %d seats for you at the %s in row %d, %s.\n",
              seatNum, theater.getName(), row, occupant));
        } else {
          System.out.println(
              "Sorry, we don’t have that many seats together for you.\n");
        }
      } else if (operation.equals(SHOW)) {
        System.out.println(ReservationsService.getSeatMap(theater));
      } else if (operation.equals(DONE)) {
        System.out.println("Have a nice day!");
        break;
      } else {
        System.out.println("Doesn't support this operation.\n" +
            "Usage: reserve <number> or show or done\n");
      }
    }
  }
}
