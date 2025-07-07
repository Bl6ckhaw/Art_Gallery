package com.projetcave.items;

import com.projetcave.hero.Hero;
import com.projetcave.locations.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;
    private Hero hero;
    private Location location;

    @BeforeEach
    public void setUp() {
        item = new Item("Sword", "A sharp medieval sword.", true, 5.0, 3.0);
        hero = new Hero("Adventurer", 50.0, 5.0);
        location = new Location("Test Room", "A room for testing.");
    }

    @Test
    public void testGetName() {
        assertEquals("Sword", item.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A sharp medieval sword.", item.getDescription());
    }

    @Test
    public void testCanBeTaken() {
        assertTrue(item.canBeTaken());
    }

    @Test
    public void testGetVolume() {
        assertEquals(5.0, item.getVolume());
    }

    @Test
    public void testGetWeight() {
        assertEquals(3.0, item.getWeight());
    }

    @Test
    public void testUseSword() {
        item.use(hero, location, "Test Person");
        // Check console output manually
    }

    @Test
    public void testUseKey() {
        Item key = new Item("Key", "A small golden key.", true, 0.1, 0.1);
        key.use(hero, location, "north");
        // Check console output manually
    }
}