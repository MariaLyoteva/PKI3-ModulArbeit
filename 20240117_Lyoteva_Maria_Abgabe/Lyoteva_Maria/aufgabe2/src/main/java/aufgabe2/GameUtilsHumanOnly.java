package aufgabe2;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * The {@code GameUtilsHumanOnly} represents the game logic when it's played
 * only by two human players.
 * 
 * @author Maria Lyoteva
 */
public class GameUtilsHumanOnly {
	private static Scanner scanner = new Scanner(System.in);
	public static Set<Character> chosenMarks = new HashSet<>();

	/**
	 * The method creates a new player with a name and a mark.
	 * 
	 * @param pNum this is the number of the player
	 * @return a HumanPlayer object.
	 */
	public static HumanPlayer getPlayerInfo(int pNum) {
		System.out.println("Player " + pNum + ", enter your name: ");
		String playerName = scanner.nextLine();
		char playerMark = getPlayerMark(playerName);
		return new HumanPlayer(playerName, playerMark);
	}

	/**
	 * The method gets the mark that the player has chosen to play with. If the mark
	 * has already been chosen by another player, an
	 * {@link IllegalArgumentException} is thrown.Enter should be pressed in order
	 * to enter a new mark.
	 * 
	 * @param playerName the name that the player has chosen
	 * @return a char - the mark chosen by the player
	 */
	public static char getPlayerMark(String playerName) {
		System.out.println(playerName + ", choose your mark (e.g., X): ");
		try {
			char mark = scanner.nextLine().charAt(0);
			// check if the mark is already taken
			if (!chosenMarks.add(mark)) {
				throw new IllegalArgumentException(
						"This mark is already taken. Please choose a different mark. Press Enter");
			}
			return mark;
		} catch (InputMismatchException | StringIndexOutOfBoundsException | IllegalArgumentException ex) {
			System.out.println("Invalid input: " + ex.getMessage());
			scanner.nextLine(); // Clear the buffer
			return getPlayerMark(playerName);
		}
	}

	/**
	 * The method implements the logic of how turns between the two players are
	 * being taken.
	 * 
	 * @param board  takes the current version of the board
	 * @param player the player that has o make a move
	 * @return a boolean - true if the game is over and false if the board is not
	 *         full yet and nobody has won till now.
	 */
	public static boolean playTurn(Board board, HumanPlayer player) {
		board.printBoard();
		Board.Coordinates coords = setCoordinates(player);

		// Make a move and check the game's state
		for (int rw = board.getRows() - 1; rw >= 0; rw--) {
			if (board.makeMove(coords, player.getMark())) {
				if (board.win(player.getMark())) {
					board.printBoard();
					System.out.println(player.getName() + " wins!");
					return true; // Game ends
				} else if (board.isFull()) {
					board.printBoard();
					System.out.println("It's a tie!");
					return true; // Game ends
				}
				return false; // Continue game
			}
		}
		return false;
	}

	/**
	 * The method implements choosing a row and a column and setting them as
	 * coordinates. If the coordinates are invalid it throws either an
	 * {@link IllegalArgumentException } , {@link InputMismatchException} or
	 * {@link IllegalArgumentException} depending on the scenario.
	 * 
	 * @param p an object of the HumanPlayer type.
	 * @return a new Board.Coordinates object
	 */
	public static Board.Coordinates setCoordinates(HumanPlayer p) {
		System.out.println(p.getName() + " (" + p.getMark() + "), choose a row and column: ");
		try {
			int row = scanner.nextInt() - 1; // Adjusting for 0-based indexing
			int col = scanner.nextInt() - 1; // Adjusting for 0-based indexing
			scanner.nextLine(); // Clear the buffer
			if (col < 0 || col >= 10 || row < 0 || row >= 10) {
				throw new IllegalArgumentException("Row and column number must be between 1 and 9.");
			}
			return new Board.Coordinates(row, col);
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a number.");
			scanner.nextLine(); // Clear the buffer
			return setCoordinates(p);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return setCoordinates(p);
		}
	}

	// Created for testing purposes
	public static void setScanner(Scanner customScanner) {
		scanner = customScanner;
	}

	public static void setPlayerMark(Character mark) {
		chosenMarks.add(mark);
	}
}
