package com.projetcave.exits;

import com.projetcave.locations.Location;
import java.io.Serializable;

public class Exit implements Serializable {
    private static final long serialVersionUID = 1L;
    private String direction;
    private Location destination;
    private boolean locked;
    private Location linkedLocation;

    public Exit(String direction, Location destination, boolean locked, Location linkedLocation) {
        this.direction = direction;
        this.destination = destination;
        this.locked = locked;
        this.linkedLocation = linkedLocation;
    }

    public void setLocked(boolean isLocked) {
        this.locked = isLocked;
        // Find the corresponding exit in the linked location and update its lock status
        for (Exit exit : linkedLocation.getExits()) {
            if (exit.getDestination() == this.destination) {
                exit.locked = isLocked;
                break;
            }
        }
    }

    public String getDirection() {
        return direction;
    }

    public Location getDestination() {
        return destination;
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock() {
        this.locked = false;
    }
}