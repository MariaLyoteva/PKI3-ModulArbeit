package aufgabe2_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe2.AIPlayer;
import aufgabe2.Board;
import aufgabe2.GameUtilsAIHuman;
import aufgabe2.HumanPlayer;

class GameUtilsAIHumanTest {

	private Board board;
	private HumanPlayer humanPlayer;
	private AIPlayer aiPlayer;
	GameUtilsAIHuman game;

	@BeforeEach
	void setUp() {
		humanPlayer = new HumanPlayer("Human", 'X');
		aiPlayer = new AIPlayer("AI", 'O');
		game = new GameUtilsAIHuman(board, humanPlayer, aiPlayer);
	}

	@Test
	void testSetUpGame() {
		String simulatedUserInput = "5\n5\n"; // 5 rows, 5 columns
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

		// Call the setUpGame method
		GameUtilsAIHuman newGame = GameUtilsAIHuman.setUpGame();

		// Assert that the game is set up correctly
		assertNotNull(newGame);
		assertEquals('X', newGame.humanPlayer.getMark());
		assertEquals('O', newGame.aiPlayer.getMark());
	}

}
