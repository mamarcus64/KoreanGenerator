package application;

import words.Word;

import java.util.ArrayList;

public class WordDatabase {
    private static WordDatabase mWordDatabase;
    private ArrayList<Word>[] words;


    private WordDatabase() {
        Constants.Words[] wordTypes = Constants.Words.values();
    }

    public static WordDatabase getInstance() {
        if (mWordDatabase == null) {
            mWordDatabase = new WordDatabase();
        }
        return mWordDatabase;
    }
}
