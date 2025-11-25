package se.yrgo.teachers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Question> loadQuestions(String filename) {
        List<Question> questions = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(filename));

            for (String line : lines) {
                if (line.isBlank())
                    continue;

                String[] parts = line.split(";");

                String question = parts[0];
                String answer = parts[1];
                Subject category = Subject.valueOf(parts[2]);
                int points = Integer.parseInt(parts[3]);
                String flavor = parts[4];

                questions.add(new Question(question, answer, category, points, flavor));
            }

        } catch (IOException e) {
            System.out.println("Kunde inte läsa filen: " + filename);
        }

        return questions;
    }
}
