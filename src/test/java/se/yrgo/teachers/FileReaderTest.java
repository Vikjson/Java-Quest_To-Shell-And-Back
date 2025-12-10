package se.yrgo.teachers;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FileReaderTest {

    /**
     * Ensures that a valid file is parsed correctly into Question objects.
     */
    @Test
    void loadQuestionsReadsValidFile() {
        List<Question> questions = FileReader.loadQuestions("dev_tools_questions.txt");

        assertEquals(2, questions.size(), "Should load exactly two questions");

        Question q1 = questions.get(0);
        assertEquals("Vilket Maven-kommando tar bort target-katalogen och kompilerar projektet på nytt?%n1. mvn clean package%n2. mvn compile%n3. mvn install", q1.question());
        assertEquals("1", q1.answer());
        assertEquals(Subject.DEVTOOLS, q1.category());
        assertEquals(15, q1.points());
        assertEquals("Bra jobbat, du kan verkligen det här med Maven-kommandon!", q1.flavorText());
    }
    
    /**
     * Ensures that missing files return an empty list instead of null or exception.
     */
    @Test
    void loadQuestionsReturnsEmptyListIfFileNotFound() {
        List<Question> questions = FileReader.loadQuestions("no_such_file.txt");
        assertTrue(questions.isEmpty(), "Non-existent file should return an empty list");
    }

    /**
     * Ensures that blank lines are skipped gracefully.
     */
    @Test
    void loadQuestionsSkipsBlankLines() {
        List<Question> questions = FileReader.loadQuestions("oop_questions.txt");

        // File contains 3 lines: two valid questions + one blank line
        assertEquals(2, questions.size(), "Blank lines must be ignored");
    }
}