package w2d2;

import java.util.ArrayList;
import java.util.Iterator;

public class Reducer {

    public Reducer() {
    }

    public ArrayList<PairW> reduce(ArrayList<GroupByPair> groupByPairs) {
        String line;
        ArrayList<PairW> pairs = new ArrayList<PairW>();

        int count = 0;


        for (GroupByPair g : groupByPairs) {
            Iterator<Integer> i = g.getValues().iterator();
            while (i.hasNext()) {
                i.next();
                count++;
            }
            pairs.add(new PairW(g.getKey(), count));
            count = 0;
        }
        return pairs;
    }

}
