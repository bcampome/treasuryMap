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
        Adventurer adventurer = new Adventurer("Joe", Direction.East);
        adventurer.addInstructions("D");
        Optional<Instruction> instruction = adventurer.applyNextInstruction();
        assertTrue(instruction.isPresent());
        assertEquals(Instruction.ROTATE_RIGHT, instruction.get());
    }

    @Test
    void should_applySuccessiveInstructions_when_receivedComplexCommand() {
        Adventurer adventurer = new Adventurer("Joe",  Direction.East);
        adventurer.addInstructions("DGA");

        Queue<Instruction> instructions = adventurer.nextInstructions();
        List<Instruction> expected = Arrays.asList(Instruction.ROTATE_RIGHT, Instruction.ROTATE_LEFT,
                Instruction.MOVE_FORWARD);
        assertEquals(expected, instructions);

    }

    @Test
    void should_haveDirectionToEast_when_existWithNorthDirectionAndGetInstructionRotationRight() {
        Adventurer adventurer = new Adventurer("Joe",  Direction.East);
        adventurer.addInstructions(Instruction.ROTATE_RIGHT.getValue());

        adventurer.applyNextInstruction();
        assertEquals(Direction.East, adventurer.getDirection());
    }

    @Test
    void should_haveDirectionToWest_when_existWithNorthDirectionAndGetInstructionRotationLeft() {
        Adventurer adventurer = new Adventurer("Joe",  Direction.East);
        adventurer.addInstructions(Instruction.ROTATE_LEFT.getValue());

        adventurer.applyNextInstruction();
        assertEquals(Direction.West, adventurer.getDirection());
    }

    @Test
    void should_returnNoPosition_when_nextInstructionIsARotateInstruction(){
        Adventurer adventurer = new Adventurer("Joe",  Direction.East);
        adventurer.addInstructions(Instruction.ROTATE_RIGHT.getValue());
        Optional<Position> desiredPosition = adventurer.getDesiredPosition();
        assertTrue(desiredPosition.isEmpty());
    }

    @Test
    void should_returnOverheadPosition_when_nextInstructionIsMoveForwardAndDirectionEqualNorth(){
        Adventurer adventurer = new Adventurer("Joe",  Direction.East);
        adventurer.addInstructions(Instruction.MOVE_FORWARD.getValue());
        adventurer.setCurrentPosition(new Position(1,1));

        Optional<Position> desiredPosition = adventurer.getDesiredPosition();

        assertTrue(desiredPosition.isPresent());
        assertEquals(new Position(1,0),desiredPosition.get());
    }

    @Test
    void should_returnOverheadPosition_when_nextInstructionIsMoveForwardAndDirectionEqualEst(){
        Adventurer adventurer = new Adventurer("Joe",  Direction.East);
        adventurer.addInstructions(Instruction.MOVE_FORWARD.getValue());
        adventurer.setCurrentPosition(new Position(1,1));

        Optional<Position> desiredPosition = adventurer.getDesiredPosition();

        assertTrue(desiredPosition.isPresent());
        assertEquals(new Position(2,1),desiredPosition.get());
    }

    @Test
    void should_returnOverheadPosition_when_nextInstructionIsMoveForwardAndDirectionEqualSouth(){
        Adventurer adventurer = new Adventurer("Joe",  Direction.South);
        adventurer.addInstructions(Instruction.MOVE_FORWARD.getValue());
        adventurer.setCurrentPosition(new Position(1,1));

        Optional<Position> desiredPosition = adventurer.getDesiredPosition();

        assertTrue(desiredPosition.isPresent());
        assertEquals(new Position(1,2),desiredPosition.get());
    }

    @Test
    void should_returnOverheadPosition_when_nextInstructionIsMoveForwardAndDirectionEqualWest(){
        Adventurer adventurer = new Adventurer("Joe", Direction.West);
        adventurer.addInstructions(Instruction.MOVE_FORWARD.getValue());
        adventurer.setCurrentPosition(new Position(1,1));

        Optional<Position> desiredPosition = adventurer.getDesiredPosition();

        assertTrue(desiredPosition.isPresent());
        assertEquals(new Position(0,1),desiredPosition.get());
    }

    @Test
    void should_saveAmountOfTreasury_when_handleATreasury(){
        Adventurer adventurer = new Adventurer("Joe",  Direction.North);

        adventurer.handleTreasury(5);

        assertEquals(5, adventurer.getSumOfTreasury());
    }

    @Test
    void should_saveAmountOfAllTreasures_when_handleSeveralTreasures(){
        Adventurer adventurer = new Adventurer("Joe", Direction.North);

        adventurer.handleTreasury(5);
        adventurer.handleTreasury(3);

        assertEquals(8, adventurer.getSumOfTreasury());
    }




}