package grammar;

public class EnglishWordFilters {
    public static String capitalize(String input) {
        return input.length() == 0 ? input : input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
