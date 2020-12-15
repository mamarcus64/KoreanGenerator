package application;

public class Constants {
    public static final int FIRST_LESSON = 1;
    public static final int LAST_LESSON = 3;
    public static final int[] EXCLUDED_LESSONS = {2};
    public static boolean DEBUG_ON = true;
    enum Words {
        NOUN("noun"), VERB("verb"), ADJECTIVE("adjective"), OTHER("other");

        public String val;
        private Words(String val) {
            this.val = val;
        }
    }
}