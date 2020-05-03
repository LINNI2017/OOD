package todoauto.view;

import java.text.ParseException;
import java.util.Map;
import todoauto.controller.CSVProcessor;
import todoauto.controller.CommandLineParser;
import todoauto.controller.InvalidArgumentsException;

public class Main {

  public Main() {
  }

  /**
   * Method to process command line argument
   * @param args an array of String representing command line argument
   * @throws InvalidArgumentsException when argument length is 0
   * @throws ParseException when date format is incorrect
   */
  public static void main(String[] args)
      throws InvalidArgumentsException, ParseException {
//    System.out.println(System.getProperty("user.dir"));
    Map<String, String> argMap = CommandLineParser.process(args);
    CSVProcessor.process(argMap);
  }
}
