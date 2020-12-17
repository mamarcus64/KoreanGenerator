package grammar;

import words.Noun;
import words.Verb;

public enum GrammarPart {
    SUBJECT("noun"),
    DIRECT_OBJECT("noun"),
    TRANSITIVE_VERB("verb"),
    INTRANSITIVE_VERB("verb");

    private String word;

    private GrammarPart(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
