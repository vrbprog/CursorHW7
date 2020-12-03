import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CharStatistics {
    private final SortedMap<Character, Integer> generalTree;
    private SortedMap<Character, Integer> alphaTreeLat;
    private SortedMap<Character, Integer> alphaTreeCyr;
    private SortedMap<Character, Integer> digitTree;
    private SortedMap<Character, Integer> separatorsTree;
    private SortedMap<Character, Integer> separatorsExtraTree;
    private SortedMap<Character, Integer> separatorsExtra2Tree;
    private SortedMap<Character, Integer> separatorsExtra3Tree;
    private boolean mapMayBeInit = false;
    private String currentString;

    public CharStatistics(String input) {
        generalTree = new TreeMap<>();
        alphaTreeLat = new TreeMap<>();
        alphaTreeCyr = new TreeMap<>();
        digitTree = new TreeMap<>();
        separatorsTree = new TreeMap<>();

        if (input != null) {
            currentString = input;
            mapMayBeInit = true;
        } else System.out.println("You input null string!");
    }

    private void initCharMap(String input) {
        loadCharMap(input.toLowerCase().toCharArray());
    }

    private void loadCharMap(char[] chars) {
        for (char aChar : chars) {
            if (!generalTree.containsKey(aChar)) {
                generalTree.put(aChar, 1);
            } else {
                generalTree.put(aChar, generalTree.get(aChar) + 1);
            }
        }
    }

    private void generateStatistics() {
        digitTree = generalTree.subMap('0', ':');
        separatorsTree = generalTree.subMap('!', '0');
        separatorsExtraTree = generalTree.subMap(':', 'A');
        separatorsExtra2Tree = generalTree.subMap('[', 'a');
        separatorsExtra3Tree = generalTree.subMap('{', (char) 177);
        alphaTreeLat = generalTree.subMap('a', '{');
        alphaTreeCyr = generalTree.subMap('а', (char) 1104);
    }

    public void printCharStatistics() {
        if (mapMayBeInit) {
            initCharMap(currentString);
            generateStatistics();
            System.out.println("\n\rLetters statistics in string");
            if (alphaTreeLat.size() > 0) System.out.println("Latin letters : " + alphaTreeLat +
                    "; total number of letters in string - " + getTotalNumber(alphaTreeLat));
            if (alphaTreeCyr.size() > 0) System.out.println("Cyrillic letters : " + alphaTreeCyr +
                    "; total number of letters in string - " + getTotalNumber(alphaTreeCyr));
            Integer numberSpaces = generalTree.get(' ');
            if (numberSpaces == null) numberSpaces = 0;
            System.out.println("Number of spaces - " + numberSpaces);
            System.out.println("Number of digits - " + getTotalNumber(digitTree));
            System.out.println("Number of separators - " +
                    (getTotalNumber(separatorsTree) + getTotalNumber(separatorsExtraTree) +
                            getTotalNumber(separatorsExtra2Tree) + getTotalNumber(separatorsExtra3Tree)));
            System.out.println();
        }
    }

    private int getTotalNumber(SortedMap<Character, Integer> map) {
        int number = 0;
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
            number += entry.getValue();
        }
        return number;
    }
}


