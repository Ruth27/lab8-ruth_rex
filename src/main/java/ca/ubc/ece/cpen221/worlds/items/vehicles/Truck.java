package ca.ubc.ece.cpen221.worlds.items.vehicles;

import ca.ubc.ece.cpen221.worlds.Direction;
import ca.ubc.ece.cpen221.worlds.Location;
import ca.ubc.ece.cpen221.worlds.Util;

import javax.swing.*;

public class Truck extends AbstractVehicle{
    protected static final int INITIAL_ENERGY = 2000;
    protected static final int MAX_ENERGY = 2200;
    protected static final int STRENGTH = 2000;
    protected static final int COOLDOWN = 10;
    protected static final Direction INITIAL_DIRECTION = Util.getRandomDirection();
    private static final ImageIcon truckImage = Util.loadImage("trucks.gif");

    public Truck(Location initialLocation) {
        super.MAX_ENERGY = MAX_ENERGY;
        super.STRENGTH = STRENGTH;

        super.location = initialLocation;
        super.energy = INITIAL_ENERGY;
        super.direction = INITIAL_DIRECTION;
        super.cooldown = COOLDOWN;
    }

    /**
     * The visualization of this Item in the world.
     *
     * @return the image of this Item
     */
    @Override
    public ImageIcon getImage() {
        return truckImage;
    }

    /**
     * Gets a String that serves as a unique identifier for this type of Item.
     *
     * @return the name of this item
     */
    @Override
    public String getName() {
        return "Truck";
    }
}
