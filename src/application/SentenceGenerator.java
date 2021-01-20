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
    }

    private SentencePattern getPattern() {
        return SentencePattern.pattern2;
    }

    public static Constants.Tense getTense() {
        return Constants.Tense.PAST;
        //return Constants.Tense.values()[(int) (Constants.Tense.values().length * Math.random())];
    }

    public static Constants.Honorific getHonorific() {
        return Constants.Honorific.values()[(int) (Constants.Honorific.values().length * Math.random())];
    }

    public void generate() {
        sentence = new Sentence(getPattern(), getTense(), getHonorific());
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

    public String metadata() { return sentence.metadata(); }
}
