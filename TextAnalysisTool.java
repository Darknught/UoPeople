import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Input
        System.out.println("Please enter a paragraph or a lengthy text:");
        String inputText = scanner.nextLine();

        // Character Count
        int charCount = inputText.length();
        System.out.println("\nTotal number of characters: " + charCount);

        // Word Count
        String[] words = inputText.trim().split("\\s+");
        int wordCount = words.length;
        System.out.println("Total number of words: " + wordCount);

        // Most Common Character
        char mostCommonChar = getMostCommonCharacter(inputText);
        System.out.println("Most common character: " + mostCommonChar);

        // Character Frequency
        System.out.println("\nEnter a character to find its frequency:");
        String charInput = scanner.nextLine();
        if (charInput.length() == 1) {
            char charToFind = charInput.toLowerCase().charAt(0);
            int frequency = getCharacterFrequency(inputText, charToFind);
            System.out.println("Frequency of '" + charInput + "': " + frequency);
        } else {
            System.out.println("Invalid input. Please enter a single character.");
        }

        // Word Frequency
        System.out.println("\nEnter a word to find its frequency:");
        String wordToFind = scanner.nextLine();
        int wordFreq = getWordFrequency(inputText, wordToFind);
        System.out.println("Frequency of '" + wordToFind + "': " + wordFreq);

        // Unique Words
        int uniqueWordsCount = getUniqueWordCount(inputText);
        System.out.println("Number of unique words: " + uniqueWordsCount);

        scanner.close();
    }

    /**
     * Calculates the most common character in the given text (case-insensitive).
     *
     * @param text The input text.
     * @return The most common character.
     */
    public static char getMostCommonCharacter(String text) {
        Map<Character, Integer> charFrequencies = new HashMap<>();
        String lowerCaseText = text.toLowerCase();

        for (char c : lowerCaseText.toCharArray()) {
            if (Character.isLetterOrDigit(c)) { // Consider only letters and digits for common character
                charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);
            }
        }

        char mostCommon = ' ';
        int maxFrequency = 0;

        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostCommon = entry.getKey();
            }
        }
        return mostCommon;
    }

    /**
     * Calculates the frequency of a given character in the text (case-insensitive).
     *
     * @param text        The input text.
     * @param charToFind The character to find.
     * @return The frequency of the character.
     */
    public static int getCharacterFrequency(String text, char charToFind) {
        int count = 0;
        String lowerCaseText = text.toLowerCase();
        char lowerCaseCharToFind = Character.toLowerCase(charToFind);

        for (char c : lowerCaseText.toCharArray()) {
            if (c == lowerCaseCharToFind) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the frequency of a given word in the text (case-insensitive).
     *
     * @param text       The input text.
     * @param wordToFind The word to find.
     * @return The frequency of the word.
     */
    public static int getWordFrequency(String text, String wordToFind) {
        int count = 0;
        String lowerCaseText = text.toLowerCase();
        String lowerCaseWordToFind = wordToFind.toLowerCase();

        // Split the text into words, removing punctuation
        String[] words = lowerCaseText.split("[\\s.,;!?()-]+");

        for (String word : words) {
            if (word.equals(lowerCaseWordToFind)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Calculates the number of unique words in the text (case-insensitive).
     *
     * @param text The input text.
     * @return The number of unique words.
     */
    public static int getUniqueWordCount(String text) {
        Set<String> uniqueWords = new HashSet<>();
        String lowerCaseText = text.toLowerCase();

        // Split the text into words, removing punctuation
        String[] words = lowerCaseText.split("[\\s.,;!?()-]+");

        for (String word : words) {
            if (!word.isEmpty()) { // Avoid adding empty strings from multiple delimiters
                uniqueWords.add(word);
            }
        }
        return uniqueWords.size();
    }
}
