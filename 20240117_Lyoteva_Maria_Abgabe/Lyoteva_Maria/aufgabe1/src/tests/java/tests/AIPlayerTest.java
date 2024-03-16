package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe1.AIPlayer;
import aufgabe1.Board;
import aufgabe1.InvalidMoveException;

class AIPlayerTest {
	private Board board;
	private AIPlayer aiPlayer;

	@BeforeEach
	void setUp() {
		board = new Board(3, 3);
		aiPlayer = new AIPlayer("AI", 'X');
	}

	@Test
	void decideMoveLegal() throws InvalidMoveException {
		Board.Coordinates coords = new Board.Coordinates(1, 3);
		assertTrue(aiPlayer.decideMove(board, coords));
	}

	@Test
	void decideMoveFullBoard() throws InvalidMoveException {
		board.makeMove(new Board.Coordinates(0, 0), 'O');
		board.makeMove(new Board.Coordinates(1, 1), 'O');
		board.makeMove(new Board.Coordinates(2, 2), 'X');
		board.makeMove(new Board.Coordinates(0, 2), '0');
		board.makeMove(new Board.Coordinates(1, 0), 'X');
		board.makeMove(new Board.Coordinates(2, 1), 'O');
		board.makeMove(new Board.Coordinates(0, 1), 'X');
		board.makeMove(new Board.Coordinates(1, 2), 'O');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		Board.Coordinates coords = new Board.Coordinates(0, 0);
		InvalidMoveException thrown = assertThrows(InvalidMoveException.class, () -> aiPlayer.decideMove(board, coords),
				"The board is already full. The game is a tie.");

		assertEquals("The board is already full. The game is a tie.", thrown.getMessage());
	}
}
