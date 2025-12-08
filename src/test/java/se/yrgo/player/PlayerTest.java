package se.yrgo.player;

import se.yrgo.game.Menu;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayerName");
        Menu menu = new Menu(new Scanner(System.in)) {
            @Override
            public void startMenu() {

            }
        };
        player.setMenu(menu);
    }

    @Test
    void testConstructor() {
        assertEquals("TestPlayerName", player.getName());
        assertEquals(Player.MAX_HEALTH, player.getHealth());
        assertEquals(Player.MIN_KNOWLEDGE, player.getKnowledge());
    }

    @Test
    void testSetName() {
        player.setName("Name");
        assertEquals("Name", player.getName());
    }

    @Test
    void testGainKnowledge() {
        player.gainKnowledge(10);
        assertEquals(10, player.getKnowledge());
    }

    @Test
    void testMaxKnowledge() {
        player.gainKnowledge(150);
        assertEquals(Player.MAX_KNOWLEDGE, player.getKnowledge());
    }

    @Test
    void testLoseKnowledge() {
        player.gainKnowledge(Player.MAX_KNOWLEDGE);
        player.loseKnowledge(20);
        assertEquals(80, player.getKnowledge());
    }

    @Test
    void testMinKnowledge() {
        player.loseKnowledge(50);
        assertEquals(Player.MIN_KNOWLEDGE, player.getKnowledge());
    }

    @Test
    void testGainHealth() {
        player.loseHealth(20);
        player.gainHealth(10);
        assertEquals(90, player.getHealth());
    }

    @Test
    void testMaxHealth() {
        player.gainHealth(1000);
        assertEquals(Player.MAX_HEALTH, player.getHealth());
    }

    @Test
    void testMinHealth() {
        player.loseHealth(1000);
        assertEquals(Player.MIN_HEALTH, player.getHealth());
    }

    @Test
    void testGameOverAtLoseHealth() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));

        player.loseHealth(1000);

        String print = os.toString();

        assertTrue(print.contains("GAME OVER"));
        assertTrue(print.contains("Din hälsa har gått i botten"));
    }

    @Test
    void testStatsToString() {
        player.gainKnowledge(80);
        player.loseHealth(20);

        String expected = "Health: 80 Knowledge: 80";
        assertEquals(expected, player.statsToString());
    }
}

