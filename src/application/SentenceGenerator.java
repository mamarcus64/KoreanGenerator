package application;

import grammar.Sentence;
import grammar.SentencePattern;
import words.Noun;
import words.Verb;
import words.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SentenceGenerator {
    private static SentenceGenerator mSentenceGenerator;
    private static ArrayList<Word> nouns;
    private static ArrayList<Word> verbs;
    private static ArrayList<Word>[] words;
    private Sentence sentence;


    public static SentenceGenerator getInstance() {
        if (mSentenceGenerator == null) {
            mSentenceGenerator = new SentenceGenerator();
        }
        return mSentenceGenerator;
    }

    private SentenceGenerator() {
        words = WordDatabase.getInstance().getWords();
        generate();
    }

    public void generate() {
        sentence = new Sentence(SentencePattern.pattern2);
        while (!sentence.isAssembled()) {
            sentence.add(words[sentence.nextRequest()].get
                    ((int) (Math.random() * words[sentence.nextRequest()].size())));
        }
    }

    public String english() {
        return sentence.english();
    }

    public String korean() {
        return sentence.korean();
    }
}
