// TODO: Write your header comment here!

// TODO: Write your class comment here! 

// Yunho Cho
// TA: Ben Wang
// CSE 122 AF
// July 27 2023
// Absurdle
// This class is a absurdle. User gets to choose one of the three diction and length of the word
// and tried to guess it while if the alphabet letter exist in other exist
// instead same index then motify it to YELLOW else if the alphabet of guess word does 
// not exit in the dictionary word then motify rest index of word into GREY. letter is in correct spot it will motify to green
// else it's located different index it will motify it to yellow else if it's not in any other
// index it will motify to gray.
import java.util.*;
import java.io.*;

public class Absurdle  {
    public static final String GREEN = "🟩";
    public static final String YELLOW = "🟨";
    public static final String GRAY = "⬜";

    // [[ ALL OF MAIN PROVIDED ]]
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the game of Absurdle.");

        System.out.print("What dictionary would you like to use? ");
        String dictName = console.next();

        System.out.print("What length word would you like to guess? ");
        int wordLength = console.nextInt();

        List<String> contents = loadFile(new Scanner(new File(dictName)));
        Set<String> words = pruneDictionary(contents, wordLength);

        List<String> guessedPatterns = new ArrayList<>();
        while (!isFinished(guessedPatterns)) {
            System.out.print("> ");
            String guess = console.next();
            String pattern = record(guess, words, wordLength);
            guessedPatterns.add(pattern);
            System.out.println(": " + pattern);
            System.out.println();
        }
        System.out.println("Absurdle " + guessedPatterns.size() + "/∞");
        System.out.println();
        printPatterns(guessedPatterns);
    }

    // [[ PROVIDED ]]
    // Prints out the given list of patterns.
    // - List<String> patterns: list of patterns from the game
    public static void printPatterns(List<String> patterns) {
        for (String pattern : patterns) {
            System.out.println(pattern);
        }
    }

    // [[ PROVIDED ]]
    // Returns true if the game is finished, meaning the user guessed the word. Returns
    // false otherwise.
    // - List<String> patterns: list of patterns from the game
    public static boolean isFinished(List<String> patterns) {
        if (patterns.isEmpty()) {
            return false;
        }
        String lastPattern = patterns.get(patterns.size() - 1);
        return !lastPattern.contains("⬜") && !lastPattern.contains("🟨");
    }

    // [[ PROVIDED ]]
    // Loads the contents of a given file Scanner into a List<String> and returns it.
    // - Scanner dictScan: contains file contents
    public static List<String> loadFile(Scanner dictScan) {
        List<String> contents = new ArrayList<>();
        while (dictScan.hasNext()) {
            contents.add(dictScan.next());
        }
        return contents;
    }

    // TODO: Write your code here! 

    // For user to set length of the word they want to guess and then
    // it will return new diction with that specific number.
    //                  Exception:
    // - IllegalArgumentException: It will throw exception if word length is less than a 1
    //    Parameter:
    //   - contents: List of dictionary words they choose from a specific file
    // - wordLength: length of the dictionary words
    //        Return:
    // - Set<String>: new dictionary words that only contains wordLength size
    public static Set<String> pruneDictionary(List<String> contents, int wordLength) {
        if(wordLength < 1) {
            throw new IllegalArgumentException("Word's length should not be less than a 1!");
        }
        Set<String> diction = new HashSet<>();
        for(String word : contents) {
            if(word.length() == wordLength) {
                diction.add(word);
            }
        }
        return diction;
    }

    // To change a set of sizes is determined by the next set of words by eliminating the smaller
    // size of the set and the largest set of words still remains in the set because the game
    // itself will make it more complex to finish it and consumes more time to solve.
    // Exception: throw IllegalArgumentException if set is empty or length of 
    // guess is not equal to given wordLength
    //    Parameter:
    //      - guess: user's guess word
    //      - words: set of specififc dictionary words
    // - wordLength: word's length of dictionary words
    //   Return:
    // - String: Pattern of the word's index color 
    public static String record(String guess, Set<String> words, int wordLength) {
        if(words.isEmpty() || guess.length() != wordLength) {
            throw new IllegalArgumentException();
        }
        Map<String, Set<String>> patternWord = new TreeMap<>();
        constructMap(words, guess, patternWord);
        String mostWord = "";
        int maxSize = 0;
        for(String pattern : patternWord.keySet()) {
            if(patternWord.get(pattern).size() > maxSize) {
                maxSize = patternWord.get(pattern).size();
                mostWord = pattern;
            }
        }
        words.clear();
        words.addAll(patternWord.get(mostWord));
        return mostWord;
    }
    
    // Construct an empty map by adding patterns of word and set of dictionary words.
    // no exception
    // Parameter:
    //   - words: set of specififc dictionary words
    //   - guess: user's guess word
    //     - map: empty map to add pattern and set of dictionary words
    // no return:
    public static void constructMap(Set<String> words, String guess, Map<String, Set<String>> map) {
        for(String word : words) {
            String pattern = patternFor(word, guess);
            if(!map.containsKey(pattern)) {
                map.put(pattern, new HashSet<>());
            }
            map.get(pattern).add(word);
        }
    }
    
    // To see if guess word and dictionary word are similiar by if guess's alphabet are on same
    // index as word then motify that index of word to GREEN and if the alphabet letter exist in
    // different index of word then motify word's index into YELLOW else if the alphabet of guess
    // does not exist in alphabet of word then motify rest index of word into GREY.
    // no exception
    // Parameter:
    //    - word: dictionary word from specific file and length of word
    //   - guess: user's guess word
    //   Return:
    // - String: pattern of word's index color
    public static String patternFor(String word, String guess) {
        List<String> pattern = new ArrayList<>();
        Map<Character, Integer> charCount = new TreeMap<>();
        for(int i = 0; i < guess.length(); i++) {
            String letter = guess.substring(i, i + 1);
            pattern.add(letter);
        }
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!charCount.containsKey(c)) {
                charCount.put(c, 1);
            } else {
                charCount.put(c, charCount.get(c) + 1);
            }
        }

        for(int i = 0; i < word.length(); i++) {
            char trueChar = word.charAt(i);
            char guessChar = pattern.get(i).charAt(0);
            if(trueChar == guessChar) {
                pattern.set(i, GREEN);
                charCount.put(guessChar, charCount.get(guessChar) - 1);
            }
        }
        for(int i = 0; i < word.length(); i++) {
            char trueChar = word.charAt(i);
            char guessChar = pattern.get(i).charAt(0);
            if (charCount.containsKey(guessChar) && charCount.get(guessChar) > 0) {
                pattern.set(i, YELLOW);
                charCount.put(guessChar, charCount.get(guessChar) - 1);
            }
        }
        for(int i = 0; i < pattern.size(); i++) {
            String character = pattern.get(i);
            if (!character.equals(YELLOW) && !character.equals(GREEN)) {
                pattern.set(i, GRAY);
            }
        }
        String wordle = "";
        for(String color : pattern) {
            wordle += color;
        }
        return wordle;
    }
}