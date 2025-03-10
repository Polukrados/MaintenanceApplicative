package trivia;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

public class QuestionDeck {
    private final Map<String, LinkedList<String>> questions;

    public QuestionDeck() {
        questions = new HashMap<>();
        questions.put("Pop", new LinkedList<>());
        questions.put("Science", new LinkedList<>());
        questions.put("Sports", new LinkedList<>());
        questions.put("Rock", new LinkedList<>());
        questions.put("Geography", new LinkedList<>());

        loadQuestions("Pop", "pop.properties");
        loadQuestions("Science", "science.properties");
        loadQuestions("Sports", "sports.properties");
        loadQuestions("Rock", "rock.properties");
        loadQuestions("Geography", "geography.properties");
    }

    private void loadQuestions(String category, String filename) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("properties/"+filename)) {
            if (input == null) {
                System.out.println("Could not find " + filename);
                return;
            }
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                questions.get(category).add(properties.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getNextQuestion(String category) {
        return questions.getOrDefault(category, new LinkedList<>()).isEmpty() ?
                "No more questions in category " : questions.get(category).removeFirst();
    }

    public int size() {
        return questions.size();
    }

    public String getCategory(int adjustedPosition) {
        return (String) questions.keySet().toArray()[adjustedPosition];
    }
}
