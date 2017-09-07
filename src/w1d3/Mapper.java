package w1d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

public class Mapper {

    public Mapper() {
    }

    public ArrayList<PairW> mapper(String filePath) throws IOException {
        String line;
        ArrayList<PairW> pairs =
                new ArrayList<PairW>();
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
                        pairs.add(new PairW(w, 1));
                    }
                }
            }
        }
        //Collections.sort(pairs);   // Follow data output sample, this assignment not require sort here
        return pairs;
    }

}
