package shrekanddonkey;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the response generation and welcome message in {@link ShrekAndDonkey}.
 */
public class ShrekAndDonkeyTest {

    @Test
    public void getWelcomeMessage_returnsNonEmptyMessage() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        String welcome = bot.getWelcomeMessage();
        assertFalse(welcome.isEmpty());
        assertTrue(welcome.contains("ShrekAndDonkey"));
    }

    @Test
    public void getResponse_invalidCommand_returnsErrorMessage() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        String response = bot.getResponse("invalid command");
        assertTrue(response.contains("NO VALID INPUT GIVEN"));
    }

    @Test
    public void getResponse_validTodo_returnsTaskAddedMessage() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        String response = bot.getResponse("todo read book");
        assertTrue(response.contains("Got it. I've added this task:"));
        assertTrue(response.contains("read book"));
    }
}
