package se.yrgo.game;

import se.yrgo.player.*;
import se.yrgo.teachers.*;
import se.yrgo.game.Menu;
import java.util.Scanner;

public class Menu{
    private final Scanner scan;


    public Menu(Scanner scan){
        this.scan = scan;
    }


    public void startGame() {
        Game game = new Game(scan, this);
        game.startGame();
    }

    public void getRules() {
        System.out.print("\033[H\033[2J"); // eller ev testa Ansi.clearScreen();
        System.out.println("Regler");
        System.out.println("Tryck enter för att gå tillbaka till startmenyn");
        scan.nextLine();
        startMenu();
    }


    public void startMenu(){
        while (true) {
            System.out.println("~~~~~Welcome to $hell! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" ");
            System.out.println("_____Välj ett alternativ:_____");
            System.out.println(" ");
            System.out.println("[1] Starta spelet");
            System.out.println("[2] Läs spelregler");
            System.out.println("[3] Avsluta spelet");
            //   System.out.println("[4] High score");
            // System.out.println("5. Inställningar");

            String input = scan.nextLine();

            switch (input) {
                case "1" -> startGame(); //Clear screen Ansi.clearScreen();
                case "2" -> getRules();
                case "3" -> System.exit(0);
                //     case "4" -> getHighScore();
                //  case "5" -> Menu.settings();
                default -> System.out.println("Skriv in ett val från 1-3");
            }
        }
    }
}