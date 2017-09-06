package w2d2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Producer {

    public Producer() {
    }

    public ArrayList<PairW> producer(ArrayList<GroupByPair> groupByPairs) {
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
