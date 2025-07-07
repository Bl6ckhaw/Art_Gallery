package com.projetcave.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    private Hero hero;

    @BeforeEach
    public void setUp() {
        hero = new Hero("Adventurer", 50.0, 5.0);
    }

    @Test
    public void testGetName() {
        assertEquals("Adventurer", hero.getName());
    }

    @Test
    public void testGetBackpack() {
        assertNotNull(hero.getBackpack());
    }

    @Test
    public void testGetAndSetCurrentLocationIndex() {
        hero.setCurrentLocationIndex(2);
        assertEquals(2, hero.getCurrentLocationIndex());
    }

    @Test
    public void testGetAndSetLifePoints() {
        hero.setLifePoints(80);
        assertEquals(80, hero.getLifePoints());
    }
}