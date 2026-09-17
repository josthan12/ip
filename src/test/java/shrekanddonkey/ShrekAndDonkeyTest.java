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
        assertTrue(response.contains("What are ye doing in my swamp?!"));
    }

    @Test
    public void getResponse_validTodo_returnsTaskAddedMessage() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        String response = bot.getResponse("todo read book");
        assertTrue(response.contains("Alright, added to the swamp list:"));
        assertTrue(response.contains("read book"));
    }

    @Test
    public void getResponse_sortCommand_ordersTasksByDate() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        bot.getResponse("todo read book");
        bot.getResponse("deadline submit report /by 2026-10-15 18:00");

        String response = bot.getResponse("sort");

        assertTrue(response.indexOf("1.[D][ ] submit report") < response.indexOf("2.[T][ ] read book"));
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
        assertTrue(markResponse.contains("Shrek approves! Marked as done:"));
        assertTrue(markResponse.contains("[T][X] read book"));

        String unmarkResponse = bot.getResponse("unmark 1");
        assertTrue(unmarkResponse.contains("Back in the swamp it goes! Unmarked:"));
        assertTrue(unmarkResponse.contains("[T][ ] read book"));

        String findResponse = bot.getResponse("find essay");
        assertTrue(findResponse.contains("Donkey found these in the swamp:"));
        assertTrue(findResponse.contains("essay"));

        String deleteResponse = bot.getResponse("delete 1");
        assertTrue(deleteResponse.contains("Poof! Banished from the swamp:"));

        String exitResponse = bot.getResponse("bye");
        assertTrue(exitResponse.contains("GET OUT OF MY SWAMP!"));
    }

    @Test
    public void isExit_byeCommand_returnsTrue() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        assertTrue(bot.isExit("bye"));
        assertFalse(bot.isExit("list"));
    }

    @Test
    public void isLastResponseError_invalidCommand_returnsTrue() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        bot.getResponse("invalidcommand123");
        assertTrue(bot.isLastResponseError());
    }

    @Test
    public void isLastResponseError_validCommand_returnsFalse() {
        ShrekAndDonkey bot = new ShrekAndDonkey();
        bot.getResponse("todo read book");
        assertFalse(bot.isLastResponseError());
    }
}
