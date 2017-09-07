package w1d3;

public class PairW<K extends Comparable<K>, V >  implements Comparable<PairW<K, V>> {
    private K key;
    private V value;

    public PairW() {
    }

    public PairW(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public K getKey() {
        return key;
    }

    public V getValue(){
        return value;
    }

    @Override
    public int compareTo(PairW <K, V> other) {
        return this.key.compareTo(other.getKey());
    }

    @Override
    public String toString() {
        return "< " + key + " , " + value + " >";
    }
}
