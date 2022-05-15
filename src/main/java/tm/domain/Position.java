package tm.domain;

import java.util.Objects;

public class Position {
    private final int column;
    private final int line;

    public Position(int column, int line) {
        this.column = column;
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
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

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", line=" + line +
                '}';
    }

    public Position top(){
        return new Position(this.column, this.line-1);
    }
    public Position down(){
        return new Position(this.column, this.line+1);
    }
    public Position left(){
        return new Position(this.column-1, this.line);
    }
    public Position right(){
        return new Position(this.column+1, this.line);
    }
}
