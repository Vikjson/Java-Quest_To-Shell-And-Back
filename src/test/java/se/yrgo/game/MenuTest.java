package se.yrgo.game;

import org.junit.jupiter.api.Test;
import se.yrgo.game.Menu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class MenuTest {

    class TestMenu extends Menu {
        boolean startGameWasCalled = false;

        public TestMenu(Scanner scan) {
            super(scan, true);
        }

        @Override
        public void startGame() {
            startGameWasCalled = true;
        }
    }

    @Test
    void menuShouldStartGameOn1() {
        Scanner mockScan = mock(Scanner.class);

        when(mockScan.nextLine()).thenReturn("1", "3");

        TestMenu menu = new TestMenu(mockScan);

        menu.startMenu();

        assertTrue(menu.startGameWasCalled);
    }


    @Test
    void menuShouldShowRulesOn2() {
        Scanner mockScan = mock(Scanner.class);
        Menu menu = spy(new Menu(mockScan, true));

        when(mockScan.nextLine()).thenReturn("2", " ", "3");

        RuntimeException thrown = assertThrows(RuntimeException.class, menu::startMenu);
        assertEquals("EXIT MENU", thrown.getMessage());

        verify(menu, times(1)).getRules();
    }


    @Test
    void menuShouldExitOn3() {
        Scanner mockScan = mock(Scanner.class);
        Menu menu = new Menu(mockScan, true);

        when(mockScan.nextLine()).thenReturn("3");

        RuntimeException thrown = assertThrows(RuntimeException.class, menu::startMenu);
        assertEquals("EXIT MENU", thrown.getMessage());
    }

    @Test
    void printRulesShouldPrintRules() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOutputStream = System.out;
        System.setOut(new PrintStream(outputStream));

        Menu.printRules();
        System.setOut(originalOutputStream);
        String output = outputStream.toString();

        assertTrue(output.contains("GAME RULES"), "printRules()should at least print GAME RULES");

    }

}