package tm.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    public void should_mapIsEmpty_when_mapIsOnlyCreated() {
        final Map map = new Map(1, 1);
        assertEquals(0, map.getNumbersOfMountains());
        assertEquals(0, map.getNumberOfTreasuryAvailable());
    }

    @Test
    public void should_createMountain_when_addMountainOnMap() {
        // Given
        final Map map = new Map(2, 2);
        // When
        map.addMountain(1, 1);
        // Then
        final MapItem item = map.getItem(1, 1);
        assertInstanceOf(Mountain.class, item);
    }

    @Test
    public void should_createTreasury_when_addTreasuryOnMap() {
        // Given
        final Map map = new Map(2, 2);
        // When
        map.addTreasury(5,1, 1);
        // Then
        final MapItem item = map.getItem(1, 1);
        if(item instanceof Treasury treasury){
            assertEquals(5, treasury.getSize());
        }else {
            throw new AssertionError();
        }
    }
}