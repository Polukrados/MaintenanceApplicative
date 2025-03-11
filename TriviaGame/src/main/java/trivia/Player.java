package trivia;

public class Player {
    private final String name;
    private int position;
    private int coins;
    private boolean inPenaltyBox;
    private String lastWrongCategory = "";
    private boolean hasSecondChance = false;
    private int streak = 0;

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

    public int getCoins() {
        return coins;
    }

    public void addCoin() {
        streak++;
        if (streak >= 3) {
            coins += 2;
        } else {
            coins++;
        }
    }

    public void resetStreak() {
        streak = 0;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean shouldGoToPenaltyBox(String category) {
        if (hasSecondChance && lastWrongCategory.equals(category)) {
            hasSecondChance = false;
            return true;
        } else {
            lastWrongCategory = category;
            hasSecondChance = true;
            return false;
        }
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getStreak() {
        return streak;
    }
}
