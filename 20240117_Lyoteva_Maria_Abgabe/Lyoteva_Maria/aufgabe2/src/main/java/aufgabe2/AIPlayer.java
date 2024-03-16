package aufgabe2;

import java.util.Random;
import aufgabe2.Board.Coordinates;

/**
 * This class describes the behavior of an AI Player and is an implementation of
 * the {link IPlayer}
 * 
 * @author Maria Lyoteva
 */
public class AIPlayer implements IPlayer {
	private final char mark;
	private final String name;
	private final Random random = new Random();

	public AIPlayer(final String name, final char mark) {
		this.mark = mark;
		this.name = name;
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
	 * This method describes the decision that the AI Player takes when it's
	 * needed to make a move. The row and column are chosen randomly. If the board
	 * is already full and the AI Player still attempts making a move, it will throw
	 * an {@link InvalidMoveException}.
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

		int rows = board.getRows();
		int cols = board.getCols();
		while (true) {
			int rRow = random.nextInt(rows); 
			int rCol = random.nextInt(cols);
			if (board.getBoard()[rRow][rCol] == '-') {
				coords.row = rRow;
				coords.col = rCol;
				return board.makeMove(coords, getMark());
			}
		}
	}
}

