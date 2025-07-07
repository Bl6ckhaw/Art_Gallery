package com.projetcave.locations;

import com.projetcave.exits.Exit;
import com.projetcave.hero.Hero;
import com.projetcave.items.Item;
import com.projetcave.people.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location location;
    private Hero hero;

    @BeforeEach
    public void setUp() {
        location = new Location("Test Room", "A room for testing.");
        hero = new Hero("Adventurer", 50.0, 5.0);
    }

    @Test
    public void testGetName() {
        assertEquals("Test Room", location.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A room for testing.", location.getDescription());
    }

    @Test
    public void testAddAndGetItems() {
        Item item = new Item("Sword", "A sharp medieval sword.", true, 5.0, 3.0);
        location.addItem(item);
        assertTrue(location.getItems().contains(item));
    }

    @Test
    public void testAddAndGetHeroes() {
        location.addHero(hero);
        assertTrue(location.getHeroes().contains(hero));
    }

    @Test
    public void testAddAndGetExits() {
        Location destination = new Location("Destination Room", "A destination room.");
        Exit exit = new Exit("north", destination, false, null);
        location.addExit(exit);
        assertTrue(location.getExits().contains(exit));
    }

    @Test
    public void testAddAndGetPeople() {
        Person person = new Person("Test Person", "This is a test person.");
        location.addPerson(person);
        assertTrue(location.getPeople().contains(person));
    }

    @Test
    public void testDisplayLocation() {
        location.displayLocation(hero);
        // Check console output manually
    }
}