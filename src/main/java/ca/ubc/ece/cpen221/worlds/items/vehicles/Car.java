package ca.ubc.ece.cpen221.worlds.items.vehicles;

import ca.ubc.ece.cpen221.worlds.Direction;
import ca.ubc.ece.cpen221.worlds.Location;
import ca.ubc.ece.cpen221.worlds.Util;
import ca.ubc.ece.cpen221.worlds.World;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.DestroyCommand;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.GasStation;
import ca.ubc.ece.cpen221.worlds.items.Grass;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.MoveableItem;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.worlds.items.animals.Gnat;

import javax.swing.*;
import java.util.Set;

public class Car extends AbstractVehicle {
    protected static final int INITIAL_ENERGY = 1000;
    protected static final int MAX_ENERGY = 1200;
    protected static final int STRENGTH = 1000;
    protected static final int COOLDOWN = 10;
    protected static final Direction INITIAL_DIRECTION = Util.getRandomDirection();
    private static final ImageIcon carImage = Util.loadImage("cars.gif");

    public Car(Location initialLocation) {
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
        return carImage;
    }

    /**
     * Gets a String that serves as a unique identifier for this type of Item.
     *
     * @return the name of this item
     */
    @Override
    public String getName() {
        return "Car";
    }
}
