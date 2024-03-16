package aufgabe2_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe2.AIPlayer;
import aufgabe2.Board;
import aufgabe2.InvalidMoveException;

class AIPlayerTest {
	private Board board;
	private AIPlayer aiPlayer;

	@BeforeEach
	void setUp() {
		board = new Board(4, 4);
		aiPlayer = new AIPlayer("AI", 'X');
	}

	@Test
	void decideMoveLegal() throws InvalidMoveException {
		Board.Coordinates coords = new Board.Coordinates(1, 3);
		assertTrue(aiPlayer.decideMove(board, coords));
	}

	@Test
	void decideMoveFullBoard() throws InvalidMoveException {
		board.makeMove(new Board.Coordinates(0, 0), 'X');
		board.makeMove(new Board.Coordinates(0, 1), 'O');
		board.makeMove(new Board.Coordinates(0, 2), 'X');
		board.makeMove(new Board.Coordinates(0, 3), 'O');

		board.makeMove(new Board.Coordinates(1, 0), 'O');
		board.makeMove(new Board.Coordinates(1, 1), 'X');
		board.makeMove(new Board.Coordinates(1, 2), 'O');
		board.makeMove(new Board.Coordinates(1, 3), 'X');

		board.makeMove(new Board.Coordinates(2, 0), 'X');
		board.makeMove(new Board.Coordinates(2, 1), 'O');
		board.makeMove(new Board.Coordinates(2, 2), 'X');
		board.makeMove(new Board.Coordinates(2, 3), 'O');

		board.makeMove(new Board.Coordinates(3, 0), 'X');
		board.makeMove(new Board.Coordinates(3, 1), 'O');
		board.makeMove(new Board.Coordinates(3, 2), 'X');
		board.makeMove(new Board.Coordinates(3, 3), 'O');
		Board.Coordinates coords = new Board.Coordinates(0, 0);
		InvalidMoveException thrown = assertThrows(InvalidMoveException.class, () -> aiPlayer.decideMove(board, coords),
				"The board is already full. The game is a tie.");

		assertEquals("The board is already full. The game is a tie.", thrown.getMessage());
	}
}
