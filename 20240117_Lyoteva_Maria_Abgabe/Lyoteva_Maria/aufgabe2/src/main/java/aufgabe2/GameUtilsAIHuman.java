package aufgabe2;

import aufgabe2.Board.Coordinates;
import java.util.Scanner;

/**
 * The {@code GameUtilsAIHuman} class represents the rules and logic of a game
 * that's player between a human player and an AI player. The marks are set by
 * default and cannot be changed. However, the board size can be modified by the
 * user.
 * 
 * @author Maria Lyoteva
 */
public class GameUtilsAIHuman {
	private static final char AI_MARK = 'O';
	private static final char HUMAN_MARK = 'X';

	private static final String AI_NAME = "AI";
	private static final String HUMAN_NAME = "Human";

	final Board board;
	public final HumanPlayer humanPlayer;
	public final AIPlayer aiPlayer;
	boolean isHumanTurn;

	public GameUtilsAIHuman(final Board board, final HumanPlayer hPlayer, final AIPlayer aPlayer) {
		this.board = board;
		this.humanPlayer = hPlayer;
		this.aiPlayer = aPlayer;
		this.isHumanTurn = true;
	}

	/**
	 * Sets up the game with the configurations needed.
	 * 
	 * @return an object from the GameUtilsAIHuman class
	 */
	public static GameUtilsAIHuman setUpGame() {
		Scanner scanner = new Scanner(System.in); // Create a Scanner object

		System.out.println("Enter the number of rows for the board:");
		int rows = scanner.nextInt(); // Read user input for rows

		System.out.println("Enter the number of columns for the board:");
		int columns = scanner.nextInt(); // Read user input for columns
		scanner.close();
		Board board = new Board(rows, columns);
		HumanPlayer humanPlayer = new HumanPlayer(HUMAN_NAME, HUMAN_MARK);
		AIPlayer aiPlayer = new AIPlayer(AI_NAME, AI_MARK);
		return new GameUtilsAIHuman(board, humanPlayer, aiPlayer);
	}

	/**
	 * This method is responsible for managing the taking of the turns between the
	 * two players. It also checks if a move is considered valid or not. In case the
	 * move is not valid it catches an InvalidMoveException
	 * 
	 * @param game an object of the GameUtilsAIHuman class, represents a single
	 *             game.
	 */
	public static void playTurn(GameUtilsAIHuman game) {
		final Board board = game.board;
		final Coordinates coords = new Coordinates(0, 0);
		final IPlayer currentPlayer = game.isHumanTurn ? game.humanPlayer : game.aiPlayer; // checks whose turn it is
		boolean validMoveMade = false;

		while (!validMoveMade) {
			try {
				System.out.println(currentPlayer.getName() + "'s turn:");
				validMoveMade = currentPlayer.decideMove(board, coords);

				if (validMoveMade) {
					if (board.win(currentPlayer.getMark())) {
						board.printBoard();
						System.out.println(currentPlayer.getName() + " wins!");
						System.exit(0); // There is a win found, end of the game
					} else if (board.isFull()) {
						board.printBoard();
						System.out.println("The game is a tie.");
						System.exit(0); // Nobody won, it's a draw, end of the game
					}
				}
			} catch (InvalidMoveException e) {
				System.out.println(e.getMessage());
			}
		}
		game.isHumanTurn = !game.isHumanTurn; // Switch turns
	}
}
