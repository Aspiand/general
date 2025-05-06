package id.my.aspian.a013;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    private final Random rand = new Random();

    public Question generate(int maxOperand) {
        boolean isAddition = rand.nextBoolean();

        int a = rand.nextInt(maxOperand + 1);
        int b = rand.nextInt(maxOperand + 1);
        String op = isAddition ? "+" : "-";

        if (!isAddition && a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        String questionText = String.format("%d %s %d = ?", a, op, b);
        int correct = isAddition ? (a + b) : (a - b);

        List<String> opts = new ArrayList<>();
        opts.add(String.valueOf(correct));

        while (opts.size() < 4) {
            int fake = correct + rand.nextInt(7) - 3;
            if (fake != correct && fake >= 0) {
                opts.add(String.valueOf(fake));
            }
        }

        Collections.shuffle(opts);

        return new Question(questionText, opts, String.valueOf(correct));
    }
}