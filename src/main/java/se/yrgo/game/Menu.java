package se.yrgo.game;

import se.yrgo.player.*;
import se.yrgo.teachers.*;
import se.yrgo.game.Menu;
import java.util.Scanner;
import java.io.FilterWriter;

public class Menu {
    private final Scanner scan;

    public Menu(Scanner scan) {
        this.scan = scan;
    }

    public void startGame() {
        Game game = new Game(scan, this);
        game.startGame();
    }

    public void getRules() {
        System.out.print("\033[H\033[2J"); // eller ev testa Ansi.clearScreen();
        printRules();
        System.out.println("Tryck enter för att gå tillbaka till startmenyn");
        scan.nextLine();
        startMenu();
    }

    public void startMenu() {
        while (true) {
            System.out.println(
                    "~~~~~Welcome to $hell! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" ");
            System.out.println("_____Välj ett alternativ:_____");
            System.out.println(" ");
            System.out.println("[1] Starta spelet");
            System.out.println("[2] Läs spelregler");
            System.out.println("[3] Avsluta spelet");
            // System.out.println("[4] High score");
            // System.out.println("5. Inställningar");

            String input = scan.nextLine();

            switch (input) {
                case "1" -> startGame(); // Clear screen Ansi.clearScreen();
                case "2" -> getRules();
                case "3" -> System.exit(0);
                // case "4" -> getHighScore();
                // case "5" -> Menu.settings();
                default -> System.out.println("Skriv in ett val från 1-3");
            }
        }
    }

    public static void printRules() {
        System.out.printf("""
                GAME RULES — JavaQuest: To $Hell and Back

                Overview:
                 You are a student attending a demanding programming academy.
                 Each day you go to class, answer questions from your teachers,
                 earn XP, and try to survive the week without burning out.
                 Your goal is to pass the final exam at the end of the week.
                 To succeed, you must manage both your HP (Health Points)
                 and your XP (Knowledge Experience).

                HP (Health Points):
                 HP represents your physical and mental energy during the school week.
                 You lose HP when you stay up too late studying, arrive late to class,
                 or make risky choices. If your HP reaches zero, you collapse from
                 exhaustion and cannot take the final exam.

                XP (Experience Points):
                 XP represents your programming knowledge. You gain XP by answering
                 questions correctly, performing well in lessons, and staying engaged.
                 You need enough XP by the end of the week to unlock the final exam.

                Lessons:
                 - Each day you attend lessons with one of two teachers:
                  * Hampus teaches Object-Oriented Programming (OOP).
                  * Nahid teaches Development Tools (DevTools).
                 - Each teacher has their own pool of questions.

                A lesson contains:
                 - A random classroom event, such as arriving late.
                 - About three random questions from the teacher's subject.
                 - XP rewards for correct answers.
                 - Possible HP penalties for mistakes or unlucky events.

                Questions:
                 - Each question consists of:
                 - A question text
                 - Three answer choices (1, 2, or 3)
                 - The correct answer
                 - A point value
                 - A flavor text message shown after a correct answer

                Questions are selected randomly, and the same question will not appear
                twice in the same lesson.

                The Weekly Schedule:
                 Your school week contains four DevTools lessons, four OOP lessons,
                 and one final exam at the end of the week. Lessons occur in a random
                 order based on a randomized weekly schedule.

                The Final Exam:
                 To qualify for the exam, your HP must be above zero and your XP must
                 meet the required threshold. The exam uses questions from both subjects,
                 is more difficult, and offers higher XP rewards.

                Failure Conditions:
                 You fail the week if your HP reaches zero or if you do not meet the
                 XP requirement.

                Winning the Game:
                 You win the game by maintaining your HP, gaining enough XP throughout
                 the week, and successfully completing the final exam. Completing all
                 these tasks means you graduate as a certified Java Adventurer.

                """);

    }
}