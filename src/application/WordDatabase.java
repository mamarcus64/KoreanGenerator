package application;

import grammar.EnglishWordFilters;
import words.Noun;
import words.Verb;
import words.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordDatabase {
    private static WordDatabase mWordDatabase;
    private ArrayList<Word>[] words;


    private WordDatabase() {
        List<Constants.Words> wordEnums = Arrays.asList(Constants.Words.values());
        List<String> wordTypes = new ArrayList<>();
        for (Constants.Words wordEnum : wordEnums) {
            wordTypes.add(wordEnum.val);
        }
        words = new ArrayList[wordTypes.size()];
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
                    Word word = (Word) (Class.forName("words." + EnglishWordFilters.capitalize(line[2]))
                            .getConstructor(String.class, String.class).newInstance(line[0], line[1]));
                    words[wordTypes.indexOf(line[2].toLowerCase())].add(word);
                } catch (Exception e) {
                    if (Constants.DEBUG_ON) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    public static WordDatabase getInstance() {
        if (mWordDatabase == null) {
            mWordDatabase = new WordDatabase();
        }
        return mWordDatabase;
    }

    public ArrayList<Word>[] getWords() {
        return words;
    }

}
