package grammar;

import words.Word;

import java.util.ArrayList;

public class Sentence {
    private ArrayList<Word> words;
    private SentencePattern pattern;

    public Sentence(ArrayList<Word> words) {
        this.words = words;
    }

    public String english() {
        StringBuilder english = new StringBuilder();
        for (Word word : words) {
            english.append(word.english + " ");
        }
        return english.toString();
    }

    public String korean() {
        StringBuilder korean = new StringBuilder();
        for (Word word : words) {
            korean.append(word.korean + " ");
        }
        return korean.toString();
    }
}
