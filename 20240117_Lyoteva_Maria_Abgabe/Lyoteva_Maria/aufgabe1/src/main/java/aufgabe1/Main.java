package aufgabe1;

/**
 * The {@code Main} class represents the entry point of the game application.
 * It initializes the game, displays the game board, and allows players to take turns until the game ends.
 * 
 * @author Maria Lyoteva
 */
class Main {
	public static void main(String[] args) {
		System.out.println("---------Welcome to the Tic Tac Toe game ---------\n");
		GameUtils game = GameUtils.setUpGame();
		while (true) {
			game.board.printBoard();
			System.out.println();
			GameUtils.playTurn(game);
		}
	}
}
