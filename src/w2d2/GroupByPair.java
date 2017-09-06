package w2d2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GroupByPair implements Comparable<PairW> {
    String key;
    ArrayList<Integer> values;

    public GroupByPair() {
        key = "";
        values = new ArrayList();
    }

    public GroupByPair(String key, ArrayList<Integer> values) {
        this.key = key;
        this.values = values;
    }

    @Override
    public int compareTo(PairW other) {
        return this.key.compareToIgnoreCase(other.key);
    }

    public ArrayList<Integer> getValues() {
        return this.values;
    }

    public void addValue(Integer newValue) {
        values.add(newValue);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {

        String str = "<" + key + " , " + "[";
        Iterator<Integer> i = values.iterator();

        while (i.hasNext()) {
            str = str + i.next() + ", ";
        }
        return str.substring(0, str.length() - 2) + "]>";
    }
}