import enums.Rank;
import enums.Suit;

import java.text.MessageFormat;

public class Card {
  private Rank rank;
  private Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public String getName() {
    return MessageFormat.format("【{0}】{1}",getSuit().getTitle(), rank.getTitle());
  }

  @Override
  public String toString() {
    return getName();
  }
}
