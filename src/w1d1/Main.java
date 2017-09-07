package w1d1;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "src/w1d1/datatest/testDataForW1D1.txt";

        Mapper map = new Mapper();
        ArrayList<PairW> pairs = map.mapper(filePath);

        // Output of Mapper
        System.out.println("");
        System.out.println("OUTPUT FOR MAPPER");
        System.out.println("---------------------------------------");
        for (PairW p : pairs) {
            System.out.println(p);
        }
    }

}
