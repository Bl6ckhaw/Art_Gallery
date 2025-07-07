package com.projetcave.hero;

import com.projetcave.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Backpack implements Serializable {
    private static final long serialVersionUID = 1L;
    private double volume;
    private double weight;
    private List<Item> items;

    public Backpack(double volume, double weight) {
        this.volume = volume;
        this.weight = weight;
        this.items = new ArrayList<>();
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Your backpack is empty.");
        } else {
            System.out.println("Items in your backpack:");
            for (Item item : items) {
                System.out.println("  " + item.getName());
            }
        }
    }
}