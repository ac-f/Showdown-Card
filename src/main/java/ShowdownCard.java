import java.util.Scanner;

public class ShowdownCard {
  public static void main (String[] args) {
    try {
      new Game().start();
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
}
