package tm.builder;

import org.junit.jupiter.api.Test;
import tm.domain.Adventurer;
import tm.domain.Direction;
import tm.domain.Instruction;
import tm.domain.Position;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tm.domain.Instruction.*;

class AdventurerBuilderTest {

    @Test
    void should_createAdventurer_when_receivedAdventurerDescription() throws IOException {
        String input = new String(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("./adventurers/one_adventurer.txt")).readAllBytes());

        List<Adventurer> adventurer = AdventurerBuilder.build(input);

        assertEquals(1,adventurer.size());
        Adventurer John = adventurer.get(0);
        assertEquals("John", John.getName());
        assertEquals(new Position(1,1),John.getCurrentPosition());
        assertEquals(Direction.East, John.getDirection());
        List<Instruction> expectedInstructions = Arrays.asList(MOVE_FORWARD, MOVE_FORWARD, ROTATE_RIGHT, MOVE_FORWARD, ROTATE_RIGHT, MOVE_FORWARD, ROTATE_LEFT, MOVE_FORWARD);
        assertEquals(expectedInstructions, John.nextInstructions());
    }

    @Test
    void should_createAdventurer_when_receivedTwoAdventurersDescription() throws IOException {
        String input = new String(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("./adventurers/two_adventurers.txt")).readAllBytes());

        List<Adventurer> adventurers = AdventurerBuilder.build(input);

        assertEquals(2, adventurers.size());

        Adventurer john = adventurers.get(0);
        assertEquals("John", john.getName());
        assertEquals(new Position(1,1),john.getCurrentPosition());
        assertEquals(Direction.East, john.getDirection());
        List<Instruction> expectedInstructionsJohn = Arrays.asList(MOVE_FORWARD, MOVE_FORWARD, ROTATE_RIGHT, MOVE_FORWARD, ROTATE_RIGHT, MOVE_FORWARD, ROTATE_LEFT, MOVE_FORWARD);
        assertEquals(expectedInstructionsJohn, john.nextInstructions());

        Adventurer joe = adventurers.get(1);
        assertEquals("Joe", joe.getName());
        assertEquals(new Position(2,3),joe.getCurrentPosition());
        assertEquals(Direction.North, joe.getDirection());
        List<Instruction> expectedInstructionsJoe = Arrays.asList(ROTATE_LEFT,MOVE_FORWARD,ROTATE_RIGHT,ROTATE_RIGHT,MOVE_FORWARD,MOVE_FORWARD,ROTATE_LEFT,ROTATE_LEFT);
        assertEquals(expectedInstructionsJoe, joe.nextInstructions());
    }
}