package se.yrgo.teachers;

public record Question(
        String question,
        String answer,
        Subject category,
        int points,
        String flavorText) {
}
