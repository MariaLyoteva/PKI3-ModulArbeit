package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe1.AIPlayer;
import aufgabe1.Board;
import aufgabe1.GameUtils;
import aufgabe1.HumanPlayer;

class GameUtilsTest {
	private Board board;
	private HumanPlayer humanPlayer;
	private AIPlayer aiPlayer;
	GameUtils game;

	@BeforeEach
	void setUp() {
		board = new Board(3, 3);
		humanPlayer = new HumanPlayer("Human", 'X');
		aiPlayer = new AIPlayer("AI", 'O');
		game = new GameUtils(board, humanPlayer, aiPlayer);
	}

	@Test
	void testSetUpGame() {
		GameUtils newGame = GameUtils.setUpGame();
		assertNotNull(newGame);
		assertEquals('X', newGame.humanPlayer.getMark());
		assertEquals('O', newGame.aiPlayer.getMark());
	}

}
