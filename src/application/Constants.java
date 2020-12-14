package application;

public class Constants {
    public static final int FIRST_LESSON = 1;
    public static final int LAST_LESSON = 3;
    public static final String[] EXCLUDED_LESSONS = {"2.csv"};

    enum Words {
        NOUN(0), VERB(1), ADJECTIVE(2);

        public int val;
        private Words(int val) {
            this.val = val;
        }
    }
}