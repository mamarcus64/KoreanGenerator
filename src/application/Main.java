package application;

public class Main {
    public static void main(String[] args) {
        SentenceGenerator sentenceGenerator = SentenceGenerator.getInstance();
        System.out.println(sentenceGenerator.english());
        System.out.println(sentenceGenerator.korean());
    }

}
