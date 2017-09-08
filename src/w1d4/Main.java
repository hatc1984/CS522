package w1d4;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WordCount wordCount = new WordCount(3, 4);

        // print map output
        wordCount.showMapperOutput();

        // print partition output
        wordCount.showPartitionOutput();

        // print reduce input
        wordCount.showReduceInput();

        // print reduce output
        wordCount.showReduceOutput();
    }
}
