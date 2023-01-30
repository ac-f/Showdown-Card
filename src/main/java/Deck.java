import enums.Rank;
import enums.Suit;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
  private List<Card> cards = new ArrayList<>();

  public Deck() {
    initCards();
    shuffle();
  }

  private void initCards() {
    System.out.println("> 正在初始化牌組...");
    var suitList = Suit.getList();
    var rankList = Rank.getList();
    suitList.forEach(suit -> {
      rankList.forEach(rank -> {
        Card card = new Card(rank, suit);
        cards.add(card);
      });
    });
    System.out.println("> 初始化牌組完畢");
  }

  private void shuffle() {
    System.out.println("> 正在洗牌...");
    int minIndex  = 0;
    int maxIndex  = cards.size()-1;
    for (int i = 0 ; i < maxIndex; i++) {
      ThreadLocalRandom random = ThreadLocalRandom.current();
      var swapIndex = random.nextInt(minIndex, maxIndex + 1);
      Collections.swap(cards, i, swapIndex);
    }
    System.out.println("> 洗牌完畢");
  }

  public void draw(Player player) {
    System.out.println(MessageFormat.format("> 正在發牌給玩家 [{0}]...", player.getName()));
    for (int i = 0 ; i < 13 ; i++) {
      player.addHandCards(cards.get(0));
//      System.out.println(MessageFormat.format("玩家[{0}]取得卡片: {1}", player.getName(), cards.get(0)));
      cards.remove(0);
    }
    System.out.println("> 發牌完畢");
  }
}
