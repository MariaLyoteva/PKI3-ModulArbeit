package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe1.Board;
import aufgabe1.Board.Coordinates;
import aufgabe1.HumanPlayer;
import aufgabe1.InvalidMoveException;

class HumanPlayerTest {

	private Board board;
	private HumanPlayer player;
	private ByteArrayInputStream in;

	@BeforeEach
	void setUp() {
		board = new Board(3, 3);
	}

	@Test
	void testValidMove() throws InvalidMoveException {
		// Simulate user input for row 1 and column 1
		in = new ByteArrayInputStream("1 1".getBytes());
		Scanner scanner = new Scanner(in);
		player = new HumanPlayer("Alice", 'X', scanner);

		Coordinates coords = new Board.Coordinates(0, 0);
		assertTrue(player.decideMove(board, coords));
		scanner.close();
	}

	@Test
	void testInValidMove() throws InvalidMoveException {
		// Simulate user
		in = new ByteArrayInputStream("s d".getBytes());
		Scanner scanner = new Scanner(in);
		player = new HumanPlayer("Alice", 'X', scanner);

		Coordinates coords = new Board.Coordinates(0, 0);
		InvalidMoveException thrown = assertThrows(InvalidMoveException.class, () -> player.decideMove(board, coords),
				"Invalid input. Please enter two integers separated by a space.");

		assertEquals("Invalid input. Please enter two integers separated by a space.", thrown.getMessage());
		scanner.close();
	}

	@Test
	void testInValidMove1() throws InvalidMoveException {
		// Simulate user
		in = new ByteArrayInputStream("1 d".getBytes());
		Scanner scanner = new Scanner(in);
		player = new HumanPlayer("Alice", 'X', scanner);

		Coordinates coords = new Board.Coordinates(0, 0);
		InvalidMoveException thrown = assertThrows(InvalidMoveException.class, () -> player.decideMove(board, coords),
				"Invalid input. Please enter two integers separated by a space.");

		assertEquals("Invalid input. Please enter two integers separated by a space.", thrown.getMessage());
		scanner.close();
	}

}
