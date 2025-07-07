package com.projetcave.exits;

import com.projetcave.locations.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExitTest {
    private Exit exit;
    private Location destination;
    private Location linkedLocation;

    @BeforeEach
    public void setUp() {
        destination = new Location("Destination Room", "A destination room.");
        exit = new Exit("north", destination, true, linkedLocation);
    }

    @Test
    public void testGetDirection() {
        assertEquals("north", exit.getDirection());
    }

    @Test
    public void testGetDestination() {
        assertEquals(destination, exit.getDestination());
    }

    @Test
    public void testIsLocked() {
        assertTrue(exit.isLocked());
    }

    @Test
    public void testUnlock() {
        exit.unlock();
        assertFalse(exit.isLocked());
    }
}