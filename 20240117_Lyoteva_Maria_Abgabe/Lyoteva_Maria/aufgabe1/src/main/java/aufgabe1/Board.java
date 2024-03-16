package aufgabe1;

/**
 * This class represents the board of a TicTacToe game.
 * 
 * @author Maria Lyoteva
 */
public class Board {
	private int rows;
	private int cols;
	private char[][] board;

	/**
	 * Specifies the board's elements.
	 *
	 */
	public enum LineType {
		ROW, COLUMN
	}

	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		initializeBoard();
	}

	private void initializeBoard() {
		this.board = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = '-';
			}
		}
	}

	char[][] getBoard() {
		return board;
	}

	int getCols() {
		return cols;
	}

	int getRows() {
		return rows;
	}

	public void printBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Checks whether the board is full or not.
	 * 
	 * @return returns true by default
	 */
	public boolean isFull() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if there is a "full match" of 3 in a line (row or column).
	 * 
	 * @param piece    the chosen player's mark
	 * @param lineType specifies whether the line is a row or a column
	 * @return by default returns false
	 */
	public boolean allMatchLine(char piece, LineType lineType) {
		boolean allMatch = false;
		for (int i = 0; i < 3; i++) {
			allMatch = true;
			for (int j = 0; j < 3; j++) {
				if ((lineType == LineType.ROW && board[i][j] != piece)
						|| (lineType == LineType.COLUMN && board[j][i] != piece)) {
					allMatch = false;
					break;
				}
			}
			if (allMatch) {
				return true;
			}
		}
		return allMatch;
	}

	/**
	 * Checks if a match of 3 is made on one of the diagonals.
	 * 
	 * @param piece the chosen player's mark
	 * @return returns true when there is a match of 3 made on one of the diagonals
	 */
	public boolean checkDiagonals(char piece) {
		boolean allMatchMajor = true; // main diagonal
		boolean allMatchMinor = true; // anti diagonal

		for (int i = 0; i < 3; i++) {
			if (board[i][i] != piece) {
				allMatchMajor = false;
			}
			if (board[i][2 - i] != piece) {
				allMatchMinor = false;
			}
		}
		return allMatchMajor || allMatchMinor;
	}

	/**
	 * Checks if there is a win.
	 * 
	 * @param piece the chosen player's mark
	 * @return returns true when there is a match of 3 found somewhere in the row,
	 *         columns or on the diagonals
	 */
	public boolean win(char piece) {
		return (allMatchLine(piece, LineType.ROW) || allMatchLine(piece, LineType.COLUMN) || checkDiagonals(piece));
	}

	public boolean makeMove(Coordinates coords, char piece) {
		if (coords.row >= 0 && coords.row < rows && coords.col >= 0 && coords.col < cols
				&& board[coords.row][coords.col] == '-') {
			board[coords.row][coords.col] = piece;
			return true; // Move was successful
		}
		return false; // Move was not successful
	}

	/**
	 * Represents the coordinates of a cell in the board through row and column
	 */
	public static class Coordinates {
		int row;
		int col;

		public Coordinates(int row, int col) {
			this.row = row;
			this.col = col;

		}
	}
}
