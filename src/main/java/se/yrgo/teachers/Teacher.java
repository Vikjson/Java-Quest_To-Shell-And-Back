package se.yrgo.teachers;

import java.util.*;

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
     * shuffles the list of questions so that you don't allways get the same questions.
     */
    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    /**
     *
     * This method gives you any number of questions for a lecture if questions are random.
     *
     * @param amount the number of questions you want to ask this lecture.
     * @return number of questions equal to the parameter amount.
     */
    public List<Question> getRandomQuestions(int amount) {
        return questions.subList(0, Math.min(amount, questions.size()));
    }

}