import java.util.ArrayList;
import java.util.List;

public class Hand {
  private List<Card> cards = new ArrayList<>();
  public int size() {
    return cards.size();
  }
  public void addCard(Card card) {
    cards.add(card);
  }

  public List<Card> getCards() {
    return cards;
  }

}
