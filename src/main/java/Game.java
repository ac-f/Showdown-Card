import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
  private final boolean DEBUG_MODE = false;
  private final Scanner scanner = new Scanner(System.in);
  private String nextLine;
  private final int PLAYER_QUANTITY = 4;
  private int humanPlayerQuantity;
  private final List<Player> playerList = new ArrayList<>();
  private final List<Card> currentCardList = new ArrayList<>();
  private final List<ExchangeHands> currentExchangeList = new ArrayList<>();

  public void start() {
    System.out.println("===================================================");
    System.out.println("=====歡迎來到 C1M2H1 讓 Showdown ! 撲克牌遊戲動起來=====");
    System.out.println("===================================================");

    setPlayerQuantity();
    createPlayer();
    Deck deck = new Deck();
    // 發牌給每個玩家
    for (Player player : playerList) {
      deck.draw(player);
    }

    System.out.println("===================================================");
    System.out.println("=====================遊戲正式開始=====================");
    System.out.println("===================================================");

    for(int round = 0 ; round < 13 ; round++) {
      updateExchangeHandsStatus();
      playerList.forEach(player -> player.takeTurn(this));
      System.out.println("===================================================");
      System.out.println(MessageFormat.format("======================第 {0} 回合======================", round+1));
      System.out.println("===================================================");
      System.out.println("> 本回合玩家出的牌分別是：");
      for (int i = 0; i < playerList.size(); i++) {
        System.out.println(MessageFormat.format(">> {0} - {1} ", playerList.get(i).getName(), currentCardList.get(i)));
      }
      Integer winnerIndex = judgeWinner();
      System.out.println("===================================================");
      System.out.println("> 本局贏家為：" + playerList.get(winnerIndex).getName() + "\r\n \r\n");
      playerList.get(winnerIndex).gainPoint();
      showScore();
      currentCardList.clear();
    }

    int winnerPoints = 0;
    int winnerIndex = 0;
    for (int i = 0; i < playerList.size(); i++) {
      if (playerList.get(i).getPoints() > winnerPoints) {
        winnerPoints = playerList.get(i).getPoints();
        winnerIndex = i;
      }
    }
    System.out.println("===================================================");
    System.out.println("=======================遊戲結束=======================");
    System.out.println("===================================================");
    System.out.println("> 本局贏家為：" + playerList.get(winnerIndex).getName() + "\r\n \r\n");
    System.out.println("> 總分：" + playerList.get(winnerIndex).getPoints() + "\r\n \r\n");
  }

  // 設定玩家人數
  private void setHumanPlayerQuantity(int quantity) {
    if ((quantity < 1 || quantity > 4) && !DEBUG_MODE) {
      throw new IllegalArgumentException("【參數錯誤】最少一人，最多四人");
    }
    this.humanPlayerQuantity = quantity;
  }

  // 選擇遊玩人數
  private void setPlayerQuantity() {
    while (humanPlayerQuantity == 0 && !DEBUG_MODE) {
      System.out.print("> 請輸入遊玩人數（最少一人 最多四人）：");
      nextLine = scanner.nextLine();
      try {
        setHumanPlayerQuantity(Integer.parseInt(nextLine));
      } catch (NumberFormatException e) {
        System.out.println("【參數錯誤】請輸入數字");
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  //創建玩家
  private void createPlayer() {
    int order = 0;
    while (playerList.size() != PLAYER_QUANTITY) {
      if (humanPlayerQuantity != 0) {
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.setOrder(order);
        humanPlayer.setName();
        System.out.println(MessageFormat.format("\n> 玩家 [{0}] 已被創建！", humanPlayer.getName()));
        playerList.add(humanPlayer);
        humanPlayerQuantity--;
      } else {
        ComputerPlayer computerPlayer = new ComputerPlayer();
        computerPlayer.setOrder(order);
        computerPlayer.setName();
        System.out.println(MessageFormat.format("\n> 電腦玩家 [{0}] 已被創建！", computerPlayer.getName()));
        playerList.add(computerPlayer);
      }
      order++;
    }

  }
  public List<Player> getPlayerList() {
    return playerList;
  }
  public void addCurrentCard(Card card) {
    this.currentCardList.add(card);
  }
  public Integer judgeWinner() {
    int winnerIndex = 0;
    for (int i = 0; i < currentCardList.size(); i++) {
        if (currentCardList.get(i).getRank().getValue() > currentCardList.get(winnerIndex).getRank().getValue()) {
          winnerIndex = i;
        } else if (currentCardList.get(i).getRank().getValue().equals(currentCardList.get(winnerIndex).getRank().getValue())) {{
          if (currentCardList.get(i).getSuit().getValue() > currentCardList.get(winnerIndex).getSuit().getValue()) {
            winnerIndex = i;
          }
        }
        }
    }
    return winnerIndex;
  }
  private void showScore() {
    System.out.println("===================================================");
    System.out.println("=====================目前分數統計=====================");
    System.out.println("===================================================");
    for (Player player : playerList) {
      System.out.println(MessageFormat.format(">> {0} - {1} ", player.getName(), player.getPoints()));
    }
    System.out.println("===================================================");
  }
  public void addCurrentExchangeHands(ExchangeHands exchangeHands) {
    currentExchangeList.add(exchangeHands);
  }
  private void updateExchangeHandsStatus() {
    currentExchangeList.forEach(ExchangeHands::updateStatus);
  }


}
