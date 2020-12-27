package grammar;

public class KoreanWordFilters {
    private static final String initials = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";// list of initials
    private static final String vowels = "ᅡᅢᅣᅤᅥᅦᅧᅨᅩᅪᅫᅬᅭᅮᅯᅰᅱᅲᅳᅴᅵ";// list of vowels
    private static final String finals = "ᆨᆩᆪᆫᆬᆭᆮᆯᆰᆱᆲᆳᆴᆵᆶᆷᆸᆹᆺᆻᆼᆽᆾᆿᇀᇁᇂ";// list of tails

    public enum Position {
        LEAD, VOWEL, TAIL;
    }

    public boolean endsInVowel(char character) {
        return vowels.contains(Character.toString(toLetters(character)[1]));
    }

    public static char[] toLetters(char character) {
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
        return new char[] {lead.toCharArray()[0], vowel.toCharArray()[0], tail.toCharArray()[0]};
    }

    public static char letterInPosition(char letter, Position pos) throws IllegalAccessException {
        if (pos == Position.LEAD) {
            switch(letter) {
                case 'ㄱ': return initials.substring(0, 1).toCharArray()[0];
                case 'ㄲ': return initials.substring(1, 2).toCharArray()[0];
                case 'ㄴ': return initials.substring(2, 3).toCharArray()[0];
                case 'ㄷ': return initials.substring(3, 4).toCharArray()[0];
                case 'ㄸ': return initials.substring(4, 5).toCharArray()[0];
                case 'ㄹ': return initials.substring(5, 6).toCharArray()[0];
                case 'ㅁ': return initials.substring(6, 7).toCharArray()[0];
                case 'ㅂ': return initials.substring(7, 8).toCharArray()[0];
                case 'ㅃ': return initials.substring(8, 9).toCharArray()[0];
                case 'ㅅ': return initials.substring(9, 10).toCharArray()[0];
                case 'ㅆ': return initials.substring(10, 11).toCharArray()[0];
                case 'ㅇ': return initials.substring(11, 12).toCharArray()[0];
                case 'ㅈ': return initials.substring(12, 13).toCharArray()[0];
                case 'ㅉ': return initials.substring(13, 14).toCharArray()[0];
                case 'ㅊ': return initials.substring(14, 15).toCharArray()[0];
                case 'ㅋ': return initials.substring(15, 16).toCharArray()[0];
                case 'ㅌ': return initials.substring(16, 17).toCharArray()[0];
                case 'ㅍ': return initials.substring(17, 18).toCharArray()[0];
                case 'ㅎ': return initials.substring(18, 19).toCharArray()[0];
                default: throw new IllegalAccessException();
            }
        } else if (pos == Position.VOWEL) {
            switch (letter) {
                case 'ㅏ': return vowels.substring(0, 1).toCharArray()[0];
                case 'ㅐ': return vowels.substring(1, 2).toCharArray()[0];
                case 'ㅑ': return vowels.substring(2, 3).toCharArray()[0];
                case 'ㅒ': return vowels.substring(3, 4).toCharArray()[0];
                case 'ㅓ': return vowels.substring(4, 5).toCharArray()[0];
                case 'ㅔ': return vowels.substring(5, 6).toCharArray()[0];
                case 'ㅕ': return vowels.substring(6, 7).toCharArray()[0];
                case 'ㅖ': return vowels.substring(7, 8).toCharArray()[0];
                case 'ㅗ': return vowels.substring(8, 9).toCharArray()[0];
                case 'ㅘ': return vowels.substring(9, 10).toCharArray()[0];
                case 'ㅙ': return vowels.substring(10, 11).toCharArray()[0];
                case 'ㅚ': return vowels.substring(11, 12).toCharArray()[0];
                case 'ㅛ': return vowels.substring(12, 13).toCharArray()[0];
                case 'ㅜ': return vowels.substring(13, 14).toCharArray()[0];
                case 'ㅝ': return vowels.substring(14, 15).toCharArray()[0];
                case 'ㅞ': return vowels.substring(15, 16).toCharArray()[0];
                case 'ㅟ': return vowels.substring(16, 17).toCharArray()[0];
                case 'ㅠ': return vowels.substring(17, 18).toCharArray()[0];
                case 'ㅡ': return vowels.substring(18, 19).toCharArray()[0];
                case 'ㅢ': return vowels.substring(19, 20).toCharArray()[0];
                case 'ㅣ': return vowels.substring(20, 21).toCharArray()[0];
                default: throw new IllegalAccessException();
            }
        } else {
            switch (letter) {
                case 'ㄱ': return finals.substring(0, 1).toCharArray()[0];
                case 'ㄲ': return finals.substring(1, 2).toCharArray()[0];
                case 'ㄳ': return finals.substring(2, 3).toCharArray()[0];
                case 'ㄴ': return finals.substring(3, 4).toCharArray()[0];
                case 'ㄵ': return finals.substring(4, 5).toCharArray()[0];
                case 'ㄶ': return finals.substring(5, 6).toCharArray()[0];
                case 'ㄷ': return finals.substring(6, 7).toCharArray()[0];
                case 'ㄹ': return finals.substring(7, 8).toCharArray()[0];
                case 'ㄺ': return finals.substring(8, 9).toCharArray()[0];
                case 'ㄻ': return finals.substring(9, 10).toCharArray()[0];
                case 'ㄼ': return finals.substring(10, 11).toCharArray()[0];
                case 'ㄽ': return finals.substring(11, 12).toCharArray()[0];
                case 'ㄾ': return finals.substring(12, 13).toCharArray()[0];
                case 'ㄿ': return finals.substring(13, 14).toCharArray()[0];
                case 'ㅀ': return finals.substring(14, 15).toCharArray()[0];
                case 'ㅁ': return finals.substring(15, 16).toCharArray()[0];
                case 'ㅂ': return finals.substring(16, 17).toCharArray()[0];
                case 'ㅄ': return finals.substring(17, 18).toCharArray()[0];
                case 'ㅅ': return finals.substring(18, 19).toCharArray()[0];
                case 'ㅆ': return finals.substring(19, 20).toCharArray()[0];
                case 'ㅇ': return finals.substring(20, 21).toCharArray()[0];
                case 'ㅈ': return finals.substring(21, 22).toCharArray()[0];
                case 'ㅊ': return finals.substring(22, 23).toCharArray()[0];
                case 'ㅋ': return finals.substring(23, 24).toCharArray()[0];
                case 'ㅌ': return finals.substring(24, 25).toCharArray()[0];
                case 'ㅍ': return finals.substring(25, 26).toCharArray()[0];
                case 'ㅎ': return finals.substring(26, 27).toCharArray()[0];
                default: throw new IllegalAccessException();
            }
        }
    }
}
