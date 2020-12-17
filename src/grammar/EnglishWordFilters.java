package grammar;

import words.Word;

public class EnglishWordFilters {

    public static void capitalize(Word input) {
        input.english = capitalize(input.english);
    }

    public static String capitalize(String input) {
        return input.length() == 0 ? input : input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

}
