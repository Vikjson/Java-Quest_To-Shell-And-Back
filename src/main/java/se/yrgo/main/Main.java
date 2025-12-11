package se.yrgo.main;

import java.util.Scanner;
import se.yrgo.game.*;
import se.yrgo.player.*;
import se.yrgo.teachers.*;



public class Main {
    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {
            Menu menu = new Menu(scan);
            menu.startMenu();


        }



    }
}