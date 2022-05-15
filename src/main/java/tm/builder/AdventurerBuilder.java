package tm.builder;

import tm.domain.Adventurer;
import tm.domain.Direction;
import tm.domain.Position;

import java.util.List;

public class AdventurerBuilder {

    static public List<Adventurer> build(String input) {
        return input.lines().map(AdventurerBuilder::convertLineToAdventurer).toList();
    }

    private static Adventurer convertLineToAdventurer(String input) {
        String[] config = input.split(" ");
        String name = config[0];
        Direction initialDirection = Direction.getDirectionByValue(config[2]);
        Position initialPosition = extractPosition(config[1]);

        Adventurer adventurer = new Adventurer(name, initialDirection);
        adventurer.setCurrentPosition(initialPosition);
        adventurer.addInstructions(config[3]);
        return adventurer;
    }

    private static Position extractPosition(String input) {
        String[] split = input.split("-");
        return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

}
