package tm.domain;

import java.util.*;

public class Adventurer {
    private final String name;
    private final Queue<Instruction> instructions = new LinkedList<>();
    private Direction direction;
    private Position position;

    public Adventurer(String name, String direction) {
        this.name = name;
        this.direction = Optional
                .ofNullable(Direction.getDirectionByValue(direction))
                .orElse(Direction.East);
    }


    public void addInstructions(String instructions) {
        Arrays.stream(instructions.split("")).
                map(Instruction::getInstructionByValue)
                .forEach(this.instructions::add);
    }

    public Optional<Instruction> applyNextInstruction() {
        Optional<Instruction> instruction = Optional.ofNullable(instructions.poll());

        if (instruction.isPresent()) {
            switch (instruction.get()) {
                case ROTATE_RIGHT -> this.direction = direction.getNextDirectionInClockwise();
                case ROTATE_LEFT -> this.direction = direction.getNextDirectionInAntiClockwise();
            }
        }
        return instruction;
    }

    public Queue<Instruction> nextInstructions() {
        return instructions;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setCurrentPosition(Position position) {
        this.position = position;
    }

    public Optional<Position> getDesiredPosition() {
     return null;// todo to complete
    }
}
