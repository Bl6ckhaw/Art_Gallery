package com.projetcave.hero;

import com.projetcave.items.Item;
import com.projetcave.people.Person;
import java.io.Serializable;

public class Hero implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Backpack backpack;
    private int currentLocationIndex;
    private int lifePoints;
    private boolean vaseBroken;
    private boolean distracted;
    private boolean hasSculpture;
    private int attackDamage;
    private int distractionCounter;

    public Hero(String name, double backpackVolume, double backpackWeight) {
        this.name = name;
        this.backpack = new Backpack(backpackVolume, backpackWeight);
        this.currentLocationIndex = 0;
        this.lifePoints = 100;
        this.vaseBroken = false;
        this.distracted = false;
        this.hasSculpture = false;
        this.attackDamage = 10;
        this.distractionCounter = 0;
    }

    public String getName() {
        return name;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public int getCurrentLocationIndex() {
        return currentLocationIndex;
    }

    public void setCurrentLocationIndex(int currentLocationIndex) {
        this.currentLocationIndex = currentLocationIndex;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public boolean isVaseBroken() {
        return vaseBroken;
    }

    public void setVaseBroken(boolean vaseBroken) {
        this.vaseBroken = vaseBroken;
    }

    public boolean isDistracted() {
        return distracted;
    }

    public void setDistracted(boolean distracted) {
        this.distracted = distracted;
        this.distractionCounter = distracted ? 2 : 0; // Set counter to 2 if distracted, otherwise 0
    }

    public boolean hasSculpture() {
        return hasSculpture;
    }

    public void setHasSculpture(boolean hasSculpture) {
        this.hasSculpture = hasSculpture;
    }

    public void displayInventory() {
        backpack.displayItems();
    }

    public boolean useItemOnItem(Item item1, Item item2) {
        return false;
    }

    public void takeDamage(int damage) {
        if (hasSculpture) {
            System.out.println("The sculpture absorbs the damage.");
            hasSculpture = false;
        } else {
            lifePoints -= damage;
            if (lifePoints < 0) {
                lifePoints = 0;
            }
            System.out.println(getName() + " Health: " + lifePoints);
        }
    }

    public int attack() {
        return attackDamage;
    }

    public void attack(Person person) {
        person.takeDamage(attackDamage);
        if (distracted) {
            distractionCounter--;
            if (distractionCounter <= 0) {
                distracted = false;
                System.out.println("The demon is no longer distracted.");
            }
        }
    }

    public boolean isAlive() {
        return lifePoints > 0;
    }
}