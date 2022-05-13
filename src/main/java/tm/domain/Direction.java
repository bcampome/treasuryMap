package tm.domain;

public enum Direction {
    North("N", 0),
    East("E", 1),
    South("S", 2),
    West("O", 3);

    private final String value;
    private final int order;


    Direction(String value, int order) {
        this.value = value;
        this.order = order;
    }


    public String value() {
        return this.value;
    }


    public Direction getNextDirectionInClockwise() {
        return getByOrder((this.order + 1) % 4);
    }

    public Direction getNextDirectionInAntiClockwise() {
        return getByOrder((this.order + 3) % 4);
    }


    private Direction getByOrder(int order) {
        for (Direction direction : Direction.values()) {
            if (direction.order == order) {
                return direction;
            }
        }
        return null;
    }

    public static Direction getDirectionByValue(String value) {
        for (Direction direction : Direction.values()) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        return null;
    }


}
