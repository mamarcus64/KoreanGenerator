package grammar;

import application.Constants;
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
        return Constants.wordType(pattern.getEnglish().get(words.size()).getWord());
    }

    public void add(Word word) {
        words.add(word);
        if (words.size() == pattern.length()) {
            assembled = true;
            cleanSentence();
        }
    }

    public void cleanSentence() {
        EnglishWordFilters.capitalize(words.get(pattern.getEnglish().indexOf(GrammarPart.SUBJECT)));
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
