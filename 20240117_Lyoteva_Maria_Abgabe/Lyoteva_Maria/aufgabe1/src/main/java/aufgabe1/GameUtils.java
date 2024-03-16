package aufgabe1;

import aufgabe1.Board.Coordinates;

/**
 * This class holds together the basic logic and the set up of the game
 * 
 * @author Maria Lyoteva
 */
public class GameUtils {
	private static final int BOARD_ROW = 3;
	private static final int BOARD_COLUMN = 3;

	private static final char AI_MARK = 'O';
	private static final char HUMAN_MARK = 'X';

	private static final String AI_NAME = "AI";
	private static final String HUMAN_NAME = "Human";

	final Board board;
	public final HumanPlayer humanPlayer;
	public final AIPlayer aiPlayer;
	boolean isHumanTurn;

	public GameUtils(final Board board, final HumanPlayer hPlayer, final AIPlayer aPlayer) {
		this.board = board;
		this.humanPlayer = hPlayer;
		this.aiPlayer = aPlayer;
		this.isHumanTurn = true;
	}

	/**
	 * Sets up the game with the configurations needed.
	 * 
	 * @return an object from the GameUtils class
	 */
	public static GameUtils setUpGame() {
		Board board = new Board(BOARD_ROW, BOARD_COLUMN);
		HumanPlayer humanPlayer = new HumanPlayer(HUMAN_NAME, HUMAN_MARK);
		AIPlayer aiPlayer = new AIPlayer(AI_NAME, AI_MARK);
		return new GameUtils(board, humanPlayer, aiPlayer);
	}

	/**
	 * This method is responsible for managing the taking of the turns between the
	 * two players. It also checks if a move is considered valid or not.
	 * 
	 * @param game an object of the GameUtils class, represents a single game.
	 */
	public static void playTurn(GameUtils game) {
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
