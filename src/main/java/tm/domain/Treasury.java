package tm.domain;

public final class Treasury implements MapItem {
    private int size;

    public Treasury(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
