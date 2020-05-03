package todoauto.view;

import java.text.ParseException;
import java.util.Map;
import todoauto.controller.CSVProcessor;
import todoauto.controller.CommandLineParser;

public class Main {

  public Main() {
  }

  /**
   * Method to process command line argument
   * @param args an array of String representing command line argument
   * @throws ParseException when date format is incorrect
   */
  public static void main(String[] args) throws ParseException {
//    System.out.println(System.getProperty("user.dir"));
    Map<String, String> argMap = CommandLineParser.process(args);
    if (argMap != null && !argMap.isEmpty()) {
      CSVProcessor.process(argMap);
    } else {
      System.out.println("Usage Error: Missing arguments.");
    }
  }
}
