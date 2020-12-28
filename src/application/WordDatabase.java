package application;

import grammar.EnglishWordFilters;
import words.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordDatabase {
    private static WordDatabase instance;
    private ArrayList<Word>[] words;


    private WordDatabase() {
        words = new ArrayList[Constants.wordTypeNumber()];
        for (int i = 0; i < words.length; i++) {
            words[i] = new ArrayList<>();
        }
        Scanner scan = null;
        for (int lesson = Constants.FIRST_LESSON; lesson <= Constants.LAST_LESSON; lesson++) {
            if (Constants.DEBUG_ON) {
                System.out.println("Loading vocab from lesson " + lesson + "...");
            }
            if (Arrays.asList(Constants.EXCLUDED_LESSONS).contains(lesson)) {
                lesson++;
                continue;
            }
            try {
                scan = new Scanner(new File("vocab/" + lesson + ".csv"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split(",");
                try {
                    //creates a new instance of the given word subclass and adds it to the appropriate words arraylist
                    Word word = (Word) (Class.forName("words." +
                            line[2].substring(0, 1).toUpperCase() + line[2].substring(1).toLowerCase())
                            .getConstructor(String.class, String.class).newInstance(line[0], line[1]));
                    words[Constants.wordType(line[2].toLowerCase())].add(word);
                } catch (Exception e) {
                    if (Constants.DEBUG_ON) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    public static WordDatabase getInstance() {
        if (instance == null) {
            instance = new WordDatabase();
        }
        return instance;
    }

    public ArrayList<Word>[] getWords() {
        return words;
    }

}
