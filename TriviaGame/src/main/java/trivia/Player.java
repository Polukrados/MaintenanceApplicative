package trivia;

public class Player {
    private final String name;
    private int position;
    private int coins;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.position = 1;
        this.coins = 0;
        this.inPenaltyBox = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move(int roll) {
        position = (position + roll) % 12;
        if (position == 0) position = 12;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoin() {
        coins++;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }
}
