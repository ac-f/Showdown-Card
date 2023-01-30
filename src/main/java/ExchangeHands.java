import java.text.MessageFormat;

public class ExchangeHands {
  private final Player sourcePlayer;
  private final Player targetPlayer;
  private Integer remainTurns = 3;
  public ExchangeHands (Player sourcePlayer, Player targetPlayer) {
    this.sourcePlayer = sourcePlayer;
    this.targetPlayer = targetPlayer;
    System.out.println(MessageFormat.format("> {0}使用特權<交換手牌>，現在 {0} 將與 {1} 進行手牌交換", sourcePlayer.getName(), targetPlayer.getName()));
    swapHand();
  }
  public void updateStatus() {
    remainTurns--;
    if (remainTurns == 0) {
      System.out.println(MessageFormat.format("> 特權三回合時間已到，現在 {0} 將與 {1} 進行手牌交換", sourcePlayer.getName(), targetPlayer.getName()));
      swapHand();
    }
  }
  private void swapHand() {
    Hand tempHand = sourcePlayer.getHand();
    sourcePlayer.setHand(targetPlayer.getHand());
    targetPlayer.setHand(tempHand);

  }
}
