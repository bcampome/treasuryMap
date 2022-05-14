package tm.domain;

import java.util.*;

public class Adventurer {
    private final String name;
    private final Queue<Instruction> instructions = new LinkedList<>();
    private Direction direction;
    private Position position;
    private int treasuresSum = 0;

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
        System.out.println("Current Position : " + position);
        Optional<Instruction> instruction = Optional.ofNullable(instructions.peek());
        if(instruction.isPresent() && instruction.get().equals(Instruction.MOVE_FORWARD) ){
            switch (direction){
                case North -> {
                    return Optional.of(position.top());
                }
                case South -> {
                    return Optional.ofNullable(position.down());
                }
                case East -> {
                    return Optional.ofNullable(position.right());
                }
                case West -> {
                    System.out.println("Neext Position : " + position.left());

                    return Optional.ofNullable(position.left());
                }
                default -> throw new AssertionError();
            }
        }
     return Optional.empty();
    }

    public void handleTreasury(int treasury) {
        this.treasuresSum = treasuresSum + treasury;
    }

    public int getSumOfTreasury() {
        return this.treasuresSum;

    }
}
