package w2d2;

import java.util.ArrayList;
import java.util.Iterator;

public class GroupByPair<K extends Comparable<K>, V > implements Comparable<GroupByPair<K, V>> {
    K key;
    ArrayList<V> values;

    public GroupByPair() {
    }

    public GroupByPair(K key, ArrayList<V> values) {
        this.key = key;
        this.values = values;
    }

    @Override
    public int compareTo(GroupByPair<K, V> other) {
        return this.key.compareTo(other.key);
    }

    public ArrayList<V> getValues() {
        return this.values;
    }

    public void addValue(V newValue) {
        values.add(newValue);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {

        String str = "<" + key + " , " + "[";
        Iterator<V> i = values.iterator();

        while (i.hasNext()) {
            str = str + i.next() + ", ";
        }
        return str.substring(0, str.length() - 2) + "]>";
    }
}