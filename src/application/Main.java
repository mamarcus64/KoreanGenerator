package application;

import java.text.Normalizer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SentenceGenerator sentenceGenerator = SentenceGenerator.getInstance();
        Scanner buffer = new Scanner(System.in);
        while (true) {
            sentenceGenerator.generate();
            System.out.println(sentenceGenerator.english());
            System.out.println(sentenceGenerator.korean());
            if (buffer.nextLine().equalsIgnoreCase("exit")) {
                break;
            }
        }
    }

}
