package grammar;

import application.Constants;
import words.Word;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private List<Word> words;
    private SentencePattern pattern;
    private EnglishWordFilters englishFilters;
    private KoreanWordFilters koreanFilters;
    private boolean assembled;
    private Constants.Tense tense;
    private Constants.Honorific honorific;
    private Constants.Person person;

    public Sentence(SentencePattern pattern, Constants.Tense tense, Constants.Honorific honorific) {
        this.pattern = pattern;
        this.tense = tense;
        this.honorific = honorific;
        this.person = Constants.Person.THIRD;
        assembled = false;
        words = new ArrayList<Word>(pattern.length());
        englishFilters = EnglishWordFilters.getInstance();
        koreanFilters = KoreanWordFilters.getInstance();
    }

    public boolean isAssembled() {
        return assembled;
    }

    public int nextRequest() {
        return Constants.wordType(pattern.getPattern().get(words.size()).getWord());
    }

    public void add(Word word) {
        words.add(word.clone());
        if (words.size() == pattern.length()) {
            assembled = true;
            cleanSentence();
        }
    }

    private Word patternWord(GrammarPart part) {
        int index = pattern.getPattern().indexOf(part);
        return index == -1 ? null : words.get(index);
    }

    private void cleanSentence() {
        englishFilters.articulate(patternWord(GrammarPart.SUBJECT));
        englishFilters.articulate(patternWord(GrammarPart.DIRECT_OBJECT));
        englishFilters.capitalize(patternWord(GrammarPart.SUBJECT));
        englishFilters.toTense(patternWord(GrammarPart.INTRANSITIVE_VERB),
                Constants.Tense.FUTURE_CONFIDENT, Constants.Person.THIRD);
        englishFilters.toTense(patternWord(GrammarPart.TRANSITIVE_VERB),
                Constants.Tense.FUTURE_CONFIDENT, Constants.Person.THIRD);
        koreanFilters.subjectify(patternWord(GrammarPart.SUBJECT));
        koreanFilters.objectify((patternWord(GrammarPart.DIRECT_OBJECT)));
        //koreanFilters.tensify(patternWord(GrammarPart.TRANSITIVE_VERB));
        //koreanFilters.tensify(patternWord(GrammarPart.INTRANSITIVE_VERB));

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
        for (Integer koreanOrder : pattern.getKoreanOrder()) {
            korean.append(words.get(koreanOrder).korean + " ");
        }
        return korean.toString();
    }

    public String metadata() throws IllegalStateException {
        if (!assembled) {
            throw new IllegalStateException("Sentence is not fully assembled.");
        }
        return (honorific + " " + tense).toLowerCase().replace("_", " ");
    }
}
