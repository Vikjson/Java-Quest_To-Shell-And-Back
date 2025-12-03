package se.yrgo.teachers;

/**
 * A record to store the questions that are read from the files
 * dev_tools_questions.txt and oop_questions.txt.
 */
public record Question(
        String question,
        String answer,
        Subject category,
        int points,
        String flavorText) {
}
