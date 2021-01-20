package grammar;

import application.Constants;
import words.Word;

public class KoreanWordFilters {

    private static KoreanWordFilters instance;

    private final String leads = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";// list of initials
    private final String vowels = "ᅡᅢᅣᅤᅥᅦᅧᅨᅩᅪᅫᅬᅭᅮᅯᅰᅱᅲᅳᅴᅵ";// list of vowels
    private final String tails = "ᆨᆩᆪᆫᆬᆭᆮᆯᆰᆱᆲᆳᆴᆵᆶᆷᆸᆹᆺᆻᆼᆽᆾᆿᇀᇁᇂ";// list of tails

    private final String leadsOutOfPosition = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";
    private final String vowelsOutOfPosition = "ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ";
    private final String tailsOutOfPosition = "ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ";

    public enum Position {
        LEAD, VOWEL, TAIL;
    }

    public static KoreanWordFilters getInstance() {
        if (instance == null) {
            instance = new KoreanWordFilters();
        }
        return instance;
    }

    private KoreanWordFilters() {

    }

    private String combineBlocks(String initial, String appended) {
        if (endsInVowel(initial)) {
            String[] appendedParts = toLetters(appended);
            String[] resultParts = toLetters(initial);
            switch (appendedParts[1]) {
                case "ㅏ":
                    if (appendedParts[0].equals("ㅎ")) {
                        resultParts[1] = "ㅐ";
                    } else {
                        resultParts[1] = "ㅏ";
                    }
                    break;
                case "ㅗ":
                    resultParts[1] = "ㅘ";
                    break;
                case "ㅓ":
                    resultParts[1] = "ㅓ";
                    break;
                case "ㅜ":
                case "ㅣ":
                    resultParts[1] = "ㅝ";
                    break;
                case "ㅕ":
                    resultParts[1] = "ㅕ";
                    break;
                default:
                    throw new IllegalArgumentException("Illegal vowel in verb stem.");
            }
            resultParts[2] = appendedParts[2];
            return toBlock(resultParts);
        } else {
            return initial + appended;
        }
    }

    public void tensify(Word input, Constants.Tense tense, Constants.Honorific honorific) {
        if (input == null) {
            return;
        }
        String stem = input.korean.substring(0, input.korean.length() - 1);
        StringBuilder koreanResult = new StringBuilder(stem);
        String[] lastSyllable = toLetters(stem.charAt(stem.length() - 1));
        switch (tense) {
            case PAST:
                String updatedEnding;
                if (lastSyllable[1].equals("ㅏ") || lastSyllable[1].equals("ㅗ")) {
                    updatedEnding = combineBlocks(toBlock(lastSyllable), "았");
                } else {
                    updatedEnding = combineBlocks(toBlock(lastSyllable), "었");
                }
                koreanResult.replace(koreanResult.length() - 1, koreanResult.length(), updatedEnding);
                break;
            case PRESENT:
                break;
            case FUTURE_NEUTRAL:
                break;
            case FUTURE_CONFIDENT:
                break;
            default:
                break;
        }
        switch (honorific) {
            case PLAIN:
                koreanResult.replace(koreanResult.length() - 1, koreanResult.length(),
                        combineBlocks(toBlock(lastSyllable), "다"));
                break;
            case INFORMAL_LOW:
                koreanResult.replace(koreanResult.length() - 1, koreanResult.length(),
                        combineBlocks(toBlock(lastSyllable), "어"));
                break;
            case INFORMAL_HIGH:
                koreanResult.replace(koreanResult.length() - 1, koreanResult.length(),
                        combineBlocks(toBlock(lastSyllable), "어요"));
                break;
            case FORMAL_HIGH:
                koreanResult.replace(koreanResult.length() - 1, koreanResult.length(),
                        combineBlocks(toBlock(lastSyllable), "습니다"));
                break;
            default:
        }
        input.korean = koreanResult.toString();
    }

    public void subjectify(Word input) {
        if (input == null) {
            return;
        }
        if (endsInVowel(input.korean)) {
            input.korean = input.korean + "는";
        } else {
            input.korean = input.korean + "은";
        }
    }

    public void objectify(Word input) {
        if (input == null) {
            return;
        }
        if (endsInVowel(input.korean)) {
            input.korean = input.korean + "를";
        } else {
            input.korean = input.korean + "을";
        }
    }

    public boolean endsInVowel(String string) {
        return toLetters(string.charAt(string.length() - 1))[2].equals("");
    }

    public String[] toLetters(String character) {
        return toLetters(character.charAt(0));
    }

    public String[] toLetters(char character) {
        // the following characters are in the correct (i.e. Unicode) order
        final String initials = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";// list of initials
        final String vowels = "ᅡᅢᅣᅤᅥᅦᅧᅨᅩᅪᅫᅬᅭᅮᅯᅰᅱᅲᅳᅴᅵ";// list of vowels
        final String finals = "ᆨᆩᆪᆫᆬᆭᆮᆯᆰᆱᆲᆳᆴᆵᆶᆷᆸᆹᆺᆻᆼᆽᆾᆿᇀᇁᇂ";// list of tail characters
        final int characterValue = character; // Unicode value
        final int hangulUnicodeStartValue = 44032;
        if (characterValue < hangulUnicodeStartValue) {
            throw new IllegalArgumentException();
        }
        final int tailIndex = Math.round((characterValue - hangulUnicodeStartValue) % 28) - 1;
        final int vowelIndex = Math.round(((characterValue - hangulUnicodeStartValue - tailIndex) % 588) / 28);
        final int initialIndex = (characterValue - hangulUnicodeStartValue) / 588;
        final String lead = initials.substring(initialIndex, initialIndex + 1);
        final String vowel = vowels.substring(vowelIndex, vowelIndex + 1);
        final String tail = tailIndex == -1 ? "" : finals.substring(tailIndex, tailIndex + 1);
        // may be -1 when there is no tail character
        return new String[] {lead, vowel, tail};
    }

    public String toBlock(String[] letters) {
        final int hangulUnicodeStartValue = 44032;
        return Character.toString((char) (hangulUnicodeStartValue
                + 588 * leadsOutOfPosition.indexOf(letters[0])
                + 28 * vowelsOutOfPosition.indexOf(letters[1])
                + tailsOutOfPosition.indexOf(letters[2]) + 1));
    }

    public String addTail(String block, String tail) {
        return addTail(toLetters(block), tail);
    }

    public String addTail(String[] block, String tail) {
        String[] newBlock = new String[] {block[0], block[1], tail};
        return toBlock(newBlock);
    }

    public String letterInPosition(String letter, Position pos) throws IllegalArgumentException {
        return letterInPosition(letter.charAt(0), pos);
    }

    public String letterInPosition(char letter, Position pos) throws IllegalArgumentException {
        if (pos == Position.LEAD) {
            switch(letter) {
                case 'ㄱ': return leads.substring(0, 1);
                case 'ㄲ': return leads.substring(1, 2);
                case 'ㄴ': return leads.substring(2, 3);
                case 'ㄷ': return leads.substring(3, 4);
                case 'ㄸ': return leads.substring(4, 5);
                case 'ㄹ': return leads.substring(5, 6);
                case 'ㅁ': return leads.substring(6, 7);
                case 'ㅂ': return leads.substring(7, 8);
                case 'ㅃ': return leads.substring(8, 9);
                case 'ㅅ': return leads.substring(9, 10);
                case 'ㅆ': return leads.substring(10, 11);
                case 'ㅇ': return leads.substring(11, 12);
                case 'ㅈ': return leads.substring(12, 13);
                case 'ㅉ': return leads.substring(13, 14);
                case 'ㅊ': return leads.substring(14, 15);
                case 'ㅋ': return leads.substring(15, 16);
                case 'ㅌ': return leads.substring(16, 17);
                case 'ㅍ': return leads.substring(17, 18);
                case 'ㅎ': return leads.substring(18, 19);
                default: throw new IllegalArgumentException();
            }
        } else if (pos == Position.VOWEL) {
            switch (letter) {
                case 'ㅏ': return vowels.substring(0, 1);
                case 'ㅐ': return vowels.substring(1, 2);
                case 'ㅑ': return vowels.substring(2, 3);
                case 'ㅒ': return vowels.substring(3, 4);
                case 'ㅓ': return vowels.substring(4, 5);
                case 'ㅔ': return vowels.substring(5, 6);
                case 'ㅕ': return vowels.substring(6, 7);
                case 'ㅖ': return vowels.substring(7, 8);
                case 'ㅗ': return vowels.substring(8, 9);
                case 'ㅘ': return vowels.substring(9, 10);
                case 'ㅙ': return vowels.substring(10, 11);
                case 'ㅚ': return vowels.substring(11, 12);
                case 'ㅛ': return vowels.substring(12, 13);
                case 'ㅜ': return vowels.substring(13, 14);
                case 'ㅝ': return vowels.substring(14, 15);
                case 'ㅞ': return vowels.substring(15, 16);
                case 'ㅟ': return vowels.substring(16, 17);
                case 'ㅠ': return vowels.substring(17, 18);
                case 'ㅡ': return vowels.substring(18, 19);
                case 'ㅢ': return vowels.substring(19, 20);
                case 'ㅣ': return vowels.substring(20, 21);
                default:
                    System.out.println(letter);
                    throw new IllegalArgumentException();
            }
        } else {
            switch (letter) {
                case 'ㄱ': return tails.substring(0, 1);
                case 'ㄲ': return tails.substring(1, 2);
                case 'ㄳ': return tails.substring(2, 3);
                case 'ㄴ': return tails.substring(3, 4);
                case 'ㄵ': return tails.substring(4, 5);
                case 'ㄶ': return tails.substring(5, 6);
                case 'ㄷ': return tails.substring(6, 7);
                case 'ㄹ': return tails.substring(7, 8);
                case 'ㄺ': return tails.substring(8, 9);
                case 'ㄻ': return tails.substring(9, 10);
                case 'ㄼ': return tails.substring(10, 11);
                case 'ㄽ': return tails.substring(11, 12);
                case 'ㄾ': return tails.substring(12, 13);
                case 'ㄿ': return tails.substring(13, 14);
                case 'ㅀ': return tails.substring(14, 15);
                case 'ㅁ': return tails.substring(15, 16);
                case 'ㅂ': return tails.substring(16, 17);
                case 'ㅄ': return tails.substring(17, 18);
                case 'ㅅ': return tails.substring(18, 19);
                case 'ㅆ': return tails.substring(19, 20);
                case 'ㅇ': return tails.substring(20, 21);
                case 'ㅈ': return tails.substring(21, 22);
                case 'ㅊ': return tails.substring(22, 23);
                case 'ㅋ': return tails.substring(23, 24);
                case 'ㅌ': return tails.substring(24, 25);
                case 'ㅍ': return tails.substring(25, 26);
                case 'ㅎ': return tails.substring(26, 27);
                default: throw new IllegalArgumentException();
            }
        }
    }
}
