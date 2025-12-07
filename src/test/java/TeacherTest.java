import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import se.yrgo.teachers.*;

public class TeacherTest {
    private Question q1 = new Question("Q1", "1", Subject.OOP, 10, "hej");
    private Question q2 = new Question("Q2", "2", Subject.OOP, 10, "hej");


    @Test
    void getRandomQuestionsDoesNotExceedAvailableQuestions() {
        List<Question> list = List.of(q1);
        Teacher t = new Teacher("Hampus", Subject.OOP, list);

        List<Question> result = t.getRandomQuestions(10);

        assertEquals(1, result.size());
    }

    @Test
    void constructorDoesNotExposeOriginalList() {
        List<Question> original = new ArrayList<>(List.of(q1));
        Teacher t = new Teacher("Hampus", Subject.OOP, original);

        original.add(q2);

        assertEquals(1, t.getRandomQuestions(5).size());
    }
}