package w1d3;

import java.io.IOException;
import java.util.*;

public class WordCount {
    private int numberOfMapper;
    private int numberOfReducer;

    private Mapper[] mappers;
    private Reducer[] reducers;

    List<List<PairW>> mapperOutputs = new ArrayList<>();
    List<List<List<PairW>>> partitions = new ArrayList<>();

    List<List<GroupByPair>> reducerInputs = new ArrayList<>();
    List<List<PairW>> reducerOutputs = new ArrayList<>();


    public WordCount(int numberOfMapper, int numberOfReducer) throws IOException {
        this.numberOfMapper = numberOfMapper;
        this.numberOfReducer = numberOfReducer;

        // initialize mapper and reducer objects
        initialization();


        // Read file and run mapper
        map();


        // Using partition
        partition();

        // Call all reducer
        reduce();

    }

    private void initialization() {

        mappers = new Mapper[this.numberOfMapper];
        reducers = new Reducer[this.numberOfReducer];


        // initialize all the objects
        for(int i = 0; i < mappers.length; i++) {
            mappers[i] = new Mapper();
            mapperOutputs.add(new ArrayList<>());
            partitions.add(new ArrayList<>());

            for(int j = 0; j < reducers.length; j++) {
                partitions.get(i).add(new ArrayList<>());
            }
        }

        for(int i = 0; i < reducers.length; i++) {
            reducers[i] = new Reducer();
        }
    }


    private void map() throws IOException {
        for(int i = 0; i < mappers.length; i++) {
            mapperOutputs.get(i).addAll(mappers[i].mapper("src/w1d3/datatest/input" + i + ".txt"));
        }
    }

    public int getPartition(String key){
        return (int) key.hashCode() % this.numberOfReducer;
    }

    private void partition() {
        for(int i = 0; i < mappers.length; i++) {
            for(PairW<String, Integer> p : mapperOutputs.get(i)) {
                int reducerIndex = getPartition(p.getKey());
                partitions.get(i).get(reducerIndex).add(p);
            }
        }

        for(int i = 0; i < partitions.size(); i++) {
            for(int j = 0; j < partitions.get(i).size(); j++) {
                Collections.sort(partitions.get(i).get(j));
            }
        }

    }

    private void reduce() {
        for(int i = 0; i < reducers.length; i++) {

            List<PairW<String, Integer>> list = new ArrayList<>();

            for(int j = 0; j < partitions.size(); j++) {
                for(PairW<String, Integer> p : partitions.get(j).get(i)) {
                    list.add(p);
                }
            }

            Collections.sort(list);
            ArrayList<GroupByPair> groupByPairs = inputReduce(list);
            reducerInputs.add(groupByPairs);

            // Reduce all
            reducerOutputs.add(reducers[i].reduce(groupByPairs));
        }

    }

    public ArrayList<GroupByPair> inputReduce(List<PairW<String, Integer>> list) {
        ArrayList<GroupByPair> groupBypairs = new ArrayList<GroupByPair>();
        int count = 0;
        PairW<String, Integer> pPrev = null;
        GroupByPair<String, Object> groupByPair = null;

        for (PairW<String, Integer> p : list) {
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

        Collections.sort(groupBypairs);
        return groupBypairs;
    }



    public void showMapperOutput() {
        System.out.println("");
        System.out.println("MAPPER OUTPUT");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < mapperOutputs.size(); i++) {
            System.out.println("Mapper " + i + " Output: ");
            for(PairW<String, Integer> p : mapperOutputs.get(i)) {
                System.out.println(p);
            }
        }
    }

    public void showPartitionOutput() {
        System.out.println("");
        System.out.println("PAIR SEND FROM MAPPER TO REDUCER");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < partitions.size(); i++) {
            for(int j = 0; j < partitions.get(i).size(); j++) {
                System.out.println("Pairs send from Mapper " + i + " Reducer " + j);
                for(PairW<String, Integer> p : partitions.get(i).get(j)) {
                    System.out.println(p);
                }
            }
        }
    }


    public void showReduceInput() {
        System.out.println("");
        System.out.println("DATA INPUT FOR REDUCER");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < reducerInputs.size(); i++) {
            System.out.println("Reducer " + i + " input: ");
            for(GroupByPair<String, Integer> p : reducerInputs.get(i)) {
                StringBuilder s = new StringBuilder();
                s.append("[");
                for(int j = 0; j < p.getValues().size(); j++) {
                    s.append(p.getValues().get(j));
                    if(j < p.getValues().size() - 1) s.append(", ");
                }
                s.append("]");
                System.out.println("< " + p.getKey() + ", " + s + " >");
            }
        }
    }

    public void showReduceOutput() {
        System.out.println("");
        System.out.println("REDUCER OUTPUT");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < reducerOutputs.size(); i++) {
            System.out.println("Reducer " + i + " output: ");
            for(PairW<String, Integer> p : reducerOutputs.get(i)) {
                System.out.println("< " + p.getKey() + ", " + p.getValue() + " >");
            }
        }
    }


}
