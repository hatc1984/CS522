package w2d2;

public class PairW implements Comparable<PairW> {
    String key;
    int value;

    public PairW(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(PairW other) {
        return this.key.compareToIgnoreCase(other.key);
    }

    public int getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "<" + key + " , " + value + ">";
    }
}
