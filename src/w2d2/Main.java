package w2d2;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/w2d2/datatest/testDataForW1D1.txt";

        Mapper map = new Mapper();
        ArrayList<PairW> pairs = map.mapper(filePath);

        // Output of Mapper
        System.out.println("");
        System.out.println("OUTPUT FOR MAPPER");
        System.out.println("---------------------------------------");
        for (PairW p : pairs) {
            System.out.println(p);
        }

        // Process data before go to Reducer
        ArrayList<GroupByPair> groupBypairs = new ArrayList<GroupByPair>();
        int count = 0;
        PairW<String, Integer> pPrev = null;
        GroupByPair<String, Object> groupByPair = null;
        for (PairW<String, Integer> p : pairs) {
            if (count == 0) {
                pPrev = p;
                groupByPair = new GroupByPair("", new ArrayList());
                groupByPair.setKey(p.getKey());
                groupByPair.addValue(p.getValue());
                groupBypairs.add(groupByPair);
                count++;
            } else if (p.getKey().equals(pPrev.getKey())) {
                groupByPair.addValue(p.getValue());
                count++;
            } else {
                pPrev = p;
                groupByPair = new GroupByPair("", new ArrayList());
                groupByPair.setKey(p.getKey());
                groupByPair.addValue(p.getValue());
                groupBypairs.add(groupByPair);
                count++;
            }
        }


        // Print input for Reducer
        System.out.println("");
        System.out.println("INPUT FOR REDUCER");
        System.out.println("---------------------------------------");
        for (GroupByPair g : groupBypairs) {
            System.out.println(g);
        }

        // Process Reducer
        Reducer pro = new Reducer();
        ArrayList<PairW> producerOut = pro.reduce(groupBypairs);

        // Output of Reducer
        System.out.println("");
        System.out.println("OUTPUT FOR REDUCER");
        System.out.println("---------------------------------------");
        for (PairW p : producerOut) {
            System.out.println(p);
        }


    }
}
