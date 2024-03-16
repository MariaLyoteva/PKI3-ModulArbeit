
package aufgabe2_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import aufgabe2.GameUtilsHumanOnly;
import aufgabe2.HumanPlayer;

import java.util.Scanner;

class GameUtilsHumanOnlyTest {

	@BeforeEach
	public void setUp() {
		// Reset chosenMarks before each test
		GameUtilsHumanOnly.chosenMarks.clear();
	}

	@Test
	void testGetPlayerMarkWithValidMark() {
		ByteArrayInputStream in = new ByteArrayInputStream("X\n".getBytes());
		Scanner scanner = new Scanner(in);
		GameUtilsHumanOnly.setScanner(scanner);

		char result = GameUtilsHumanOnly.getPlayerMark("Alice");
		assertEquals('X', result);

		scanner.close();
	}

	@Test
	void testGetPlayerMarkUnique() {
		String input = "X\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		GameUtilsHumanOnly.setScanner(new Scanner(System.in));

		char mark = GameUtilsHumanOnly.getPlayerMark("TestPlayer");
		assertEquals('X', mark, "Player should get their chosen mark if it's unique.");
	}

	@Test
	void testGetPlayerInfo() {
		String input = "Alice\nX\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		GameUtilsHumanOnly.setScanner(new Scanner(System.in));

		HumanPlayer player = GameUtilsHumanOnly.getPlayerInfo(1);
		assertNotNull(player, "getPlayerInfo should return a HumanPlayer object");
		assertEquals("Alice", player.getName(), "Player name should be 'Alice'");
		assertEquals('X', player.getMark(), "Player mark should be 'X'");
	}
}
