package tm.domain;

public class Map {

    private final MapItem[][] mapItem;

    public Map(int columnSize, int lineSize) {
        mapItem = new MapItem[columnSize][lineSize];
    }

    public int getNumberOfTreasuryAvailable() {
        return 0;
    }

    public int getNumbersOfMountains() {
        return 0;
    }

    public void addTreasury(int numberOfTreasury, int column, int line) {
        mapItem[column -1][line-1] = new Treasury(numberOfTreasury);
    }

    public void addMountain(int column, int line) {
        mapItem[column - 1][line - 1] = new Mountain();
    }

    public MapItem getItem(int column, int line) {
        // Fix me, check IndexOutOfBoundException ??
        return mapItem[column - 1][line - 1];
    }

}
