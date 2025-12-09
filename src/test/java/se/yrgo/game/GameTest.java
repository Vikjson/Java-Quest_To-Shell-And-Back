package se.yrgo.game;

import org.junit.jupiter.api.Test;
import se.yrgo.game.Game;
import se.yrgo.game.Menu;
import se.yrgo.player.Player;
import se.yrgo.teachers.FileReader;
import se.yrgo.teachers.Question;
import se.yrgo.teachers.Subject;
import se.yrgo.teachers.Teacher;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {


    private String captureOutput(Runnable action) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;

        System.setOut(new PrintStream(out));
        action.run();
        System.setOut(original);

        return out.toString();
    }

    @Test
    void examShouldFailIfKnowledgePointsLow() {
        Scanner mockScan = mock(Scanner.class);
        Menu mockMenu = mock(Menu.class);
        Player player = new Player("Player");

        player.gainKnowledge(30);
        Game game = spy(new Game(mockScan, mockMenu, player));

        String out = captureOutput(game::startExam);

        assertTrue(out.contains("failade"), "Exam should fail when knowledge is 49 and below");
    }

    @Test
    void examShoulGetGIfKnowlegeIs50() {
        Scanner mockScan = mock(Scanner.class);
        Menu mockMenu = mock(Menu.class);
        Player player = new Player("Player");

        player.gainKnowledge(50);
        Game game = spy(new Game(mockScan, mockMenu, player));

        String out = captureOutput(game::startExam);

        assertTrue(out.contains("Hurra! Du klarade provet. Du fick G."), "Player should get G if knowlege is 50");
    }

    @Test
    void examShoulGetVGIfKnowlegeIs61() {
        Scanner mockScan = mock(Scanner.class);
        Menu mockMenu = mock(Menu.class);
        Player player = new Player("Player");

        player.gainKnowledge(61);
        Game game = spy(new Game(mockScan, mockMenu, player));

        String out = captureOutput(game::startExam);

        assertTrue(out.contains("du fick VG! Grattis!"), "Player should get VG if knowlege is 61");
    }

    @Test
    void examShoulGetMVGIfKnowlegeIs81() {
        Scanner mockScan = mock(Scanner.class);
        Menu mockMenu = mock(Menu.class);
        Player player = new Player("Player");

        player.gainKnowledge(81);
        Game game = spy(new Game(mockScan, mockMenu, player));

        String out = captureOutput(game::startExam);

        assertTrue(out.contains("Du fick MVG+++"), "Player should get MVG if knowlege is 81");
    }


    @Test
    void startGameShouldPrintDayText() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("Player");
        boolean testGame = true;


        when(scan.nextLine()).thenReturn(" ");

        Game game = spy(new Game(scan, menu, player, testGame));

        String out = captureOutput(game::startGame);

        assertTrue(out.contains(" ***************** Dag 1"), "Day 1 text should appear at game start");
    }

    @Test
    void morningShouldPrintDayAndMorningText() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("Player");

        Game game = spy(new Game(scan, menu, player));

        String out = captureOutput(() -> game.morning(2));

        assertTrue(out.contains(" ***************** Dag 2"), "Morning text should contain current day");
        assertTrue(out.contains("morgon"), "Morning text should contain morgon");
    }

    @Test
    void evening1ShouldIncreaseHealth() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("Player");
        player.loseHealth(95);
        Game game = spy(new Game(scan, menu, player));

        when(scan.nextLine()).thenReturn("1");

        String out = captureOutput(() -> game.evening());

        assertEquals(10, player.getHealth(), " Choice 1 should result in +5 health");
    }

    @Test
    void evening2ShouldGainKnowledgeAndLoseHealth() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("player");
        Game game = spy(new Game(scan, menu, player));

        when(scan.nextLine()).thenReturn("2");

        String out = captureOutput(() -> game.evening());


        assertEquals(95, player.getHealth(), "option 2 should reduce healthPionts by 5");
        assertEquals(8, player.getKnowledge(), "option 2 should increase +8 knowledgePoints");

        assertTrue(out.contains("Herre Gud vad smart du blir!"),
                "Should print \"Herre Gud vad smart du blir!\"");
    }

    @Test
    void eveningShouldMakePlayerLateWhenChoosingPartyOption3() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("Player");

        player.gainKnowledge(20);
        int beforeHealth = player.getHealth();
        int beforeKnowledge = player.getKnowledge();

        Game game = spy(new Game(scan, menu, player));

        when(scan.nextLine()).thenReturn("3");

        String out = captureOutput(() -> game.evening());

        assertEquals(beforeHealth - 10, player.getHealth(), "Health should decrease by 10 when choosing 3 (party)");
        assertEquals(beforeKnowledge - 10, player.getKnowledge(), "Knowledge should decrease by 10 when choosing 3 (party)");
        assertTrue(out.contains("king i baren"), "Evening should show party message when choosing 3 (party)");
    }

    @Test
    void evening4ShouldChangeNoStatsAndLateIsFalse() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("player");
        Game game = spy(new Game(scan, menu, player));

        when(scan.nextLine()).thenReturn("4");

        String out = captureOutput(() -> game.evening());

        boolean isLate = game.getIsLate();

        assertEquals(100, player.getHealth(), "Health should be unchanged");
        assertEquals(0, player.getKnowledge(), "Knowledge should be unchanged");
        assertFalse(isLate, "Late should be false");

    }

    @Test
    void constructorShouldCreateNewGameObject() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("player");

        Game game = new Game(scan, menu, player);

        assertNotNull(game);
    }

    @Test
    void lunch1ShouldChangeHealth() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("player");
        Game game = spy(new Game(scan, menu, player));
        doNothing().when(game).lessonTwo();

        when(scan.nextLine()).thenReturn("1");

        game.lunch();

        assertEquals(95, player.getHealth(), "Lunch choice 1 should give -5 health");
        assertEquals(3, player.getKnowledge(), "Lunch choice 1 should give 3 Knowledge");
    }



    @Test
    void lunch2ShouldChangeKnowledge() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("player");
        player.loseHealth(95);
        Game game = spy(new Game(scan, menu, player));
        doNothing().when(game).lessonTwo();

        when(scan.nextLine()).thenReturn("2");

        game.lunch();

        assertEquals(10, player.getHealth(), "Lunch option 2 should give +5 healthPoints");
    }

    @Test
    void lunch3ShouldChangeHealthAndKnowledge() {
        Scanner scan = mock(Scanner.class);
        Menu menu = mock(Menu.class);
        Player player = new Player("Pllayer");
        Game game = spy(new Game(scan, menu, player));
        doNothing().when(game).lessonTwo();

        when(scan.nextLine()).thenReturn("3");

        game.lunch();

        assertEquals(95, player.getHealth(), "Lunch option 3 should decrease 5 healthPionts");
        assertEquals(5, player.getKnowledge(), "Lunch option 3 should increase 5 knowledgPoints");
    }

}



