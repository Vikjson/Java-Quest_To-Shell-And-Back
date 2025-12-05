package se.yrgo.teachers;

import java.util.*;

/**
 * Represents a teacher who can ask questions from a predefined list.
 * Holds the teacher's name, subject, and collection of questions.
 */
public class Teacher {

    private final String name;
    private final Subject subject;
    private List<Question> questions;

    public Teacher(String name, Subject subject, List<Question> questions) {
        this.name = name;
        this.subject = subject;
        this.questions = new ArrayList<>(questions);
    }

    public String getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    /**
     * Shuffles the internal list of questions so that the order becomes random.
     */
    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    /**
     * 
     * Returns a set number of questions starting from the beginning of the
     * shuffled question list. The returned number will never exceed the size
     * of the available questions.
     * 
     * @param amount the number of questions you want to ask this lecture.
     * @return number of questions equal to the parameter amount.
     */
    public List<Question> getRandomQuestions(int amount) {
        return questions.subList(0, Math.min(amount, questions.size()));
    }

}