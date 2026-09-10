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

    @Test
    public void getResponse_commandsFlow_executesSuccessfully() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        bot.getResponse("todo read book");
        bot.getResponse("deadline essay /by 2026-11-20 23:59");
        bot.getResponse("event gala /from 2026-12-01 18:00 /to 2026-12-01 22:00");

        String listResponse = bot.getResponse("list");
        assertTrue(listResponse.contains("1.[T][ ] read book"));
        assertTrue(listResponse.contains("2.[D][ ] essay"));
        assertTrue(listResponse.contains("3.[E][ ] gala"));

        String markResponse = bot.getResponse("mark 1");
        assertTrue(markResponse.contains("Nice! I've marked this task as done:"));
        assertTrue(markResponse.contains("[T][X] read book"));

        String unmarkResponse = bot.getResponse("unmark 1");
        assertTrue(unmarkResponse.contains("OK, I've marked this task as not done yet:"));
        assertTrue(unmarkResponse.contains("[T][ ] read book"));

        String findResponse = bot.getResponse("find essay");
        assertTrue(findResponse.contains("Here are the matching tasks in your list:"));
        assertTrue(findResponse.contains("essay"));

        String deleteResponse = bot.getResponse("delete 1");
        assertTrue(deleteResponse.contains("Noted. I've removed this task:"));

        String exitResponse = bot.getResponse("bye");
        assertTrue(exitResponse.contains("GET OUT OF MY SWAMP!"));
    }

    @Test
    public void isExit_byeCommand_returnsTrue() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        assertTrue(bot.isExit("bye"));
        assertFalse(bot.isExit("list"));
    }
}
