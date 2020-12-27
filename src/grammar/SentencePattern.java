package grammar;

import java.util.ArrayList;
import java.util.Arrays;

public class SentencePattern {

    public static SentencePattern pattern1 = new SentencePattern(
            new GrammarPart[] {GrammarPart.SUBJECT, GrammarPart.INTRANSITIVE_VERB},
            new Integer[] {0, 1});

    public static SentencePattern pattern2 = new SentencePattern(
            new GrammarPart[] {GrammarPart.SUBJECT, GrammarPart.TRANSITIVE_VERB, GrammarPart.DIRECT_OBJECT},
            new Integer[] {0, 2, 1});

    ArrayList<GrammarPart> pattern;
    ArrayList<Integer> koreanOrder;
    private SentencePattern(GrammarPart[] english, Integer[] koreanOrder) {
        this.pattern = new ArrayList<>(Arrays.asList(english));
        this.koreanOrder = new ArrayList<>(Arrays.asList(koreanOrder));
    }

    public ArrayList<Integer> getKoreanOrder() {
        return koreanOrder;
    }

    public ArrayList<GrammarPart> getPattern() {
        return pattern;
    }

    public int length() {
        return pattern.size();
    }
}
