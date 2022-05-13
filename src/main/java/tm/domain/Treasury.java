package tm.domain;

import java.util.Objects;

public final class Treasury implements ItemMap {


    private int size;

    public Treasury(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treasury treasury = (Treasury) o;
        return size == treasury.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

}
