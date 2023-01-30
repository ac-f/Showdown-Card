import java.text.MessageFormat;
import java.util.Objects;
import java.util.Scanner;

public class HumanPlayer extends Player {
  private final Scanner scanner = new Scanner(System.in);
  private String nextLine;

  @Override
  public Card showCard() {
    Card card = null;
    var handCards = this.getHand().getCards();
    while (Objects.isNull(card)) {
      try {
        System.out.println("> 您目前擁有的卡片");
        for (int i = 0; i < handCards.size(); i++) {
          System.out.println(MessageFormat.format("｜ {0} - {1}", i, handCards.get(i).getName()));
        }
        System.out.print("> 請輸入您要出的卡牌編號：");
        nextLine = scanner.nextLine();
        card = this.getHand().getCards().get(Integer.parseInt(nextLine));
      } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
        System.out.println("【參數錯誤】請輸入正確的卡片編號");
      }
    }
    return card;

  }

  public void setName() {
    System.out.print(MessageFormat.format("> 請輸入玩家[{0}]的名稱：", this.getOrder()));
    this.name = scanner.nextLine();
  }

  @Override
  public boolean makeExchangeHandsDecision() {
    Boolean result = null;
    if (this.getRemainPrivilege() == 0) return false;
    while (Objects.isNull(result)) {
      System.out.println("> 請問您有需要使用特權<交換手牌>嗎？");
      System.out.print(">> [1]使用 [其他字元]否定：");
      nextLine = scanner.nextLine();
      try {
        result = Integer.parseInt(nextLine) == 1;
      } catch (IllegalArgumentException e) {
        result = false;
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return getName();
  }
}
