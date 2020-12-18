package grammar;

import application.Constants;
import words.Word;

public class EnglishWordFilters {

    public static void capitalize(Word input) {
        if (input == null) {
            return;
        }
        input.english = capitalize(input.english);
    }

    public static String capitalize(String input) {
        return input.length() == 0 ? input : input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static void articulate(Word input) {
        if (input == null) {
            return;
        }
        input.english = "the " + input.english;
    }

    public static void toTense(Word input, Constants.Tenses tense, Constants.Person person) {
        if (input == null) {
            return;
        }
        String base = input.english;
        base = "will " + base;
//        switch (tense) {
//            case PAST: break;
//            case PRESENT: base += "ed"; break;
//            case FUTURE: base = "will " + base; break;
//            default: break;
//        }
//        switch (person) {
//            case FIRST: break;
//            case SECOND: break;
//            case THIRD: if(tense == Constants.Tenses.PRESENT) base += "s"; break;
//            default: break;
//        }
        input.english = base;
    }

}
