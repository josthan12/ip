package shrekanddonkey.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests parsing user inputs into command types and arguments in {@link Parser}.
 */
public class ParserTest {
    @Test
    public void parseCommandType_findCommand_returnsFind() {
        assertEquals(Parser.CommandType.FIND, Parser.parseCommandType("find book"));
        assertEquals(Parser.CommandType.FIND, Parser.parseCommandType("find"));
    }

    @Test
    public void getArguments_findCommand_returnsKeyword() {
        assertEquals("book", Parser.getArguments("find book", "find"));
        assertEquals("", Parser.getArguments("find", "find"));
    }
}
