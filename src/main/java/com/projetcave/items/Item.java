package com.projetcave.items;

import com.projetcave.hero.Hero;
import com.projetcave.exits.Exit;
import com.projetcave.locations.Location;
import com.projetcave.people.Person;
import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private boolean canBeTaken;
    private double volume;
    private double weight;

    public Item(String name, String description, boolean canBeTaken, double volume, double weight) {
        this.name = name;
        this.description = description;
        this.canBeTaken = canBeTaken;
        this.volume = volume;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean canBeTaken() {
        return canBeTaken;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public void use(Hero hero, Location currentLocation, String target) {
        switch (name.toLowerCase()) {
            case "sword":
                System.out.println("You swing the sword to attack.");
                for (Person person : currentLocation.getPeople()) {
                    if (person.getName().equalsIgnoreCase(target)) {
                        person.takeDamage(hero.attack());
                        System.out.println("You attacked " + person.getName() + " with the sword.");
                        return;
                    }
                }
                System.out.println("There is no one named " + target + " here to attack.");
                break;
            case "key":
                System.out.println("You use the key to unlock a exit.");
                for (Exit exit : currentLocation.getExits()) {
                    if (exit.getDirection().equalsIgnoreCase(target)) {
                        if (exit.isLocked()) {
                            exit.unlock();
                            System.out.println("You unlocked the exit to the " + target + ".");
                            currentLocation.unlockExit(exit.getDestination());

                        } else {
                            System.out.println("The exit to the " + target + " is already unlocked.");
                        }
                        return;
                    }
                }
                System.out.println("There is no exit in the direction " + target + ".");
                break;
            case "ankh":
                hero.setLifePoints(hero.getLifePoints() + 20);
                System.out.println("You used the ankh and healed 20 life points.");
                break;
            case "vase":
                hero.setVaseBroken(true);
                System.out.println("You broke the vase. Demons appear! Purge them!");
                break;
            case "painting":
                hero.setDistracted(true);
                System.out.println("You use the painting to distract enemies. You can attack twice without retaliation.");
                break;
            case "sculpture":
                hero.setHasSculpture(true);
                System.out.println("You use the sculpture to block an attack. It will absorb the next attack.");
                break;
            default:
                System.out.println("This item has no special utility.");
                break;
        }
    }
}