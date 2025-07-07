package com.projetcave.commands;

import com.projetcave.game_state.GameState;
import com.projetcave.hero.Hero;
import com.projetcave.items.Item;
import com.projetcave.locations.Location;
import com.projetcave.people.Person;
import com.projetcave.exits.Exit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {
    private GameState state;
    private Hero hero;
    private Location location;

    @BeforeEach
    public void setUp() {
        state = new GameState();
        hero = new Hero("Adventurer", 50.0, 5.0);
        location = new Location("Test Room", "A room for testing.");
        state.setHero(hero);
        state.setCurrentLocation(location);
    }

    @Test
    public void testExecuteCommandHelp() {
        Commands.executeCommand("HELP", state);
        // Check console output manually
    }

    @Test
    public void testExecuteCommandLook() {
        Commands.executeCommand("LOOK", state);
        // Check console output manually
    }

    @Test
    public void testExecuteCommandInventory() {
        Commands.executeCommand("INVENTORY", state);
        // Check console output manually
    }

    @Test
    public void testExecuteCommandGo() {
        Location destination = new Location("Destination Room", "A destination room.");
        location.addExit(new Exit("north", destination, false, null));
        Commands.executeCommand("GO north", state);
        assertEquals(destination, state.getCurrentLocation());
    }

    @Test
    public void testExecuteCommandTake() {
        Item item = new Item("Sword", "A sharp medieval sword.", true, 5.0, 3.0);
        location.addItem(item);
        Commands.executeCommand("TAKE Sword", state);
        assertTrue(hero.getBackpack().getItems().contains(item));
    }

    @Test
    public void testExecuteCommandInteract() {
        Person person = new Person("Test Person", "This is a test person.");
        location.addPerson(person);
        Commands.executeCommand("INTERACT Test Person", state);
        // Check console output manually
    }

    @Test
    public void testExecuteCommandAttack() {
        Person person = new Person("Person", "This is a test person.");
        location.addPerson(person);
        hero.setVaseBroken(true);
        int initialHealth = person.getHealth();
        Commands.executeCommand("ATTACK Person", state);
        assertTrue(person.getHealth() < initialHealth);
    }

    @Test
    public void testExecuteCommandUse() {
        Item item = new Item("Ankh", "An ancient Egyptian ankh.", true, 1.0, 0.5);
        hero.getBackpack().addItem(item);
        Commands.executeCommand("USE Ankh self", state);
        assertEquals(120, hero.getLifePoints());
    }
}