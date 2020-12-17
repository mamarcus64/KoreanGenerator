package grammar;

import words.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentencePattern {

    public static SentencePattern pattern1 = new SentencePattern(
            new GrammarPart[] {GrammarPart.SUBJECT, GrammarPart.INTRANSITIVE_VERB},
            new GrammarPart[] {GrammarPart.SUBJECT, GrammarPart.INTRANSITIVE_VERB});

    public static SentencePattern pattern2 = new SentencePattern(
            new GrammarPart[] {GrammarPart.SUBJECT, GrammarPart.TRANSITIVE_VERB, GrammarPart.DIRECT_OBJECT},
            new GrammarPart[] {GrammarPart.SUBJECT, GrammarPart.DIRECT_OBJECT, GrammarPart.INTRANSITIVE_VERB});

    ArrayList<GrammarPart> english;
    ArrayList<GrammarPart> korean;
    private SentencePattern(GrammarPart[] english, GrammarPart[] korean) {
        this.english = new ArrayList<>(Arrays.asList(english));
        this.korean = new ArrayList<>(Arrays.asList(korean));
    }

    public ArrayList<GrammarPart> getKorean() {
        return korean;
    }

    public ArrayList<GrammarPart> getEnglish() {
        return english;
    }

    public int length() {
        return english.size();
    }
}
