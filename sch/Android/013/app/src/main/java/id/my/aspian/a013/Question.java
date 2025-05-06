package id.my.aspian.a013;

import java.util.List;

public class Question {
    private final String text;
    private final List<String> options;
    private final String correctAnswer;

    public Question(String text, List<String> options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(String choice) {
        return correctAnswer.equals(choice);
    }
}
