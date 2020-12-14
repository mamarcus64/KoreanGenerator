package grammar;

import words.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentencePattern {

    public static SentencePattern pattern1 = new SentencePattern(new GrammarPart[]
            {GrammarPart.SUBJECT, GrammarPart.INTRANSITIVE_VERB});

    GrammarPart[] pattern;
    private SentencePattern(GrammarPart[] pattern) {
        this.pattern = pattern;
    }

    public GrammarPart[] getPattern() {
        return pattern;
    }

    public int length() {
        return pattern.length;
    }
}
