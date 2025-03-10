package trivia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionDeckTest {

    @Test
    public void testGetNextQuestion() {
        QuestionDeck deck = new QuestionDeck();
        String question = deck.getNextQuestion("Pop");
        assertEquals("Pop Question 0", question); // Vérifier la première question
    }

    @Test
    public void testGetNextQuestionWhenEmpty() {
        QuestionDeck deck = new QuestionDeck();
        for (int i = 0; i < 50; i++) { deck.getNextQuestion("Rock"); } // Vider la pile
        assertEquals("No more questions in category ", deck.getNextQuestion("Rock"));
    }
}
