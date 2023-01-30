package enums;

import java.util.Arrays;
import java.util.List;

public enum Suit {
  CLUB("梅花", 1),
  DIAMOND("方塊", 2),
  HEART("紅心", 3),
  SPADE("黑桃", 4);
  private final String title;
  private final Integer value;
  Suit (String title, int value) {
    this.title = title;
    this.value = value;
  }

  public static List<Suit> getList() {
    return Arrays.stream(Suit.values()).toList();
  }

  public String getTitle() {
    return title;
  }

  public Integer getValue() {
    return value;
  }
}
