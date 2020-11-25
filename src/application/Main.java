package application;

public class Main {
    private static SentenceGenerator sentenceGenerator;
    public static void main(String[] args) {
        sentenceGenerator = SentenceGenerator.getInstance();
        System.out.println(sentenceGenerator.english());
        System.out.println(sentenceGenerator.korean());
    }

}
