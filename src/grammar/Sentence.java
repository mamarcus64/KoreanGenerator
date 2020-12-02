package grammar;

import words.Noun;
import words.Verb;
import words.Word;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private List<Word> words;
    private SentencePattern pattern;
    private boolean assembled;

    public Sentence(SentencePattern pattern) {
        this.pattern = pattern;
        assembled = false;
        words = new ArrayList<Word>(pattern.length());
    }

    public boolean isAssembled() {
        return assembled;
    }

    public int nextRequest() {
        if (Noun.class.equals(pattern.getPattern().get(words.size()).getWord())) {
            return 0;
        } else if (Verb.class.equals(pattern.getPattern().get(words.size()).getWord())) {
            return 1;
        }
        return -1;
    }

    public void add(Word word) {
        words.add(word);
        if (words.size() == pattern.length()) {
            assembled = true;
        }
    }

    public String english() throws IllegalStateException {
        if (!assembled) {
            throw new IllegalStateException("Sentence is not fully assembled.");
        }
        StringBuilder english = new StringBuilder();
        for (Word word : words) {
            english.append(word.english + " ");
        }
        return english.toString();
    }

    public String korean() throws IllegalStateException {
        if (!assembled) {
            throw new IllegalStateException("Sentence is not fully assembled.");
        }
        StringBuilder korean = new StringBuilder();
        for (Word word : words) {
            korean.append(word.korean + " ");
        }
        return korean.toString();
    }
}
