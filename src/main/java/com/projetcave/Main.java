package com.projetcave;

import com.projetcave.commands.Commands;
import com.projetcave.exits.Exit;
import com.projetcave.hero.Hero;
import com.projetcave.items.Item;
import com.projetcave.locations.Location;
import com.projetcave.people.Person;
import com.projetcave.game_state.GameState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Initialize the game state
            GameState state = new GameState();
            state.setLocationCount(6);  // Assume 6 locations for now

            // Create hero
            Hero hero = new Hero("Adventurer", 50.0, 5.0); // Backpack volume and weight

            // Create items
            Item sword = new Item("Sword", "A sharp medieval sword.", true, 5.0, 3.0);
            Item key = new Item("Key", "A small golden key.", true, 0.1, 0.1);
            Item ankh = new Item("Ankh", "An ancient Egyptian ankh.", true, 1.0, 0.5);
            Item vase = new Item("Vase", "A beautiful Greek vase.", true, 3.0, 2.0);
            Item painting = new Item("Painting", "A Renaissance painting.", true, 10.0, 5.0);
            Item sculpture = new Item("Sculpture", "A modern art sculpture.", true, 15.0, 10.0);

            // Create locations with themes
            Location room1 = new Location("Medieval Room", "A room filled with medieval artifacts.");
            Location room2 = new Location("Egyptian Room", "A room filled with ancient Egyptian artifacts.");
            Location room3 = new Location("Greek Room", "A room filled with ancient Greek artifacts.");
            Location room4 = new Location("Renaissance Room", "A room filled with Renaissance art.");
            Location room5 = new Location("Modern Art Room", "A room filled with modern art.");
            Location room6 = new Location("Contemporary Room", "A room filled with contemporary art.");

            // Add items to rooms
            room1.addItem(sword);
            room2.addItem(ankh);
            room3.addItem(vase);
            room4.addItem(painting);
            room5.addItem(sculpture);
            room6.addItem(key);  // Add the key to the Contemporary Room

            // Create and add exits
            room1.addExit(new Exit("east", room2, false, null));
            room1.addLinkedExits(room4, "south", "north", true);


            room2.addExit(new Exit("west", room1, false, null));
            room2.addExit(new Exit("east", room3, false, null));
            room2.addLinkedExits(room5, "south", "north", true);
        ;

            room3.addExit(new Exit("west", room2, false, null));
            room3.addExit(new Exit("south", room6, false, null));

            room4.addExit(new Exit("east", room5, false, null));

            room5.addExit(new Exit("west", room4, false, null));
            room5.addLinkedExits(room6, "east", "west", true);

            room6.addExit(new Exit("north", room3, false, null));

            // Add people to rooms with facts about the room's theme
            room1.addPerson(new Person("Alice", "Medieval swords were often used in close combat."));
            room2.addPerson(new Person("Bob", "The ankh is an ancient Egyptian symbol of life."));
            room3.addPerson(new Person("Charlie", "Greek vases often depict scenes from mythology."));
            room4.addPerson(new Person("Diana", "Renaissance art focused on humanism and realism."));
            room5.addPerson(new Person("Eve", "Modern art includes a wide range of styles and techniques."));
            room6.addPerson(new Person("Frank", "Contemporary art often reflects current social issues."));

            // Set the starting location
            state.setCurrentLocation(room1);
            state.setHero(hero);
            state.getLocations().add(room1);
            state.getLocations().add(room2);
            state.getLocations().add(room3);
            state.getLocations().add(room4);
            state.getLocations().add(room5);
            state.getLocations().add(room6);

            String command;

            System.out.println("Welcome to the Art Gallery Adventure Game!");

            // Main game loop
            while (true) {
                System.out.print("\nEnter command: ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("QUIT")) {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return; // Exit the program
                } else if (command.equalsIgnoreCase("SAVE")) {
                    // Set the current location index before saving
                    for (int i = 0; i < state.getLocationCount(); i++) {
                        if (state.getLocations().get(i).getName().equals(state.getCurrentLocation().getName())) {
                            state.getHero().setCurrentLocationIndex(i);
                            break;
                        }
                    }
                    state.saveGame();  // Save the game state to a file
                } else if (command.equalsIgnoreCase("LOAD")) {
                    state.loadGame();  // Load the saved game state
                    // Update the game state from the loaded data
                    hero = state.getHero();
                    state.setCurrentLocation(state.getLocations().get(state.getHero().getCurrentLocationIndex()));
                } else {
                    Commands.executeCommand(command, state);
                }

                // Check if the hero is alive
                if (!hero.isAlive()) {
                    System.out.println("You have been defeated. Game over.");
                    System.out.print("Do you want to play again? (yes/no): ");
                    String playAgain = scanner.nextLine();
                    if (playAgain.equalsIgnoreCase("yes")) {
                        break; // Break the inner loop to restart the game
                    } else {
                        System.out.println("Goodbye!");
                        scanner.close();
                        return; // Exit the program
                    }
                }

                // Check if all demons are defeated
                boolean allDemonsDefeated = true;
                for (Location location : state.getLocations()) {
                    if (!location.getPeople().isEmpty()) {
                        allDemonsDefeated = false;
                        break;
                    }
                }
                if (allDemonsDefeated) {
                    System.out.println("Congratulations! You have defeated all the demons. You win!");
                    scanner.close();
                    return; // Exit the program
                }
            }
        }
    }
}