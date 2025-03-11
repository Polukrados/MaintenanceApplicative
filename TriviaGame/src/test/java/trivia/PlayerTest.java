package trivia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName());
        assertEquals(1, player.getPosition()); // Le joueur commence à la position 1
        assertEquals(0, player.getCoins()); // Le joueur commence avec 0 pièces
        assertFalse(player.isInPenaltyBox()); // Le joueur ne commence pas en prison
    }

    @Test
    public void testSetPosition() {
        Player player = new Player("Bob");
        player.setPosition(5);
        assertEquals(5, player.getPosition()); // Doit être bien mis à jour
    }

    @Test
    public void testAddCoinAndStreak() {
        Player player = new Player("Charlie");
        player.addCoin();
        assertEquals(1, player.getCoins());

        player.addCoin();
        player.addCoin();
        assertEquals(4, player.getCoins());

        player.addCoin(); // 4e bonne réponse
        assertEquals(6, player.getCoins());
    }

    @Test
    public void testResetStreak() {
        Player player = new Player("David");
        player.addCoin();
        player.addCoin();
        player.resetStreak();
        assertEquals(0, player.getStreak());
    }

    @Test
    public void testPenaltyBox() {
        Player player = new Player("Eve");
        player.setInPenaltyBox(true);
        assertTrue(player.isInPenaltyBox());
    }

    @Test
    public void testShouldGoToPenaltyBox() {
        Player player = new Player("Frank");

        // Première erreur -> Seconde chance
        assertFalse(player.shouldGoToPenaltyBox("Science"));

        // Deuxième erreur dans la même catégorie -> Prison
        assertTrue(player.shouldGoToPenaltyBox("Science"));

        // Nouvelle erreur dans une autre catégorie -> Seconde chance
        assertFalse(player.shouldGoToPenaltyBox("Pop"));
    }
}
