package grammar;

import words.Noun;
import words.Verb;

public enum GrammarPart {
    SUBJECT(Noun.class),
    DIRECT_OBJECT(Noun.class),
    TRANSITIVE_VERB(Verb.class),
    INTRANSITIVE_VERB(Verb.class);

    private Class word;

    private GrammarPart(Class word) {
        this.word = word;
    }

    public Class getWord() {
        return word;
    }
}
