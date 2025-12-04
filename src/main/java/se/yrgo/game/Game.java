package se.yrgo.game;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import se.yrgo.player.*;
import se.yrgo.teachers.*;


public class Game {
    private final Scanner scan;
    private final Player player;
    private boolean late = false;
    private Teacher hampus;
    private Teacher nahid;
    private Random randomTeacher = new Random();
    private Menu menu;

    public Game(Scanner scan, Menu menu) {
        this.scan = scan;
        this.player = new Player("Player Name");

        List<Question> oopQ = FileReader.loadQuestions("oop_questions.txt");
        List<Question> devQ = FileReader.loadQuestions("devtools_questions.txt");

        this.hampus = new Teacher("Hampus", Subject.OOP, oopQ);
        this.nahid = new Teacher("Nahid", Subject.DEVTOOLS, devQ);
    }


    public void startGame() {
        //Player setName
        for (int day = 1; day <= 4; day++) {
            playDay(day); // loop för dag 1–5, alla val osv
        }

        startExamDay();
    }

    private Teacher getRandomTeacher() {
        if (randomTeacher.nextBoolean()) {
            return hampus;
        } else {
            return nahid;
        }
    }

    private void startLesson(boolean late) {
        Teacher teacher = getRandomTeacher();
        System.out.println("Dagens lektion är " + teacher.getSubject() + " med " + teacher);

        teacher.shuffleQuestions();
        int amount = 3;
        List<Question> questions = teacher.getRandomQuestions(amount);

        if (late) {
            questions = teacher.getRandomQuestions(1);
        }

        for (Question q : questions) askQuestion(q);
    }

    private void askQuestion(Question q) {
        System.out.println(q.question());
        String answer = scan.nextLine();

        if (answer.equalsIgnoreCase(q.answer())) {
            System.out.println("RÄTT! " + q.flavorText());
            player.gainKnowledge(q.points());
        } else {
            System.out.println("Fel! Rätt svar var: " + q.answer());
            player.loseHealth(5);
        }
    }


    public void playDay(int day) {

        morning(day);
        lessonOne();
        lunch();
        evening();
    }


    public void morning(int day) {

        System.out.println("Dag " + day);


        System.out.println("God morgon! Du är hemma och har just vaknat.");
        System.out.println("Din hälsa är: " + player.getHealth() + " och din kunskapsnivå är: " + player.getKnowledge());


        if (!late) {
            System.out.println("Klockan är 5. Du mår skit, men fan vad du får pluggat! Och sen blir du heller inte, din jävla King!");

            player.loseHealth(10);
            player.gainKnowledge(10);
        } else {
            System.out.println("Du vaknar utvilad.");
            player.gainHealth(10);
            //random att försova sig if försov sig, late = true;
        }

    }

    public void lessonOne() {


        if (late) {
            System.out.println("Du missade bussen, nu igen! Nu kommer du för sent till lektionen och stapplar in 30 min efter föreläsningen startat. Pinsamt!");
            //Random med Nahid eller Hampus
            startLesson(true); //Array med frågor 1-6 st. If late: starta på 2
        } else {
            System.out.println("Du är i god tid till lektionen.");
            //Random med Nahid eller Hampus
            startLesson(false); //Array med frågor 1-6 st. startar på plats 0 i arrayen
        }
    }

    public void lunch() {

        System.out.println("Din hälsa är: " + player.getHealth() + " och din kunskapsnivå är: " + player.getKnowledge());
        System.out.println("Dags för lunchrast! Vad vill du göra?");
        System.out.println("1. Skippa lunchen och plugga"); //Få mer info, typ 2 meningar om något pluggigt.
        System.out.println("2. Ät en redig lunch");
        System.out.println("3. Hiva i dig en kaffe snabbt och plugga");
        System.out.println("4. Gå hem för dagen och \"plugga\"");

        String input = scan.nextLine();

        switch (input) {
            case "1" -> {
                player.gainKnowledge(10);
                player.loseHealth(10);
                System.out.println("Du pluggar som en gud! Men magen kurrar.");
            }
            case "2" -> {
                player.gainHealth(10);
                System.out.println("Du äter en riktigt god lunch. Mums!");
            }
            case "3" -> {
                player.gainKnowledge(10);
                player.loseHealth(10);
                System.out.println("FAN vad skarp du känner dig!");
            }
            case "4" -> {
                player.gainHealth(10);
                System.out.println("Åh vad skönt att bara lägga sig på soffan!");
            }
        }

        if (!input.equals("4")) {

            System.out.println("Du går på nästa lektion");
            lessonTwo();

        }
    }

    public void lessonTwo() {
        startLesson(false);

    }


    public void evening() {
        System.out.println("Du är hemma efter en hård dag i skolan. Vad vill du göra nu?");
        System.out.println("DIn hälsa är: " + player.getHealth() + " och din kunskapsnivå är: " + player.getKnowledge());

        System.out.println("1. Gör inget särskilt, går och lägger mig tidigt och Sover ut.");
        System.out.println("2. Stanna uppe sent och plugga hela kvällen");
        System.out.println("3. Gå ut och festa");
        System.out.println("4. Softa och kolla på serier hela kvällen, ställer klockan tidigt för att inte komma för sent nästa dag");

        String input = scan.nextLine();

        Random chanceOfBeingLate = new Random();

        switch (input) {
            case "1" -> {
                player.gainHealth(20);
                late = chanceOfBeingLate.nextInt(100) < 10;
            }
            case "2" -> {
                player.gainKnowledge(20);
                late = chanceOfBeingLate.nextInt(100) < 25;
            }
            case "3" -> {
                player.loseHealth(20);
                player.loseKnowledge(20);
                late = true;
            }
            case "4" -> late = false;
        }
    }


    public void startExamDay() {
        System.out.println("Idag är det tenta!");
        if (late) {
            System.out.println("Du har försovit dig och missade tentan. Du är körd!");
            System.out.println("GAME OVER");
            menu.startMenu();
        } else {
            startExam(); //Här ska avgöras om man vinner eller inte + gå till Menu.start();
        }

    }

    public void startExam() {

        if (player.getKnowledge() <= 60) {
            System.out.println("Hurra! Du klarade provet. Du fick G.");
        } else if (player.getKnowledge() <= 80) {
            System.out.println("Herre gud, vilken stjärna du är! Du inte bara klarade provet, du fick VG! Grattis!");
        } else if (player.getKnowledge() <= 49) {
            System.out.println("Nae, det här gick inget vidare. Du failade hårt och inser att du borde ha pluggat mer.");
            System.out.println("GAME OVER");
        }
    }


}


//Gör metod för prov med villkor för att klara baserat på poäng
