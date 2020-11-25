package words;

public abstract class Word {
    public String english;
    public String korean;
    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }

    @Override
    public String toString() {
        return english + ";" + korean;
    }
}
