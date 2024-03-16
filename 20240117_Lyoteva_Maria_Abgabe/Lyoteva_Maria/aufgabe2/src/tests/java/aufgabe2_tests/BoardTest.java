package aufgabe2_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import aufgabe2.Board.Coordinates;
import aufgabe2.Board;

class BoardTest {

	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board(4, 4);
	}

	@Test
	void testAllMatchRow() {
		// Set up the board
		board.makeMove(new Coordinates(0, 0), 'X');
		board.makeMove(new Coordinates(0, 1), 'X');
		board.makeMove(new Coordinates(0, 2), 'X');
		board.makeMove(new Coordinates(0, 3), 'X');

		// Test if allMatchLine correctly identifies a row match
		assertTrue(board.allMatchLine('X', Board.LineType.ROW),
				"Four consecutive 'X' pieces in a row should return true");
	}

	@Test
	void testAllMatchCol() {
		// Set up the board
		board.makeMove(new Coordinates(0, 0), 'O');
		board.makeMove(new Coordinates(1, 0), 'O');
		board.makeMove(new Coordinates(2, 0), 'O');
		board.makeMove(new Coordinates(3, 0), 'O');

		// Test if allMatchLine correctly identifies a column match
		assertTrue(board.allMatchLine('O', Board.LineType.COLUMN),
				"Four consecutive 'O' pieces in a column should return true");
	}

	@Test
	void testCheckDiagonal() {
		// Set up a diagonal line from top-left to bottom-right
		board.makeMove(new Coordinates(0, 0), 'X');
		board.makeMove(new Coordinates(1, 1), 'X');
		board.makeMove(new Coordinates(2, 2), 'X');
		board.makeMove(new Coordinates(3, 3), 'X');

		// Test if checkDiagonal correctly identifies a diagonal match
		assertTrue(board.checkDiagonal('X'), "Four consecutive 'X' pieces in a diagonal should return true");
	}

	@Test
	void testCheckAntiDiagonal() {
		// Set up an anti-diagonal line from top-right to bottom-left
		board.makeMove(new Coordinates(0, 3), 'O');
		board.makeMove(new Coordinates(1, 2), 'O');
		board.makeMove(new Coordinates(2, 1), 'O');
		board.makeMove(new Coordinates(3, 0), 'O');

		// Test if checkAntiDiagonal correctly identifies an anti-diagonal match
		assertTrue(board.checkAntiDiagonal('O'), "Four consecutive 'O' pieces in an anti-diagonal should return true");
	}
}
