package com.projetcave.people;

import com.projetcave.hero.Hero;
import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String fact;
    private int health;
    private int attackDamage;

    public Person(String name, String fact) {
        this.name = name;
        this.fact = fact;
        this.health = 50; // Initial health
        this.attackDamage = 5; // Initial attack damage
    }

    public String getName() {
        return name;
    }

    public String getFact() {
        return fact;
    }

    public void interact() {
        System.out.println(name + " says: " + fact);
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(getName() + " Health: " + getHealth());
    }

    public void attack(Hero hero) {
        hero.takeDamage(attackDamage);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void transform() {
        fact = "I am now a demon!";
    }

    public int getAttackDamage() {
        return attackDamage;
    }
}