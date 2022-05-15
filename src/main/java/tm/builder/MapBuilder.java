package tm.builder;

import tm.domain.Map;

import java.util.Optional;

public class MapBuilder {
    public static Map build(String input) {
        Optional<String> creationLine =
                input.lines().findFirst();
        if(creationLine.isEmpty()){
            throw new IllegalArgumentException("File is Empty");
        }

        Map map = getMap(creationLine.get());

        input.lines().skip(1).forEach(line -> applyLineToMap(line, map));

        return map;
    }

    private static void applyLineToMap(String line, Map map) {
        if (line.startsWith("T")){
            String[] description = line.split(" ");
            int treasuryValue = Integer.parseInt(description[2]);
            String[] splitConfig = description[1].split("-");
            int columnIndex = Integer.parseInt(splitConfig[0]);
            int lineIndex = Integer.parseInt(splitConfig[1]);
            map.addTreasury(treasuryValue,columnIndex,lineIndex);

        }
        if (line.startsWith("M")){
            String[] description = line.split(" ");
            String[] splitConfig = description[1].split("-");
            int columnIndex = Integer.parseInt(splitConfig[0]);
            int lineIndex = Integer.parseInt(splitConfig[1]);
            map.addMountain(columnIndex,lineIndex);
        }
    }

    private static Map getMap(String line) {
        // TODO : add consistency check
        String[] splitInput = line.split(" ");
        String[] splitConfig = splitInput[1].split("-");
        int columnSize = Integer.parseInt(splitConfig[0]);
        int lineSize = Integer.parseInt(splitConfig[1]);
        return new Map(columnSize, lineSize);
    }
}
