package com.projetcave.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projetcave.hero.Hero;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Test Person", "This is a test person.");
    }

    @Test
    public void testGetName() {
        assertEquals("Test Person", person.getName());
    }

    @Test
    public void testGetFact() {
        assertEquals("This is a test person.", person.getFact());
    }

    @Test
    public void testInteract() {
        // Capture the output of the interact method
        String expectedOutput = "Test Person says: This is a test person.";
        assertEquals(expectedOutput, person.getName() + " says: " + person.getFact());
    }

    @Test
    public void testGetHealth() {
        assertEquals(50, person.getHealth());
    }

    @Test
    public void testTakeDamage() {
        person.takeDamage(20);
        assertEquals(30, person.getHealth());

        person.takeDamage(40);
        assertEquals(0, person.getHealth());
    }

    @Test
    public void testAttack() {
        Hero hero = new Hero("Test Hero", 50.0, 5.0);
        person.attack(hero);
        assertEquals(95, hero.getLifePoints());
    }

    @Test
    public void testIsAlive() {
        assertTrue(person.isAlive());

        person.takeDamage(50);
        assertFalse(person.isAlive());
    }

    @Test
    public void testTransform() {
        person.transform();
        assertEquals("I am now a demon!", person.getFact());
    }
}