package aufgabe2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * /** The {@code Main} class represents the entry point of the game
 * application. It initializes the game - providing two modes the user can
 * choose from - Human vs Human and AI vs Human.
 * 
 * @author Maria Lyoteva
 */
public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Select game mode:");
		System.out.println("1. Human vs Human");
		System.out.println("2. AI vs Human");

		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			playHumanVsHuman();
			break;
		case 2:
			playAIVsHuman();
			break;
		default:
			System.out.println("Invalid choice");
			break;
		}

	}

	/**
	 * The method implements the logic of the Human vs Human mode of the game. The
	 * board is initialized with a size selected by the user. The size may be from
	 * 4x4 up to 9x9 inclusive. (Sizes like 4x5 are also allowed). The two players
	 * provide their data (names and chosen marks) and then they take turns until
	 * the game is over.
	 */
	public static void playHumanVsHuman() {
		// Board initialization
		int rows = getValidNumber("Enter the number of rows for the board (4-9): ");
		int columns = getValidNumber("Enter the number of columns for the board (4-9): ");
		Board board = new Board(rows, columns);

		// Getting the info of the players
		HumanPlayer player1 = GameUtilsHumanOnly.getPlayerInfo(1);
		HumanPlayer player2 = GameUtilsHumanOnly.getPlayerInfo(2);
		HumanPlayer currentPlayer = player1;

		boolean gameOver = false;
		// The two players take turns until the game is over - either with a win or with
		// a tie
		while (!gameOver) {
			gameOver = GameUtilsHumanOnly.playTurn(board, currentPlayer);
			currentPlayer = (currentPlayer == player1) ? player2 : player1;
		}
	}

	/**
	 * This method implements the AI VS Human mode of the game. THe marks and the
	 * names of the two players are chosen by default. Player 1 is always the human
	 * player. The two players take turns until the game is over.
	 * 
	 */
	private static void playAIVsHuman() {
		// AI vs Human logic
		GameUtilsAIHuman game = GameUtilsAIHuman.setUpGame();
		while (true) {
			game.board.printBoard();
			GameUtilsAIHuman.playTurn(game);
		}
	}

	/**
	 * The method checks if the input given by the user is a valid number or not.
	 * 
	 * @param message a message that we want to pass through the user with
	 *                instructions how the input should be made.
	 * @return returns the integer given by the user if it's valid (in the range
	 *         between 4 and 9 incl.). Otherwise it returns an
	 *         {@link InputMismatchException}
	 */
	public static int getValidNumber(String message) {
		int number;
		while (true) {
			System.out.println(message);
			try {
				number = scanner.nextInt();
				// Check if the number is within the range 1 to 9
				if (number >= 4 && number <= 9) {
					break; // Exit the loop if the number is valid
				} else {
					System.out.println("Invalid input. Please enter a number between 4 and 9.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Consume the invalid input
			}
		}
		return number;
	}
}
