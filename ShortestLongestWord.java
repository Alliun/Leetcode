import java.util.Scanner;

public class ShortestLongestWord {

    // Method to compute length of string without using length()
    public static int getLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count); // throws exception when index is invalid
                count++;
            }
        } catch (Exception e) {
            // stop when index is invalid
        }
        return count;
    }

    // Method to split string into words without using split()
    public static String[] splitWords(String text) {
        text = text.trim() + " "; // add space at end for easier processing
        int n = getLength(text);

        String word = "";
        String[] temp = new String[n]; // temporary max-size array
        int index = 0;

        for (int i = 0; i < n; i++) {
            char ch = text.charAt(i);
            if (ch != ' ') {
                word += ch;
            } else {
                if (!word.equals("")) {
                    temp[index++] = word;
                    word = "";
                }
            }
        }

        // copy exact number of words
        String[] words = new String[index];
        for (int i = 0; i < index; i++) {
            words[i] = temp[i];
        }

        return words;
    }

    // Method to prepare 2D array of words and lengths
    public static String[][] getWordTable(String[] words) {
        String[][] table = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            table[i][0] = words[i];
            table[i][1] = String.valueOf(getLength(words[i]));
        }
        return table;
    }

    // Method to find shortest and longest word (returns indices)
    public static int[] findShortestLongest(String[][] table) {
        int minIndex = 0, maxIndex = 0;
        int minLen = Integer.parseInt(table[0][1]);
        int maxLen = Integer.parseInt(table[0][1]);

        for (int i = 1; i < table.length; i++) {
            int len = Integer.parseInt(table[i][1]);
            if (len < minLen) {
                minLen = len;
                minIndex = i;
            }
            if (len > maxLen) {
                maxLen = len;
                maxIndex = i;
            }
        }

        return new int[]{minIndex, maxIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step a: Take input
        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();

        // Step b: Split into words
        String[] words = splitWords(text);

        // Step c+d: Build table
        String[][] table = getWordTable(words);

        // Step e: Find shortest & longest
        int[] result = findShortestLongest(table);

        // Display word table
        System.out.println("\nWord\tLength");
        System.out.println("------------------");
        for (int i = 0; i < table.length; i++) {
            int len = Integer.parseInt(table[i][1]);
            System.out.println(table[i][0] + "\t" + len);
        }

        // Display shortest and longest
        System.out.println("\nShortest word: " + table[result[0]][0] + " (" + table[result[0]][1] + ")");
        System.out.println("Longest word : " + table[result[1]][0] + " (" + table[result[1]][1] + ")");
    }
}
