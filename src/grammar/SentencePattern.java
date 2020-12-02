package grammar;

import words.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentencePattern {

    public static SentencePattern pattern1 = new SentencePattern(
            new ArrayList<GrammarPart>(Arrays.asList(new GrammarPart[]
                    {GrammarPart.SUBJECT, GrammarPart.INTRANSITIVE_VERB})));
    List<GrammarPart> pattern;
    private SentencePattern(List<GrammarPart> pattern) {
        this.pattern = pattern;
    }

    public List<GrammarPart> getPattern() {
        return pattern;
    }

    public int length() {
        return pattern.size();
    }
}
