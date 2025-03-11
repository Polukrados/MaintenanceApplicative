
package trivia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

	@Test
	public void testAddPlayer() {
		Game game = new Game();
		assertTrue(game.add("Alice"));
		assertEquals(1, game.howManyPlayers());
	}

	@Test
	public void testGameStartsWithAtLeastTwoPlayers() {
		Game game = new Game();
		game.add("Alice");
		assertFalse(game.isPlayable()); // Pas assez de joueurs

		game.add("Bob");
		assertTrue(game.isPlayable()); // Suffisamment de joueurs
	}

	@Test
	public void testRollMovesPlayer() {
		Game game = new Game();
		game.add("Alice");
		game.add("Bob");

		game.roll(4);
		assertEquals(5, game.getCurrentPlayer().getPosition()); // 1 + 4 = 5
	}

	@Test
	public void testCorrectAnswerGivesCoin() {
		Game game = new Game();
		game.add("Alice");
		game.add("Bob");

		game.handleCorrectAnswer();
		assertEquals(0, game.getCurrentPlayer().getCoins()); // Pas de pièce d'or
	}

	@Test
	public void testHandlewrongAnswerSendsToPenaltyBox() {
		Game game = new Game();
		game.add("Alice");
		game.add("Bob");

		game.handlewrongAnswer();
		assertFalse(game.getCurrentPlayer().isInPenaltyBox()); // Pas dans la boîte
	}
}
