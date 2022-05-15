package tm.builder;

import org.junit.jupiter.api.Test;
import tm.domain.ItemMap;
import tm.domain.Map;
import tm.domain.Mountain;
import tm.domain.Treasury;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MapBuilderTest {

    @Test
    public void should_createEmptyMap_when_receivedInstructionOfEmptyMap() throws IOException {
        String input = new String(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("./map/EmptyMap_6x5.txt")).readAllBytes());

        Map map = MapBuilder.build(input);

        assertEquals(5, map.getNumberOfLine());
        assertEquals(6, map.getNumberOfColumn());
        assertEquals(0, map.getNumberOfTreasuryAvailable());
        assertEquals(0, map.getNumbersOfMountains());
    }

    @Test
    public void should_createTreasury_when_receivedInstructionWithOneTreasury() throws IOException {
        String input = new String(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("./map/Map_6x5_with_1_treasury.txt")).readAllBytes());

        Map map = MapBuilder.build(input);
        Optional<ItemMap> item = map.getItem(4, 2);
        assertTrue(item.isPresent());
        assertInstanceOf(Treasury.class,item.get());
        Treasury treasury = (Treasury) item.get();
        assertEquals(3, treasury.getSize());
    }

    @Test
    public void should_createTreasury_when_receivedInstructionWithOneMountain() throws IOException {
        String input = new String(Objects.requireNonNull(getClass().getClassLoader().
                getResourceAsStream("./map/Map_6x5_with_1_mountain.txt")).readAllBytes());

        Map map = MapBuilder.build(input);

        Optional<ItemMap> item = map.getItem(5, 3);
        assertTrue(item.isPresent());
        assertInstanceOf(Mountain.class,item.get());
    }



}