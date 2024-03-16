package aufgabe1;
import aufgabe1.Board.Coordinates;
/**
 * The {@code IPlayer} interface represents a player in a game.
 * It defines methods to retrieve the player's mark, name, and make a move on the game board.
 * Implementing classes should provide their own implementations for these methods.
 * 
 * @author Maria Lyoteva
 */
public interface IPlayer {
	char getMark();

	String getName();

	boolean decideMove(Board board, Coordinates coords) throws InvalidMoveException;
}
