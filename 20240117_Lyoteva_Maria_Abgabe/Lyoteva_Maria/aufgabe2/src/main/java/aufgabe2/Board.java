package aufgabe2;

/**
 * This class represents the board of the game.
 * 
 * @author Maria Lyoteva
 */
public class Board {
	private int rows;
	private int cols;
	private char[][] board;

	public Board(int rows, int cols) {
		this.rows=rows;
		this.cols=cols;
		initializeBoard();
	}
	
	/**
	 * Specifies the board's elements.
	 *
	 */
	public enum LineType {
		ROW, COLUMN
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

	
	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}
	
	void printBoard() {
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
	boolean isFull() {
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
	 * Checks if there is a "full match" of 4 in a line (row or column).
	 * 
	 * @param piece - the chosen player's mark
	 * @param lineType specifies whether the line is a row or a column
	 * @return by default returns false
	 */
	public boolean allMatchLine(char piece, LineType lineType) {
		int limit = (lineType == LineType.ROW) ? cols - 3 : rows - 3;

		for (int i = 0; i < (lineType == LineType.ROW ? rows : cols); i++) {
			for (int j = 0; j < limit; j++) {
				boolean allMatch = true;
				for (int k = 0; k < 4; k++) {
					if ((lineType == LineType.ROW && board[i][j + k] != piece)
							|| (lineType == LineType.COLUMN && board[j + k][i] != piece)) {
						allMatch = false;
						break;
					}
				}
				if (allMatch) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a match of 4 is made on the main diagonal.
	 * @param piece - the chosen player's mark
	 * @return false by default
	 */
	public boolean checkDiagonal(char piece) {
		for (int cl = 0; cl < cols - 3; cl++) {
			for (int rw = 0; rw < rows - 3; rw++) {
				if (board[rw][cl] == piece && board[rw + 1][cl + 1] == piece && board[rw + 2][cl + 2] == piece
						&& board[rw + 3][cl + 3] == piece) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if a match of 4 is made on the anti diagonal.
	 * @param piece - the chosen player's mark
	 * @return false by default
	 */
	public boolean checkAntiDiagonal(char piece) {
		for (int cl = 3; cl < cols; cl++) {
			for (int rw = 0; rw < rows - 3; rw++) {
				if (board[rw][cl] == piece && board[rw + 1][cl - 1] == piece && board[rw + 2][cl - 2] == piece
						&& board[rw + 3][cl - 3] == piece) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if there is a win.
	 * 
	 * @param piece the chosen player's mark
	 * @return returns true when there is a match of 4 found somewhere in the row,
	 *         columns or on the diagonals
	 */
	public boolean win(char piece) {
		return (allMatchLine(piece, LineType.ROW) || allMatchLine(piece, LineType.COLUMN) || checkDiagonal(piece)
				|| checkAntiDiagonal(piece));
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
	 * This inner class represents the coordinates of a cell in the board through row and column
	 */
	public static class Coordinates {
		public int row;
		public int col;

	   public Coordinates(int row, int col) {
			this.row = row;
			this.col = col;

		}
	}
}

