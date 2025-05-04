package id.my.aspian.a013.question;

import java.util.HashMap;
import java.util.List;

public class BasicQuestion {
    String question;
    List<String> answer;
    int correctAnswer; // index of answer
    public static HashMap<Integer, BasicQuestion> questions = new HashMap<>();

    static {
    }

    // TODO: inisiasi object Question dengan constructor berdasarkan level. Data diambil dari `questions`
    BasicQuestion(int level) {}

    public static void parseQuestion() {}
}
