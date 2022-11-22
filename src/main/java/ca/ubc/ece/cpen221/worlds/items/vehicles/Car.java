package ca.ubc.ece.cpen221.worlds.items.vehicles;

import ca.ubc.ece.cpen221.worlds.Direction;
import ca.ubc.ece.cpen221.worlds.Location;
import ca.ubc.ece.cpen221.worlds.Util;
import ca.ubc.ece.cpen221.worlds.World;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.DestroyCommand;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.MoveableItem;

import javax.swing.*;
import java.util.Set;

public class Car implements Vehicle {
    private static final int INITIAL_ENERGY = 1000;
    private static final int MAX_ENERGY = 1200;
    private static final int STRENGTH = 1000;

    private static final int VIEW_RANGE = 10;
    private static final int COOLDOWN = 10;
    private static final ImageIcon carImage = Util.loadImage("cars.gif");

    private static final int INITIAL_SPEED = 50;
    private static final Direction INITIAL_DIRECTION = Util.getRandomDirection();

    //private final AI ai;

    private Location location;
    private int energy;
    private Direction direction;
    private int cooldown;

    public Car(Location initialLocation) {
        this.location = initialLocation;
        this.energy = INITIAL_ENERGY;
        this.direction = INITIAL_DIRECTION;
        this.cooldown = COOLDOWN;
    }

    /**
     * The energy that this food contains as a plant
     *
     * @return plant energy of this food
     */
    @Override
    public int getPlantCalories() {
        return 0;
    }

    /**
     * The energy that this food contains as an animal
     *
     * @return meat energy of this food
     */
    @Override
    public int getMeatCalories() {
        return 0;
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

    /**
     * Gets the location of this Item in the World.
     *
     * @return the location in the world
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the strength of this Item. Generally, if an item possesses
     * greater strength than another, then it can eliminate the other. For
     * example, a {@link Vehicle} can run over everything that has a lower
     * strength.
     *
     * @return the strength of this Item
     */
    @Override
    public int getStrength() {
        return STRENGTH;
    }

    /**
     * Causes this Item to lose energy. The consequences of this varies for
     * different types of Items.
     * <ul>
     * <li>{@link Grass} and {@link Gnat} die when they lose energy.</li>
     * <li>{@link ArenaAnimal} reduces its energy level and it dies if its
     * energy level drops below or equal to 0</li>
     * </ul>
     *
     * @param energy the amount of energy lost
     */
    @Override
    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    /**
     * Returns whether or not this Item is dead. If this Item is dead, it will
     * be removed from the World. An item is dead if it is eaten, run over by a
     * Vehicle, loses all its energy and energy level drops below or equal 0,
     * etc.
     *
     * @return true if this Item is dead, false if alive
     */
    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    /**
     * Move to the target location. The <code> targetLocation </code> must be
     * valid and empty.
     *
     * @param targetLocation the location that this item is moving to
     */
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    /**
     * Returns the maximum distance that this item can move in one step. For
     * example, a {@link MoveableItem} with moving range 1 can only move to
     * adjacent locations.
     *
     * @return the maximum moving distance
     */
    @Override
    public int getMovingRange() {
        return 10;
    }

    /**
     * Returns the cooldown period between two consecutive actions. This
     * represents the number of steps passed between two actions.
     *
     * @return the number of steps between actions
     */
    @Override
    public int getCoolDownPeriod() {
        return cooldown;
    }

    /**
     * Returns the next action to be taken, given the current state of the
     * world.
     *
     * @param world the current world
     * @return the next action of this Actor as a {@link Command}
     */
    @Override
    public Command getNextAction(World world) {
        Direction dir = direction;
        Location targetLocation = new Location(this.getLocation(), dir);
        Set<Item> items = world.searchSurroundings(location, VIEW_RANGE);

        if (Util.isValidLocation(world, targetLocation) &&
                Util.isLocationEmpty(world, targetLocation)) {
            if(cooldown > 1){
                cooldown--;
            }
            return new MoveCommand(this, targetLocation);//move forward and accelerate if nothing in front
        }else if(cooldown > 10){
            direction = Util.getRandomDirection();//change direction if slow enough
        }else {
            for(Item i : items){
                if(i.getLocation().equals(targetLocation)){
                    cooldown += 3;
                    return new DestroyCommand(this, i);//hit the obstacle and slow down
                }
            }
        }

        direction = Util.getRandomDirection();

        return new WaitCommand();
    }
}
