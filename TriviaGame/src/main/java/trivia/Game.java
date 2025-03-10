package trivia;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    public static final int NB_CASE = 12;
    private final List<Player> players = new ArrayList<>();
    private final QuestionDeck questionDeck = new QuestionDeck();
    private int currentPlayerIndex = 0;
    private boolean isGettingOutOfPenaltyBox;
    private static final int MAX_PLAYERS = 6;
    private final boolean gameStarted = false;

    public boolean add(String playerName) {
        if (gameStarted) {
            System.out.println("Cannot add more players. The game has already started.");
            return false;
        }

        if (players.size() >= MAX_PLAYERS) {
            System.out.println("Cannot add more players. The maximum number of players is " + MAX_PLAYERS);
            return false;
        }

        // Vérifier si le nom est déjà utilisé
        for (Player p : players) {
            if (p.getName().equals(playerName)) {
                System.out.println("Player name must be unique.");
                return false;
            }
        }

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
            isGettingOutOfPenaltyBox = roll % 2 != 0;
            System.out.println(currentPlayer.getName() + (isGettingOutOfPenaltyBox ? " is getting out of the penalty box" : " is not getting out of the penalty box"));
            if (isGettingOutOfPenaltyBox) {
                move(currentPlayer, roll);
                askQuestion(currentPlayer);
            }
        } else {
            move(currentPlayer, roll);
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
        int adjustedPosition = (position - 1 + NB_CASE) % NB_CASE;
        adjustedPosition = adjustedPosition%questionDeck.size();
        return questionDeck.getCategory(adjustedPosition);
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

        if (isWinning(currentPlayer)) {
            System.out.println(currentPlayer.getName() + " has won the game with a double score!");
            return false; // Fin du jeu
        }

        nextPlayer();
        return true;
    }

    public boolean wrongAnswer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        String category = getCurrentCategory(currentPlayer.getPosition());

        System.out.println("Question was incorrectly answered");
        currentPlayer.resetStreak();

        if (currentPlayer.shouldGoToPenaltyBox(category)) {
            System.out.println(currentPlayer.getName() + " was sent to the penalty box");
            currentPlayer.setInPenaltyBox(true);
        } else {
            System.out.println(currentPlayer.getName() + " gets a second chance in the same category.");
        }

        nextPlayer();
        return true;
    }


    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean isPlayable() {
        return players.size() >= 2;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void move(Player player, int roll) {
        int newPosition = (player.getPosition() + roll) % NB_CASE;
        if (newPosition == 0) newPosition = NB_CASE; // éviter 0
        player.setPosition(newPosition);
    }

    public boolean isWinning(Player player) {
        return player.getCoins() >= NB_CASE;
    }
}
