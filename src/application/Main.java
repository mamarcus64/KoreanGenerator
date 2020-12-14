package application;

public class Main {
    public static void main(String[] args) {
        SentenceGenerator sentenceGenerator = SentenceGenerator.getInstance();
        System.out.println(sentenceGenerator.english());
        System.out.println(sentenceGenerator.korean());
        Constants.Words[] wordTypes = Constants.Words.values();
        for (Constants.Words word : wordTypes) {
            System.out.println(word.val);
        }
    }

}
