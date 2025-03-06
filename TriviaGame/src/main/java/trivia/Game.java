package trivia;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private final List<Player> players = new ArrayList<>();
    private final QuestionDeck questionDeck = new QuestionDeck();
    private int currentPlayerIndex = 0;
    private boolean isGettingOutOfPenaltyBox;

    public boolean add(String playerName) {
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                currentPlayer.move(roll);
                askQuestion(currentPlayer);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            currentPlayer.move(roll);
            askQuestion(currentPlayer);
        }
    }

    private void askQuestion(Player player) {
        String category = getCurrentCategory(player.getPosition());
        System.out.println(player.getName() + "'s new location is " + player.getPosition());
        System.out.println("The category is " + category);
        System.out.println(questionDeck.getNextQuestion(category));
    }

    private String getCurrentCategory(int position) {
        int adjustedPosition = (position - 1 + 12) % 12;
        return switch (adjustedPosition) {
            case 0, 4, 8 -> "Pop";
            case 1, 5, 9 -> "Science";
            case 2, 6, 10 -> "Sports";
            case 3, 7, 11 -> "Rock";
            default -> "Unknown";
        };
    }

    public boolean handleCorrectAnswer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        if (currentPlayer.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            nextPlayer();
            return true;
        }

        System.out.println("Answer was correct!");
        currentPlayer.addCoin();
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getCoins() + " Gold Coins.");

        boolean winner = !didPlayerWin(currentPlayer);
        nextPlayer();
        return winner;
    }

    public boolean wrongAnswer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);
        nextPlayer();
        return true;
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private boolean didPlayerWin(Player player) {
        return player.getCoins() == 6;
    }
}
