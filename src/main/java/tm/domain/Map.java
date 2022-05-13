package tm.domain;

import java.util.Optional;

public class Map {

    private final ItemMap[][] itemMap;

    public Map(int columnSize, int lineSize) {
        itemMap = new ItemMap[columnSize][lineSize];
    }

    public int getNumberOfTreasuryAvailable() {
        return 0;
    }

    public int getNumbersOfMountains() {
        return 0;
    }

    public void addTreasury(int treasurySize, int column, int line) {
        itemMap[column -1][line-1] = new Treasury(treasurySize);
    }

    public void addMountain(int column, int line) {
        itemMap[column - 1][line - 1] = new Mountain();
    }

    public Optional<ItemMap> getItem(int column, int line) {
        return Optional.ofNullable(itemMap[column - 1][line - 1]);
    }

    public Optional<ItemMap> retrieve(int column, int line) {
        Optional<ItemMap> item = getItem(column, line);
        this.erase(column,line);
        return item;
    }

    private void erase(int column, int line) {
        this.itemMap[column-1][line-1] = null;
    }
}
