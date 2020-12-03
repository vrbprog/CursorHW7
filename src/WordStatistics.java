import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordStatistics {

    private final Map<String, Integer> dictionary;
    private Map.Entry<String, Integer> maxEntry;
    private Map.Entry<String, Integer> minEntry;
    private final String nameFile;

    public WordStatistics(String nameFile) {
        dictionary = new HashMap<>();
        this.nameFile = nameFile;
    }

    public void printWordStatistics() {
        loadWordMap();
        findMaxWold();
        findMinWold();
        System.out.println(this);
    }

    private String removePunctuation(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c) || Character.isSpaceChar(c)) {
                //if (Character.isAlphabetic(c) || Character.isDigit(c) || Character.isSpaceChar(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    private void loadNextLineToMap(String s) {
        String[] sMas = removePunctuation(s.toLowerCase()).split(" ");
        for (String note : sMas) {
            if (!note.equals("")) {
                if (!dictionary.containsKey(note)) {
                    dictionary.put(note, 1);
                } else {
                    dictionary.put(note, dictionary.get(note) + 1);
                }
            }
        }
    }

    private void loadWordMap() {
        try (
                FileReader fr = new FileReader(nameFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                loadNextLineToMap(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findMaxWold() {
        int max = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> map : dictionary.entrySet()) {
            if (map.getKey().length() >= max) {
                max = map.getKey().length();
                maxEntry = map;
            }
        }
    }

    private void findMinWold() {
        int min = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> map : dictionary.entrySet()) {
            if (map.getKey().length() < min) {
                min = map.getKey().length();
                minEntry = map;
            }
        }
    }

    @Override
    public String toString() {
        return "Words statistics in file\n\r" +
                "WordStatistics{" + " Longest word: " +
                maxEntry.getKey() + " - " + maxEntry.getValue() + " time(s)" +
                ", Shortest word: " + minEntry.getKey() + " - "
                + minEntry.getValue() + " time(s)" + " " + '}';
    }
}

