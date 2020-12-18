package words;

public abstract class Word implements Cloneable {
    public String english;
    public String korean;

    public Word(String english, String korean) {
        this.english = english;
        this.korean = korean;
    }

    public Word clone() {
        try {
            return (Word) (super.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return english + ";" + korean;
    }
}
