package se.yrgo.player;

import se.yrgo.game.Menu;

public class Player {

    private String name;
    private int health;
    private int knowledge;
    private int money;

    public static final int MAX_HEALTH = 100;
    public static final int MAX_KNOWLEDGE = 100;
    public static final int MIN_HEALTH = 0;
    public static final int MIN_KNOWLEDGE = 0;
    public static final int MIN_MONEY = 0;

    public Player(String name) {
        this.name = name;
        this.health = MAX_HEALTH;
        this.knowledge = MIN_KNOWLEDGE;
        this.money = 100;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMoney() {
        return money;
    }

    public String statsToString() {
        return "Health: " + getHealth() + " Knowledge: " + getKnowledge();
    }

    public void gainKnowledge(int amount) {
        knowledge += amount;
        if (knowledge > MAX_KNOWLEDGE) {
            knowledge = MAX_KNOWLEDGE;
        }
    }

    public void loseKnowledge(int amount) {
        knowledge -= amount;
        if (knowledge < MIN_KNOWLEDGE) {
            knowledge = MIN_KNOWLEDGE;
        }
    }

    public void gainHealth(int amount) {
        health += amount;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    public void loseHealth(int amount) {
        health -= amount;
        if (health < MIN_HEALTH) {
            health = MIN_HEALTH;
        }
    }

    public void loseMoney(int amount) {
        money -= amount;
        if (money < MIN_MONEY) {
            money = MIN_MONEY;
        }
    }

}