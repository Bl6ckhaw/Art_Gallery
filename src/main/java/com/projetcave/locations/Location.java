package com.projetcave.locations;

import com.projetcave.exits.Exit;
import com.projetcave.items.Item;
import com.projetcave.hero.Hero;
import com.projetcave.people.Person;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private List<Exit> exits;
    private List<Item> items;
    private List<Hero> heroes;
    private List<Person> people;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new ArrayList<>();
        this.items = new ArrayList<>();
        this.heroes = new ArrayList<>();
        this.people = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Exit> getExits() {
        return exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void displayLocation(Hero hero) {
        System.out.println("You are at: " + name);
        System.out.println(description);
        if (!exits.isEmpty()) {
            System.out.println("Exits:");
            for (Exit exit : exits) {
                String status = exit.isLocked() ? "locked" : "unlocked";
                System.out.println("  " + exit.getDirection() + " (" + status + ")");
            }
        } else {
            System.out.println("There are no exits.");
        }
        if (!items.isEmpty()) {
            System.out.println("Items:");
            for (Item item : items) {
                System.out.println("  " + item.getName());
            }
        } else {
            System.out.println("There are no items.");
        }
        if (!people.isEmpty()) {
            if (hero.isVaseBroken()) {
                System.out.println("Demons:");
                for (Person person : people) {
                    System.out.println("  " + person.getName());
                }
            } else {
                System.out.println("People:");
                for (Person person : people) {
                    System.out.println("  " + person.getName());
                }
            }
        } else {
            System.out.println("There are no people.");
        }
    }

    public void addLinkedExits(Location otherLocation, String directionToOther, String directionToThis, boolean isLocked) {
        Exit exitToOther = new Exit(directionToOther, otherLocation, isLocked, this);
        Exit exitToThis = new Exit(directionToThis, this, isLocked, otherLocation);

        this.addExit(exitToOther);
        otherLocation.addExit(exitToThis);
    }

    public void unlockExit(Location targetLocation) {
        for (Exit exit : exits) {
            if (exit.getDestination() == targetLocation) {
                exit.setLocked(false);
                // Unlock the corresponding exit in the target location
                for (Exit targetExit : targetLocation.getExits()) {
                    if (targetExit.getDestination() == this) {
                        targetExit.setLocked(false);
                        break;
                    }
                }
                break;
            }
        }
    }

}