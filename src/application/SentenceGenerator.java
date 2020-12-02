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
    private static ArrayList<ArrayList<Word>> words;
    private Sentence sentence;


    public static SentenceGenerator getInstance() {
        if (mSentenceGenerator == null) {
            mSentenceGenerator = new SentenceGenerator();
        }
        return mSentenceGenerator;
    }

    private SentenceGenerator() {
        nouns = new ArrayList<>();
        verbs = new ArrayList<>();
        words = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File("vocab/3.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNext()) {
            String[] line = scan.nextLine().split(",");
            switch (line[2]) {
                case "noun":
                    nouns.add(new Noun(line[0], line[1]));
                    break;
                case "verb":
                    verbs.add(new Verb(line[0], line[1]));
                    break;
                default:
                    break;
            }
        }
        words.add(nouns);
        words.add(verbs);
        generate();
    }

    public void generate() {
        sentence = new Sentence(SentencePattern.pattern1);
        while (!sentence.isAssembled()) {
            sentence.add(words.get(sentence.nextRequest()).get
                    ((int) (Math.random() * words.get(sentence.nextRequest()).size())));
        }
    }

    public String english() {
        return sentence.english();
    }

    public String korean() {
        return sentence.korean();
    }
}
