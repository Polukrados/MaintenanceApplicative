package trivia;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class QuestionDeck {
    private final Map<String, LinkedList<String>> questions;

    public QuestionDeck() {
        questions = new HashMap<>();
        questions.put("Pop", new LinkedList<>());
        questions.put("Science", new LinkedList<>());
        questions.put("Sports", new LinkedList<>());
        questions.put("Rock", new LinkedList<>());

        for (int i = 0; i < 50; i++) {
            questions.get("Pop").add("Pop Question " + i);
            questions.get("Science").add("Science Question " + i);
            questions.get("Sports").add("Sports Question " + i);
            questions.get("Rock").add("Rock Question " + i);
        }
    }

    public String getNextQuestion(String category) {
        return questions.getOrDefault(category, new LinkedList<>()).isEmpty() ?
                "No more questions in category " : questions.get(category).removeFirst();
    }
}
