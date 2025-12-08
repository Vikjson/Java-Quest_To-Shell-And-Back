package se.yrgo.game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import se.yrgo.player.*;
import se.yrgo.teachers.*;


/**
 * The Game class represent the main game loop for the Yrgo school simulator game Java Quest To $hell and Back.
 * The game loops over 4 days and finally ends with the exam, where the game ends and you either win or lose
 * depending on your scores. Each day in game consists of morning, lessons, lunch, and evening.
 * The player gets to chose activities that affect the players points received or taken away.
 * <p>
 * Questions are loaded from two text files, creates teachers depending on the subject and
 * lets the player answer questions, rewarding points if correctly answered.
 */
public class Game {
    private final Scanner scan;
    private final Player player;
    private boolean late = false;
    private Teacher hampus;
    private Teacher nahid;
    private Random randomTeacher = new Random();
    private Menu menu;

    /**
     *
     * @param scan the scanner used for user input.
     * @param menu the main menu for starting the game.
     */
    public Game(Scanner scan, Menu menu, Player player) {
        this.scan = scan;
        this.player = player;

        List<Question> oopQ = FileReader.loadQuestions("oop_questions.txt");
        List<Question> devQ = FileReader.loadQuestions("dev_tools_questions.txt");

        this.hampus = new Teacher("Hampus", Subject.OOP, oopQ);
        this.nahid = new Teacher("Nahid", Subject.DEVTOOLS, devQ);
    }

    /**
     * Starts the full game loop of four days + exam day.
     */
    public void startGame() {
        //Player setName?
        for (int day = 1; day <= 4; day++) {
            playDay(day);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");
        startExamDay();
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Returns one of the two teachers at random.
     *
     * @return a randomly selected Teacher.
     */
    private Teacher getRandomTeacher() {
        if (randomTeacher.nextBoolean()) {
            return hampus;
        } else {
            return nahid;
        }
    }

    /**
     * Starts lesson and decides how many questions the player recieves. (3 if on time and 1 if player is late)
     *
     * @param late decides wether the player is late for the lesson.
     */
    private void startLesson(boolean late) {
        Teacher teacher = getRandomTeacher();
        System.out.println("####### Dagens lektion är " + teacher.getSubject().toString() + " med " + teacher.getName().toString() + " #######");

        teacher.shuffleQuestions();

        List<Question> questions = teacher.getRandomQuestions(3);

        if (late) {
            questions = teacher.getRandomQuestions(1);
        }

        for (Question q : questions) askQuestion(q);
    }

    /**
     * Ask one single question during a lesson. Checks if player answer correctly or not and
     * updates player score accordingly
     *
     * @param q the question asked
     */
    private void askQuestion(Question q) {
        System.out.printf("%n" + q.question() + "%n");
        String answer = scan.nextLine();

        if (answer.equalsIgnoreCase(q.answer())) {
            System.out.println("RÄTT! " + q.flavorText());
            player.gainKnowledge(q.points());
        } else {
            System.out.println("Fel! Rätt svar var: " + q.answer());
            player.loseHealth(5);
        }
    }


    /**
     * Plays one full day concisting of: morning, lesson, lunch, lesson, evening.
     * Adds some pauses between events for better readability.
     *
     * @param day the number of the current day.
     */
    public void playDay(int day) {
        System.out.println(" ");
        morning(day);
        System.out.println(" ");
        System.out.println("Tryck enter för att fortsätta.");
        String pause = scan.nextLine();
        lessonOne();
        System.out.println(" ");
        System.out.println("Tryck enter för att fortsätta.");
        pause = scan.nextLine();
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
        lunch();
        System.out.println(" ");
        System.out.println("Tryck enter för att fortsätta.");
        pause = scan.nextLine();
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
        evening();
        System.out.println("Tryck enter för att fortsätta.");
        pause = scan.nextLine();
    }


    /**
     * Handles all morning events, showing player points and wether they have overslept.
     *
     * @param day the number of the current day.
     */
    public void morning(int day) {

        System.out.println(" ***************** Dag " + day + " **************************************************************************");
        System.out.println(" ");
        System.out.println(player.statsToString());
        System.out.println(" ");
        System.out.println("God morgon! Du är hemma och har just vaknat.");


        if (!late) {
            System.out.println("Klockan är 5. Du mår skit, men fan vad du får pluggat! Och sen blir du heller inte, din jävla King!");
            System.out.println(" ");
            player.loseHealth(10);
            player.gainKnowledge(5);
        } else {
            System.out.println("Du vaknar utvilad.");
            System.out.println(" ");
            player.gainHealth(5);
            //random att försova sig if försov sig, late = true;
        }

    }


    /**
     * Starts the first lesson of the day. Handles consequences if late.
     */
    public void lessonOne() {


        if (late) {
            System.out.println("Du missade bussen, nu igen! Nu kommer du för sent till lektionen och stapplar in 30 min efter föreläsningen startat. Pinsamt!");
            System.out.println(" ");
            startLesson(true); //Array med frågor 1-6 st. If late: starta på 2
        } else {
            System.out.println("Du är i god tid till lektionen.");
            System.out.println(" ");
            startLesson(false); //Array med frågor 1-6 st. startar på plats 0 i arrayen
        }
    }

    /**
     * Handles lunch choices and how player stats is affected by each choice.
     * If player goes home, second lesson is skipped.
     */
    public void lunch() {

        System.out.println(player.statsToString());
        System.out.println(" ");
        System.out.println("Dags för lunchrast! Vad vill du göra?");
        System.out.println(" ");
        System.out.println("1. Skippa lunchen och plugga"); //Få mer info, typ 2 meningar om något pluggigt.
        System.out.println("2. Ät en redig lunch");
        System.out.println("3. Hiva i dig en kaffe snabbt och plugga");
        System.out.println("4. Gå hem för dagen och \"plugga\"");

        String input = scan.nextLine();

        switch (input) {
            case "1" -> {
                player.gainKnowledge(3);
                player.loseHealth(5);
                System.out.println("Du pluggar som en gud! Men magen kurrar.");
            }
            case "2" -> {
                player.gainHealth(5);
                System.out.println("Du äter en riktigt god lunch. Mums!");
                System.out.println(" ");
            }
            case "3" -> {
                player.gainKnowledge(5);
                player.loseHealth(5);
                System.out.println("FAN vad skarp du känner dig!");
                System.out.println(" ");
            }
            case "4" -> {
                player.gainHealth(5);
                player.loseKnowledge(5);
                System.out.println("Åh vad skönt att bara lägga sig på soffan!");
                System.out.println(" ");
            }
        }

        if (!input.equals("4")) {

            System.out.println("Du går på nästa lektion");
            System.out.println(" ");
            lessonTwo();

        }
    }

    /**
     * Starts the second lesson of the day. Player can't be late for this lesson.
     */
    public void lessonTwo() {
        startLesson(false);

    }

    /**
     * Handles the players choices at evening, determining chances of being late the next morning.
     */
    public void evening() {
        System.out.println(player.statsToString());
        System.out.println(" ");
        System.out.println("Du är hemma efter en hård dag i skolan. Vad vill du göra nu?");
        System.out.println(" ");

        System.out.println("1. Gör inget särskilt, går och lägger mig tidigt och Sover ut.");
        System.out.println("2. Stanna uppe sent och plugga hela kvällen");
        System.out.println("3. Gå ut och festa");
        System.out.println("4. Softa och kolla på serier hela kvällen, ställer klockan tidigt för att inte komma för sent nästa dag");

        String input = scan.nextLine();

        Random chanceOfBeingLate = new Random();

        switch (input) {
            case "1" -> {
                System.out.println("Åh vad härligt att vila! Self care here you come!");
                System.out.println(" ");
                player.gainHealth(5);
                late = chanceOfBeingLate.nextInt(100) < 10;
            }
            case "2" -> {
                System.out.println("Herre Gud vad smart du blir!");
                System.out.println(" ");
                player.gainKnowledge(8);
                player.loseHealth(5);
                late = chanceOfBeingLate.nextInt(100) < 25;
            }
            case "3" -> {
                System.out.println("Du är king i baren! Och dina dance moves är on top! Whoop whoop!");
                System.out.println(" ");
                player.loseHealth(10);
                player.loseKnowledge(10);
                late = true;
            }
            case "4" -> {
                System.out.println("Bara ett avsnitt till! Eller kanske två. Okej, tre.");
                System.out.println(" ");
                late = false;
            }
        }
    }

    /**
     * Starts the exam event. If player is late, exam fails without starting.
     */
    public void startExamDay() {
        System.out.println("###### Idag är det tenta! #####");
        System.out.println(" ");
        if (late) {
            System.out.println("Du har försovit dig och missade tentan. Du är körd!");
            System.out.println(" ");
            System.out.println("##### GAME OVER ###################################################################");
            menu.startMenu();
        } else {
            startExam();
        }

    }

    /**
     * Evaluates the player stats of knowlege points and health points to chec the result of the exam.
     * Player may fail, get G, VG or MVG+++.
     */
    public void startExam() {

        if (player.getKnowledge() <= 49) {
            System.out.println("Nae, det här gick inget vidare. Du failade hårt och inser att du borde ha pluggat mer.");
            System.out.println("GAME OVER");
        } else if (player.getKnowledge() <= 60) {
            System.out.println("Hurra! Du klarade provet. Du fick G.");
        } else if (player.getKnowledge() <= 80) {
            System.out.println("Herre gud, vilken stjärna du är! Du inte bara klarade provet, du fick VG! Grattis!");
        } else {
            System.out.println("OUTSTANDING! Du fick MVG+++ trots att betyget inte ens finns och både Nahid och Hampus vill ha din autograf!");
        }

    }

    public boolean getIsLate() {
        return late;


    }

}







