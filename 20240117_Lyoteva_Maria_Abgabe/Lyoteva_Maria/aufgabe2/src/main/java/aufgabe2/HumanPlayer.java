package aufgabe2;
	import java.util.InputMismatchException;
	import java.util.Scanner;

	import aufgabe2.Board.Coordinates;

	/**
	 * The class describes the behavior of a human player and is an implementation
	 * of the {@link IPlayer} interface.
	 * 
	 * @author Maria Lyoteva
	 */
	public class HumanPlayer implements IPlayer {
		private final char mark;
		private final String name;
		private final Scanner scanner;

		public HumanPlayer(final String name, final char mark) {
			this.name = name;
			this.mark = mark;
			this.scanner = new Scanner(System.in);
		}

		@Override
		public char getMark() {
			return mark;
		}

		@Override
		public String getName() {
			return name;
		}

		/**
		 * This method describes the decision that the human player takes when it's
		 * needed to make a move. The row and column are chosen by the user and entered
		 * through the console. If the board is already full and the human player still
		 * attempts making a move, it will throw an InvalidMoveException.
		 * 
		 * @return returns a true or false, based on whether the move is possible or
		 *         not. Attention: it uses the response from the makeMove method in the
		 *         {@link Board}!
		 */
		@Override
		public boolean decideMove(Board board, Coordinates coords) throws InvalidMoveException {
			if (board.isFull()) {
				throw new InvalidMoveException("The board is already full. The game is a tie.");
			}

			try {
				System.out.println("Choose a row and a column (1-3). The input should be separated by a blank space:");
				int row = scanner.nextInt();
				int col = scanner.nextInt();

				// Adjust the row and column values to be 0-based indices
				row--;
				col--;

				if (row >= 0 && row < board.getRows() && col >= 0 && col < board.getCols()
						&& board.getBoard()[row][col] == '-') {
					coords.row = row;
					coords.col = col;
					return board.makeMove(coords, getMark());
				} else {
					scanner.nextLine(); // Clears the buffer
					throw new InvalidMoveException("Invalid move. Please enter valid coordinates.");
					// The chosen coordinates are integers but aren't valid.
				}
			} catch (InputMismatchException e) {
				scanner.nextLine(); // Clears the buffer
				throw new InvalidMoveException("Invalid input. Please enter two integers separated by a space.");
				// The chosen coordinates are not integers - some other form of input was
				// attempted.
			}
		}
		// Constructor for testing, allowing injection of a Scanner object
				public HumanPlayer(final String name, final char mark, final Scanner scanner) {
					this.name = name;
					this.mark = mark;
					this.scanner = scanner;
				}
	}

