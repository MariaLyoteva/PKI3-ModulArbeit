package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe1.Board;
import aufgabe1.Board.Coordinates;

class BoardTest {

	private Board board;

	@BeforeEach
	public void setUp() {
		board = new Board(3, 3);
	}

	@Test
	public void testAllMatchRows() {
		board.makeMove(new Coordinates(0, 0), 'X');
		board.makeMove(new Coordinates(0, 1), 'X');
		board.makeMove(new Coordinates(0, 2), 'X');
		assertTrue(board.allMatchLine('X', Board.LineType.ROW));
	}

	@Test
	public void testAllMatchCols() {
		board.makeMove(new Board.Coordinates(0, 0), 'X');
		board.makeMove(new Board.Coordinates(1, 0), 'X');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		assertTrue(board.allMatchLine('X', Board.LineType.COLUMN));
	}

	@Test
	public void testAllMatchFalseCols() {
		board.makeMove(new Board.Coordinates(0, 0), 'X');
		board.makeMove(new Board.Coordinates(1, 0), 'O');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		assertFalse(board.allMatchLine('X', Board.LineType.COLUMN));
	}

	@Test
	public void testAllMatchFalseRows() {
		board.makeMove(new Board.Coordinates(0, 0), 'X');
		board.makeMove(new Board.Coordinates(1, 0), 'X');
		board.makeMove(new Board.Coordinates(2, 0), 'O');
		assertFalse(board.allMatchLine('X', Board.LineType.COLUMN));
	}

	@Test
	public void testDiagonal() {
		board.makeMove(new Board.Coordinates(0, 0), 'X');
		board.makeMove(new Board.Coordinates(1, 1), 'X');
		board.makeMove(new Board.Coordinates(2, 2), 'X');
		assertTrue(board.checkDiagonals('X'));
	}

	@Test
	public void testAntiDiagonal() {
		board.makeMove(new Board.Coordinates(0, 2), 'X');
		board.makeMove(new Board.Coordinates(1, 1), 'X');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		assertTrue(board.checkDiagonals('X'));
	}

	@Test
	public void testFalseAntiDiagonal() {
		board.makeMove(new Board.Coordinates(0, 2), 'X');
		board.makeMove(new Board.Coordinates(1, 1), 'O');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		assertFalse(board.checkDiagonals('X'));
	}

	@Test
	public void testFalseIsFull() {
		assertFalse(board.isFull());
	}

	@Test
	public void testIsFull() {
		board.makeMove(new Board.Coordinates(0, 0), 'O');
		board.makeMove(new Board.Coordinates(1, 1), 'O');
		board.makeMove(new Board.Coordinates(2, 2), 'X');
		board.makeMove(new Board.Coordinates(0, 2), '0');
		board.makeMove(new Board.Coordinates(1, 0), 'X');
		board.makeMove(new Board.Coordinates(2, 1), 'O');
		board.makeMove(new Board.Coordinates(0, 1), 'X');
		board.makeMove(new Board.Coordinates(1, 2), 'O');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		assertTrue(board.isFull());
	}

	@Test
	public void testWin() {
		board.makeMove(new Board.Coordinates(0, 2), 'O');
		board.makeMove(new Board.Coordinates(1, 2), 'X');
		board.makeMove(new Board.Coordinates(1, 1), 'O');
		board.makeMove(new Board.Coordinates(2, 0), 'X');
		board.makeMove(new Board.Coordinates(0, 0), 'O');
		board.makeMove(new Board.Coordinates(2, 2), 'X');
		board.makeMove(new Board.Coordinates(0, 1), 'O');
		assertTrue(board.win('O'));
	}

}
