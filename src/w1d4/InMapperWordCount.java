package w1d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InMapperWordCount {

    String filePath;
    ArrayList<PairW> pairs =
            new ArrayList<PairW>();
    private List<String> wordList = new ArrayList<>();
    private Map<String, PairW<String, Integer>> H;

    public InMapperWordCount(String filePath) throws IOException{
        this.filePath = filePath;

        // read word from
        read();
    }

    public void read() throws IOException {
        String line;

        try (
                FileInputStream fis = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(isr);
        ) {
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s|-");
                for (String w : words) {
                    w = w.replaceAll("\\.$", "");
                    w = w.replaceAll("\\,$", "");
                    w = w.replaceAll("^\"|^\'", "");
                    w = w.replaceAll("\"$|\'$", "");
                    if (!w.isEmpty() && w.matches("[a-z,A-Z]*")) {
                        w = w.toLowerCase();
                        wordList.add(w);
                    }
                }
            }
        }
    }


    public void initialize() {
        H = new HashMap<>();
    }


    public void map(String term) {
        Integer value = 1;
        if(H.containsKey(term)) {
            value += H.get(term).getValue();
        }
        H.put(term, new PairW<>(term, value));
    }

    private void emit(String term, Integer count) {
        pairs.add(new PairW(term, count));
    }

    public void close() {
        for(Map.Entry<String, PairW<String, Integer>> map : H.entrySet()) {
            emit(map.getValue().getKey(), map.getValue().getValue());
        }
    }


    // get a list of words after reading from a file, empty list if no file provided
    public ArrayList<PairW>  get() {
        return pairs;
    }

    public List<String> wordList() {
        return wordList;
    }


}
