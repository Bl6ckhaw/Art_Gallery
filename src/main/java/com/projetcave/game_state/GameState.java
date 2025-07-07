package com.projetcave.game_state;

import com.projetcave.hero.Hero;
import com.projetcave.locations.Location;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Location> locations;
    private Hero hero;
    private Location currentLocation;
    private int locationCount;

    public GameState() {
        this.locations = new ArrayList<>();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getLocationCount() {
        return locationCount;
    }

    public void setLocationCount(int locationCount) {
        this.locationCount = locationCount;
    }

    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
            out.writeObject(this);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Could not save game.");
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
            GameState loadedState = (GameState) in.readObject();
            this.locations = loadedState.locations;
            this.hero = loadedState.hero;
            this.currentLocation = loadedState.currentLocation;
            this.locationCount = loadedState.locationCount;
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: Could not load game.");
            e.printStackTrace();
        }
    }
}