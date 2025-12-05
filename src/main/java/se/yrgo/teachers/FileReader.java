package se.yrgo.teachers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that reads files line-by-line and splits the lines into
 * the five parts: question, answer, category, knowledgePoints and flavor.
 */
public class FileReader {

    /**
     * Converts escaped newline sequences into actual line breaks.
     * 
     * @param text the input text that may contain escaped newline markers.
     * @return the text with all escaped newlines markers replaced by real line
     *         breaks.
     */
    private static String decode(String text) {
        return text.replace("\\n", "\n");
    }

    /**
     * Loads a list of questions from a semicolon-separated file located
     * in the application's resources directory. Each non-blank line is
     * expected to contain five parts: question, answer, category,
     * knowledge points, and flavor text.
     * 
     * @param filename the name of the resource file to read (must be located under
     *                 src/main/resources)
     * @return a list of {@link Question} objects parsed from the file; returns an
     *         empty list if the file cannot be read
     */
    public static List<Question> loadQuestions(String filename) {
        List<Question> questions = new ArrayList<>();

        InputStream in = FileReader.class.getResourceAsStream("/" + filename);
        if (in == null) {
            System.out.println("Kunde inte läsa filen: " + filename + " (finns den i src/main/resources?)");
            return questions;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.isBlank())
                    continue;

                String[] parts = line.split(";");

                String question = decode(parts[0]);
                String answer = parts[1];
                Subject category = Subject.valueOf(parts[2]);
                int knowledgePoints = Integer.parseInt(parts[3]);
                String flavor = decode(parts[4]);

                questions.add(new Question(question, answer, category, knowledgePoints, flavor));
            }

        } catch (IOException e) {
            System.out.println("Kunde inte läsa filen: " + filename);
        }

        return questions;
    }
}