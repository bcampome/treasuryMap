package tm;

import tm.builder.AdventurerBuilder;
import tm.builder.MapBuilder;
import tm.domain.Adventurer;
import tm.domain.Map;
import tm.engine.Engine;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Missing inputs files.. abort run. ");
        }

        System.out.println(Arrays.asList(args));


        String mapFIleName = args[0];
        String adventurerFIleName = args[1];
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);

        Map map = loadMap(mapFIleName);
        List<Adventurer> adventurers = loadAdventurers(adventurerFIleName);

        Engine engine = new Engine(map, adventurers);
        engine.run();


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            for (Adventurer adventurer : engine.getAdventurers()) {
                writer.append(adventurer.toString());
                writer.newLine();
            }
        }

    }

    private static List<Adventurer> loadAdventurers(String adventurerFIleName) throws IOException {
        File file = new File(adventurerFIleName);
        String adventurersDesc = new String(new FileInputStream(file).readAllBytes());
        return AdventurerBuilder.build(adventurersDesc);
    }

    private static Map loadMap(String mapFIleName) throws IOException {

        File file = new File(mapFIleName);
        String mapDescription = new String(new FileInputStream(file).readAllBytes());
        return MapBuilder.build(mapDescription);
    }
}
