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
    public void testMove() {
        Player player = new Player("Bob");
        player.move(5);
        assertEquals(6, player.getPosition()); // 1 + 5 = 6

        player.move(7); // Devrait boucler (6 + 7 = 13 -> revient à 1)
        assertEquals(1, player.getPosition());
    }

    @Test
    public void testAddCoin() {
        Player player = new Player("Charlie");
        player.addCoin();
        assertEquals(1, player.getCoins());
    }

    @Test
    public void testPenaltyBox() {
        Player player = new Player("David");
        player.setInPenaltyBox(true);
        assertTrue(player.isInPenaltyBox());
    }
}
