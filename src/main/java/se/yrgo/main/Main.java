package se.yrgo.main;

import se.yrgo.game.*;
import se.yrgo.player.*;
import se.yrgo.teachers.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Player player = new Player("Player");

        try (Scanner scan = new Scanner(System.in)) {
            Menu menu = new Menu(scan);
            menu.startMenu();


        }



    }
}