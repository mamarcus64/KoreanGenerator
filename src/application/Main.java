package application;

import grammar.KoreanWordFilters;

import java.text.Normalizer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SentenceGenerator sentenceGenerator = SentenceGenerator.getInstance();
        Scanner buffer = new Scanner(System.in);
        while (true) {
            boolean coinFlip = Math.random() > 0.5;
            sentenceGenerator.generate();
            System.out.println(sentenceGenerator.metadata());
            System.out.println(coinFlip ? sentenceGenerator.english() : sentenceGenerator.korean());
            buffer.nextLine();
            System.out.println(coinFlip ? sentenceGenerator.korean() : sentenceGenerator.english());
            buffer.nextLine();
        }
    }

}
