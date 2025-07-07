package com.projetcave.commands;

import com.projetcave.locations.Location;
import com.projetcave.hero.Hero;
import com.projetcave.exits.Exit;
import com.projetcave.items.Item;
import com.projetcave.people.Person;
import com.projetcave.game_state.GameState;

public class Commands {
    public static void executeCommand(String command, GameState state) {
        Location currentLocation = state.getCurrentLocation();
        Hero hero = state.getHero();

        String[] parts = command.split(" ");
        String cmd = parts[0];
        String arg1 = parts.length > 1 ? parts[1] : "";
        String arg2 = parts.length > 2 ? parts[2] : "";

        switch (parts.length) {
            case 1:
                if (cmd.equalsIgnoreCase("HELP")) {
                    System.out.println("Available commands:");
                    System.out.println("  GO <direction>");
                    System.out.println("  LOOK");
                    System.out.println("  TAKE <item>");
                    System.out.println("  USE <item> <target>");
                    System.out.println("  INTERACT <person>");
                    System.out.println("  ATTACK <target>");
                    System.out.println("  HELP");
                    System.out.println("  INVENTORY");
                    System.out.println("  QUIT");
                } else if (cmd.equalsIgnoreCase("LOOK")) {
                    currentLocation.displayLocation(hero);
                } else if (cmd.equalsIgnoreCase("INVENTORY")) {
                    hero.displayInventory();
                } else {
                    System.out.println("Unknown command: " + cmd);
                }
                break;
            case 2:
                if (cmd.equalsIgnoreCase("GO")) {
                    boolean found = false;
                    for (Exit exit : currentLocation.getExits()) {
                        if (exit.getDirection().equalsIgnoreCase(arg1)) {
                            if (exit.isLocked()) {
                                System.out.println("The exit is locked. You need a key to unlock it.");
                            } else {
                                System.out.println("You move to: " + exit.getDestination().getName());
                                state.setCurrentLocation(exit.getDestination());
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("There is no exit in that direction!");
                    }
                } else if (cmd.equalsIgnoreCase("TAKE")) {
                    boolean found = false;
                    for (int i = 0; i < currentLocation.getItems().size(); i++) {
                        Item item = currentLocation.getItems().get(i);
                        if (item.getName().equalsIgnoreCase(arg1)) {
                            if (item.canBeTaken()) {
                                hero.getBackpack().addItem(item);
                                System.out.println("You added " + item.getName() + " to your inventory.");
                                currentLocation.getItems().remove(i);
                            } else {
                                System.out.println("You cannot take the " + item.getName() + ".");
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("There is no item called '" + arg1 + "' here.");
                    }
                } else if (cmd.equalsIgnoreCase("INTERACT")) {
                    boolean found = false;
                    for (Person person : currentLocation.getPeople()) {
                        if (person.getName().equalsIgnoreCase(arg1)) {
                            person.interact();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("There is no person called '" + arg1 + "' here.");
                    }
                } else if (cmd.equalsIgnoreCase("ATTACK")) {
                    boolean found = false;
                    for (Person person : currentLocation.getPeople()) {
                        if (person.getName().equalsIgnoreCase(arg1)) {
                            if (hero.isVaseBroken()) {
                                System.out.println("You attacked " + person.getName() + " with the sword.");
                                hero.attack(person);
                                if (!person.isAlive()) {
                                    currentLocation.getPeople().remove(person);
                                    System.out.println(person.getName() + " has been defeated.");
                                }else if (!hero.isDistracted()) {

                                    System.out.println(person.getName() + " attack you with demonic power.");
                                    person.attack(hero);
                                }else {
                                    System.out.println("Demon is distracted and cannot attack.");
                                }
                                
                            } else {
                                System.out.println("You cannot attack civilians.");
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("There is no demon called '" + arg1 + "' here.");
                    }
                }
                break;
            case 3:
                if (cmd.equalsIgnoreCase("USE")) {
                    boolean foundItem = false;
                    Item item = null;

                    for (Item i : hero.getBackpack().getItems()) {
                        if (i.getName().equalsIgnoreCase(arg1)) {
                            item = i;
                            foundItem = true;
                            break;
                        }
                    }

                    if (foundItem) {
                        item.use(hero, currentLocation, arg2);
                        if (!item.getName().equalsIgnoreCase("key") && !item.getName().equalsIgnoreCase("sword")) {
                            hero.getBackpack().removeItem(item);
                        }
                    } else {
                        System.out.println("You don't have " + arg1 + " in your inventory.");
                    }
                }
                break;
            default:
                System.out.println("Invalid command format.");
                break;
        }
    }
}