package enums;

import java.util.Arrays;
import java.util.List;

public enum Rank {
  TWO("2", 1),
  THREE("3", 2),
  FOUR("4", 3),
  FIVE("5", 4),
  SIX("6", 5),
  SEVEN("7", 6),
  EIGHT("8", 7),
  NIGHT("9", 8),
  TEN("10", 9),
  J("J", 10),
  Q("Q", 11),
  K("K", 12),
  A("A", 13),
          ;
  private final String title;
  private final Integer value;
  Rank(String title, int value) {
    this.title = title;
    this.value = value;
  }

  public static List<Rank> getList() {
    return Arrays.stream(Rank.values()).toList();
  }

  public String getTitle() {
    return this.title;
  }

  public Integer getValue() {
    return this.value;
  }
}
