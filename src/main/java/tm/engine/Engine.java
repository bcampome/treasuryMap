package tm.engine;

import tm.domain.*;

import java.util.List;
import java.util.Optional;

public class Engine {
    private Map map;
    private List<Adventurer> adventurers;

    public Engine(Map map, List<Adventurer> adventurers) {
        this.map = map;
        this.adventurers = adventurers;
    }

    public void run() {

        while (anAdventurerHasNextInstruction()) {
            adventurers.forEach(this::applyCurrentInstruction);
        }
    }

    private void applyCurrentInstruction(Adventurer adventurer) {
        Position currentPosition = adventurer.getCurrentPosition();
        if (treasuryAtPosition(currentPosition)) {
            Optional<ItemMap> retrieve = map.retrieve(currentPosition.getColumn(), currentPosition.getLine());
            if(retrieve.isPresent() && retrieve.get() instanceof Treasury treasury){
                adventurer.handleTreasury(treasury.getSize());
                return; // IN_PAUSE
            }
        }
        Optional<Position> desiredPosition = adventurer.getDesiredPosition();
        Optional<Instruction> instruction =
                adventurer.applyNextInstruction();
        if (instruction.isPresent() && instruction.get().equals(Instruction.MOVE_FORWARD)) {
            desiredPosition.ifPresent(position -> {
                if (map.mayContain(position) && !mountainAtPosition(position)) {
                    adventurer.setCurrentPosition(position);
                }
            });
        }
    }

    private boolean treasuryAtPosition(Position position) {
        Optional<ItemMap> item = this.map.getItem(position.getColumn(), position.getLine());
        return item.isPresent() && (item.get() instanceof Treasury);
    }


    private boolean mountainAtPosition(Position position) {
        Optional<ItemMap> item = this.map.getItem(position.getColumn(), position.getLine());
        return item.isPresent() && (item.get() instanceof Mountain);
    }

    private boolean anAdventurerHasNextInstruction() {
        return this.adventurers.stream().anyMatch(adventurer -> !adventurer.nextInstructions().isEmpty());
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }
}
