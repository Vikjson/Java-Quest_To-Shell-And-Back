package se.yrgo.player;

import se.yrgo.game.*;

/**
 * This class represents the player and all its stats, such as health, knowledge level and name.
 * From this class the player can lose and gain both health and knowledge, a name can be set and
 * if the player loses all their health the game will be lost and the player will return to the menu.
 */
public class Player {

    private String name;
    private int health;
    private int knowledge;
    private Menu menu;

    public static final int MAX_HEALTH = 100;
    public static final int MAX_KNOWLEDGE = 100;
    public static final int MIN_HEALTH = 0;
    public static final int MIN_KNOWLEDGE = 0;

    public Player(String name) {
        this.name = name;
        this.health = MAX_HEALTH;
        this.knowledge = MIN_KNOWLEDGE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getKnowledge() {
        return knowledge;
    }

    /**
     *
     * @return a string to present the players stats in health and knowledge
     * to the users interface.
     */
    public String statsToString() {
        return "Health: " + getHealth() + " Knowledge: " + getKnowledge();
    }

    /**
     * This method makes it possible for the player to gain knowledge, the amount of knowledge gained is set
     * by the amount parameter.
     * If the player gains more knowledge than the maximum amount allowed the knowledge will be set to maximum.
     *
     * @param amount of knowledge to be gained.
     */
    public void gainKnowledge(int amount) {
        knowledge += amount;
        if (knowledge > MAX_KNOWLEDGE) {
            knowledge = MAX_KNOWLEDGE;
        }
    }

    /**
     * This method makes it possible for the player to lose knowledge, the amount of knowledge lost is set
     * by the amount parameter.
     * If the player loses more knowledge than the minimum amount allowed the knowledge will be set to minimum.
     *
     * @param amount of knowledge to be lost.
     */
    public void loseKnowledge(int amount) {
        knowledge -= amount;
        if (knowledge < MIN_KNOWLEDGE) {
            knowledge = MIN_KNOWLEDGE;
        }
    }

    /**
     * This method makes it possible for the player to gain health, the amount of health gained is set
     * by the amount parameter.
     * If the player gains more health than the maximum amount allowed the health will be set to maximum.
     *
     * @param amount of health to be gained.
     */
    public void gainHealth(int amount) {
        health += amount;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    /**
     * This method makes it possible for the player to lose health, the amount of health lost is set
     * by the amount parameter.
     * If the player loses more health than the minimum amount allowed the health will be set to minimum.
     *
     * @param amount of health to be lost.
     */
    public void loseHealth(int amount) {
        health -= amount;
        if (health <= MIN_HEALTH) {
            health = MIN_HEALTH;
            System.out.println("Din hälsa har gått i botten, du klarar inte av att fortsätta dina studier.");
            System.out.println("GAME OVER");
        }
    }

}