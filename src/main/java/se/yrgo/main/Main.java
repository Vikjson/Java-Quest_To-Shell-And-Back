import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to $hell!");

        System.out.println("1. Starta spelet");
        System.out.println("2. Läs spelregler");
        System.out.println("3. High score");
        System.out.println("4. Avsluta spelet");
        // System.out.println("5. Inställningar");

        try (Scanner scan = new Scanner(System.in)) {
            String input = null;

            //Menu.startMenu();
            switch (input) {
                case "1" -> Menu.startGame(); //Clear screen Ansi.clearScreen();
                case "2" -> Menu.getRules();
                case "3" -> Menu.getHighScore();
                case "4" -> System.exit(0);
              //  case "5" -> Menu.settings();
            default: -> System.out.println("Skriv in ett val från 1-5");
                   
            }

             Boolean late = false;

            for (int i = 1; i <= 4; i++) {

            System.out.println("God morgon! Du är hemma och har just vaknat.");
            System.out.println("Din hälsa är: " + Player.getHealth() + " och din kunskapsnivå är: " + Player.getKnowlege());

           
           

            if (Player.wakeUpEarly()){
               System.out.println("Klockan är 5. Du mår skit, men fan vad du får pluggat! Och sen blir du heller inte, din jävla King!");

               Player.decreaseHealth();
               Player.increaseKnowlege();
            } else{
                System.out.println("Du vaknar utvilad.");
                Player.increaseHealth();
                //random att försova sig if försov sig, late = true;
            }

            
            if (late){
                System.out.println("Du missade bussen, nu igen! Nu kommer du för sent till lektionen och stapplar in 30 min efter föreläsningen startat. Pinsamt!");
                //Random med Nahid eller Hampus
                Teacher.startLesson(2); //Array med frågor 1-6 st. If late: starta på 2
            } else{
                System.out.println("Du är i god tid till lektionen.");
                //Random med Nahid eller Hampus
                Teacher.startLesson(0); //Array med frågor 1-6 st. startar på plats 0 i arrayen
            }
            System.out.println("Din hälsa är: " + Player.getHealth() + " och din kunskapsnivå är: " + Player.getKnowlege());
            System.out.println("Dags för lunchrast! Vad vill du göra?");
            System.out.println("1. Skippa lunchen och plugga"); //Få mer info, typ 2 meningar om något pluggigt.
            System.out.println("2. Ät en redig lunch");
            System.out.println("3. Hiva i dig en kaffe snabbt och plugga");
            System.out.println("4. Gå hem för dagen och \"plugga\"");

            input = scan.nextLine();

            Switch (input){
                case "1" -> Player.increaseKnowlege(); Player.decreaseHealth(); System.out.println("Du pluggar som en gud! Men magen kurrar.");
                case "2" -> Player.increaseHealth(); System.out.println("Du äter en riktigt god lunch. Mums!");
                case "3" -> Player.increaseKnowlege(); Player.decreaseHealth(); System.out.println("FAN vad skarp du känner dig!");
                case "4" -> Player.increaseHealth(); System.out.println("Åh vad skönt att bara lägga sig på soffan!");
            }

            if (input.notEquals ("4")){

                System.out.println("Du går på nästa lektion");
                Teacher.startLesson(0); //Array med frågor 1-6 st. startar på plats 0 i arrayen
                
            }

            System.out.println("Du är hemma efter en hård dag i skolan. Vad vill du göra nu?");
            System.out.println("Dun hälsa är: " + Player.getHealth() + " och din kunskapsnivå är: " + Player.getKnowlege());

            System.out.println("1. Gör inget särskilt, går och lägger mig tidigt och Sover ut.");
            System.out.println("2. Stanna uppe sent och plugga hela kvällen");
            System.out.println("3. Gå ut och festa");
            System.out.println("4. Softa och kolla på serier hela kvällen, ställer klockan tidigt för att inte komma för sent nästa dag");

            input = scan.nextLine();

            switch (input){
                case "1" -> Player.increaseHealth();
                case "2" -> Player.increaseKnowlege();
                case "3" -> Player.decreaseHealth(); Player.decreaseKnowlege();
                case "4" -> late = false;
            }
            
        }


        System.out.println("Idag är det tenta!");
        if (late){
            System.out.println("Du har försovit dig och missade tentan. Du är körd!");
            System.out.println("GAME OVER");
            Menu.startMenu();
        } else{
            Teacher.exam(); //Här ska avgöras om man vinner eller inte + gå till Menu.start();
        }




        }

    }
}