package aufgabe2_tests;

//import org.junit.jupiter.api.*;

import aufgabe2.Main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @BeforeEach
    void setUpInput() {
        // This method will run before each test
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void testIfCrashesWithInvalidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\n10\n5\n".getBytes());
        System.setIn(in);

        Assertions.assertDoesNotThrow(() -> {
            Main.getValidNumber("Enter a number: ");
        });
    }
    
    
}

