package aufgabe2;


@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	public InvalidMoveException(String message) {
		super(message);
	}
}