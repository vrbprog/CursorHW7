import java.io.File;

public class Test {
    public static void main(String[] args) {
        //Task 1
        String s = "Happy New 2021 Year!!!";
        CharStatistics statistics = new CharStatistics(s);
        statistics.printCharStatistics();

        //Task 2
        String windowsFilePath = "src" + File.separator + "filename.txt";
        WordStatistics wordStatistics = new WordStatistics(windowsFilePath);
        wordStatistics.printWordStatistics();

    }
}
