package ca.ubc.ece.cpen221.worlds.items;

import ca.ubc.ece.cpen221.worlds.Location;
import ca.ubc.ece.cpen221.worlds.Util;

import javax.swing.*;

public class GasStation implements Item {
    private final static ImageIcon gasImage = Util.loadImage("gas.gif");

    private Location location;

    /**
     * Plant a Grass at <code> location </code>. The location must be valid and
     * empty
     *
     * @param location : the location where this grass will be created
     */
    public GasStation(Location location) {
        this.location = location;
    }

    @Override
    public ImageIcon getImage() {
        return gasImage;
    }

    @Override
    public String getName() {
        return "gas";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getPlantCalories() {
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return 0;
    }

    @Override
    public void loseEnergy(int energy) {
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public int getStrength() {
        return Integer.MAX_VALUE;
    }
}
