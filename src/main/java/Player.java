import java.text.MessageFormat;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {
  private final Scanner scanner = new Scanner(System.in);
  private String nextLine;
  private Hand hand =  new Hand();
  private int order;
  protected String name;
  private int points = 0;
  private int remainPrivilege = 1;

  public abstract Card showCard();

  public abstract void setName();

  public abstract boolean makeExchangeHandsDecision();


  public void setOrder(int order) {
    this.order = order;
  }

  public int getOrder() {
    return this.order;
  }


  public String getName() {
    return this.name;
  }

  public void addHandCards(Card card) {
    this.hand.addCard(card);
  }

  public void takeTurn(Game game) {
    System.out.println(MessageFormat.format("現在輪到玩家 [{0}]", this.getName()));
    var playerList = game.getPlayerList().stream().filter(player -> player!=this).toList();
    if (remainPrivilege > 0 && makeExchangeHandsDecision()) {
      Integer targetPlayerIndex = null;
      while(Objects.isNull(targetPlayerIndex)) {
        try {
          System.out.println("請選擇您要指定交換卡片的玩家");

          for (int i = 0; i < playerList.size(); i++) {
            System.out.println(MessageFormat.format("| {0} - {1}", i, playerList.get(i)));
          }
          if (this instanceof HumanPlayer) {
            nextLine = scanner.nextLine();
            targetPlayerIndex = Integer.parseInt(nextLine);
          } else {
            int minIndex  = 0;
            int maxIndex  = playerList.size()-1;
            ThreadLocalRandom random = ThreadLocalRandom.current();
            targetPlayerIndex = random.nextInt(minIndex, maxIndex + 1);
          }

          ExchangeHands hands = new ExchangeHands(this, playerList.get(targetPlayerIndex));
          game.addCurrentExchangeHands(hands);
        } catch (IllegalArgumentException e) {
          System.out.println("【參數錯誤】請重新選擇");
        }
      }
      exchangeHands(playerList.get(targetPlayerIndex));
    }
    var pickCard = showCard();
    game.addCurrentCard(pickCard);
    hand.getCards().remove(pickCard);
  }

  public void exchangeHands(Player targetPlayer) {
    this.remainPrivilege--;
  }

  public void gainPoint() {
    this.points++;
  }

  public Hand getHand() {
    return hand;
  }
  public void setHand(Hand h) {
    this.hand = h;
  }
  public int getRemainPrivilege () {
    return remainPrivilege;
  }
  public int getPoints () {
    return points;
  }

}
