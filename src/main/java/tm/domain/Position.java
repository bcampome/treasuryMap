package tm.domain;

import java.util.Objects;

public class Position {
    public final int column;
    public final int line;

    public Position(int column, int line) {
        this.column = column;
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column && line == position.line;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, line);
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }
}
