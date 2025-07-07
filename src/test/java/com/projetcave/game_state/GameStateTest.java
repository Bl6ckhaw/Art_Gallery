package com.projetcave.game_state;

import com.projetcave.hero.Hero;
import com.projetcave.locations.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {
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
    public void testGetAndSetHero() {
        Hero newHero = new Hero("New Adventurer", 60.0, 6.0);
        state.setHero(newHero);
        assertEquals(newHero, state.getHero());
    }

    @Test
    public void testGetAndSetCurrentLocation() {
        Location newLocation = new Location("New Room", "A new room for testing.");
        state.setCurrentLocation(newLocation);
        assertEquals(newLocation, state.getCurrentLocation());
    }

    @Test
    public void testGetAndSetLocationCount() {
        state.setLocationCount(5);
        assertEquals(5, state.getLocationCount());
    }

    @Test
    public void testGetLocations() {
        assertNotNull(state.getLocations());
    }

    @Test
    public void testSaveAndLoadGame() {
        state.saveGame();
        state.loadGame();
        // Check if the game state is correctly loaded
        assertEquals(hero.getName(), state.getHero().getName());
        assertEquals(location.getName(), state.getCurrentLocation().getName());
    }
}