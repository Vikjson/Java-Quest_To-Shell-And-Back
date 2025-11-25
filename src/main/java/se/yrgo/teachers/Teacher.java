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

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    public List<Question> getRandomQuestions(int amount) {
        return questions.subList(0, Math.min(amount, questions.size()));
    }
    
}