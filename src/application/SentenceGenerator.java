package application;

import grammar.Sentence;
import words.Noun;
import words.Verb;
import words.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SentenceGenerator {
    private static SentenceGenerator mSentenceGenerator;
    private static ArrayList<Noun> nouns;
    private static ArrayList<Verb> verbs;
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
        generate();
    }

    public void generate() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(nouns.get((int) (Math.random() * nouns.size())));
        words.add(verbs.get((int) (Math.random() * verbs.size())));
        words.add(nouns.get((int) (Math.random() * nouns.size())));
        sentence = new Sentence(words);
    }

    public String english() {
        return sentence.english();
    }

    public String korean() {
        return sentence.korean();
    }
}
