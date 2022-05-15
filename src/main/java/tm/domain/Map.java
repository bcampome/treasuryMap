package tm.domain;

import java.util.Optional;

public class Map {

    private final ItemMap[][] itemMap;
    private final int lineSize;
    private final int columnSize;

    public Map(int columnSize, int lineSize) {
        itemMap = new ItemMap[lineSize][columnSize];
        this.lineSize = lineSize;
        this.columnSize = columnSize;
    }

    public int getNumberOfTreasuryAvailable() {
        return 0;
    }

    public int getNumbersOfMountains() {
        return 0;
    }

    public void addTreasury(int treasurySize, int column, int line) {
        itemMap[line-1][column -1] = new Treasury(treasurySize);
    }

    public void addMountain(int column, int line) {
        itemMap[line - 1][column - 1] = new Mountain();
    }

    public Optional<ItemMap> getItem(int column, int line) {
        return Optional.ofNullable(itemMap[line - 1][column - 1]);
    }

    public Optional<ItemMap> retrieve(int column, int line) {
        Optional<ItemMap> item = getItem(column, line);
        this.erase(column,line);
        return item;
    }

    private void erase(int column, int line) {
        this.itemMap[line-1][column-1] = null;
    }

    public int getNumberOfLine() {
        return lineSize;
    }

    public int getNumberOfColumn() {
        return columnSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(ItemMap[] line : itemMap){
            for(ItemMap item : line) {
                if(null == item){
                    sb.append("#");
                }
                if(item instanceof Treasury){
                    sb.append("T");
                }
                if(item instanceof Mountain){
                    sb.append("M");
                }
            }
            sb.append("\n");
        }
        return "Map{" +
                ", lineSize=" + lineSize +
                ", columnSize=" + columnSize +
                "}\n" + sb;
    }
}
