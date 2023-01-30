import enums.Rank;
import enums.Suit;

import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer extends Player {
  private final List<String> nameList = List.of("Johnny", "HandsomeBoy", "Peter", "Jack");
  @Override
  public Card showCard() {
    List<Card> cards = getHand().getCards();
    int minIndex  = 0;
    int maxIndex  = getHand().getCards().size()-1;
    ThreadLocalRandom random = ThreadLocalRandom.current();
    int pickIndex = random.nextInt(minIndex, maxIndex + 1);
    return cards.get(pickIndex);
  }

  @Override
  public void setName() {
    this.name = nameList.get(this.getOrder());
  }

  @Override
  public boolean makeExchangeHandsDecision() {
    return Math.random() > 0.7;
  }
  @Override
  public String toString() {
    return getName();
  }
}
