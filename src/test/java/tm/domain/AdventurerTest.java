package tm.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    @Test
    public void should_applyRotateRightInstruction_when_received_D_asInputInstruction() {
        // Given
        Adventurer adventurer = new Adventurer("Joe", "E");
        adventurer.addInstructions("D");
        Optional<Instruction> instruction = adventurer.applyNextInstruction();
        assertTrue(instruction.isPresent());
        assertEquals(Instruction.ROTATE_RIGHT, instruction.get());
    }

    @Test
    void should_applySuccessiveInstructions_when_receivedComplexCommand() {
        Adventurer adventurer = new Adventurer("Joe", "E");
        adventurer.addInstructions("DGA");

        Queue<Instruction> instructions = adventurer.nextInstructions();
        List<Instruction> expected = Arrays.asList(Instruction.ROTATE_RIGHT, Instruction.ROTATE_LEFT,
                Instruction.MOVE_FORWARD);
        assertEquals(expected, instructions);

    }

    @Test
    void should_haveDirectionToEast_when_existWithNorthDirectionAndGetInstructionRotationRight() {
        Adventurer adventurer = new Adventurer("Joe", "N");
        adventurer.addInstructions(Instruction.ROTATE_RIGHT.getValue());

        adventurer.applyNextInstruction();
        assertEquals(Direction.East, adventurer.getDirection());
    }

    @Test
    void should_haveDirectionToWest_when_existWithNorthDirectionAndGetInstructionRotationLeft() {
        Adventurer adventurer = new Adventurer("Joe", "N");
        adventurer.addInstructions(Instruction.ROTATE_LEFT.getValue());

        adventurer.applyNextInstruction();
        assertEquals(Direction.West, adventurer.getDirection());
    }

}