package se.yrgo.teachers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Question> loadQuestions(String filename) {
        List<Question> questions = new ArrayList<>();

        InputStream in = FileReader.class.getResourceAsStream("/" + filename);
        if (in == null) {
            System.out.println("Kunde inte läsa filen: " + filename);
            return questions;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank())
                    continue;

                String[] parts = line.split(";");

                String question = parts[0];
                String answer = parts[1];
                Subject category = Subject.valueOf(parts[2]);
                int knowledgePoints = Integer.parseInt(parts[3]);
                String flavor = parts[4];

                questions.add(new Question(question, answer, category, knowledgePoints, flavor));
            }

        } catch (IOException e) {
            System.out.println("Kunde inte läsa filen: " + filename);
        }

        return questions;
    }
}