package application;

import java.util.Arrays;

public class Constants {
    public static final int FIRST_LESSON = 1;
    public static final int LAST_LESSON = 3;
    public static final int[] EXCLUDED_LESSONS = {2};
    public static boolean DEBUG_ON = true;

    public enum Tense {
        PRESENT,
        PAST,
        FUTURE_NEUTRAL,
        FUTURE_CONFIDENT
    }

    public enum Honorific {
        PLAIN,
        INFORMAL_LOW,
        INFORMAL_HIGH,
        FORMAL_HIGH
    }

    public enum Person {
        FIRST,
        SECOND,
        THIRD
    }

    public enum Words {
        NOUN("noun"), VERB("verb"), ADJECTIVE("adjective"),
        ADVERB("adverb"), PREPOSITION("preposition"), OTHER("other");

        public String val;
        private Words(String val) {
            this.val = val;
        }
    }

    public static int wordTypeNumber() {
        return Words.values().length;
    }

    public static int wordType(String word) {
        return wordType(Words.valueOf(word.toUpperCase()));
    }

    public static int wordType(Words word) {
        return Arrays.asList(Words.values()).indexOf(word);
    }
}