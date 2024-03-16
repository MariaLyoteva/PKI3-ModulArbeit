package aufgabe2;

import aufgabe2.Board.Coordinates;
/**
 * The {@code IPlayer} interface represents a player in a game.
 * It defines methods to retrieve the player's mark, name, and the move the player decides to make on the game board.
 * Implementing classes should provide their own implementations for these methods.
 * 
 * @author Maria Lyoteva
 */
public interface IPlayer {
	char getMark();

	String getName();

	boolean decideMove(Board board, Coordinates coords) throws InvalidMoveException;
}
