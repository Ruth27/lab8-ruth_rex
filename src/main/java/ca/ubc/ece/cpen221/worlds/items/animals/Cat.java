package ca.ubc.ece.cpen221.worlds.items.animals;

import ca.ubc.ece.cpen221.worlds.*;
import ca.ubc.ece.cpen221.worlds.ai.AI;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.LivingItem;

import javax.swing.*;

public class Cat implements ArenaAnimal {
    private static final ImageIcon catImage = Util.loadImage("cat.gif");

    private static final int INITIAL_ENERGY = 50;
    private static final int MAX_ENERGY = 100;
    private static final int MEAT_CALORIES = 500;
    private static final int VIEW_RANGE = 5;
    private static final int MIN_BREEDING_ENERGY = 20;
    private static final int COOLDOWN = 5;
    private static final int STRENGTH = 25;
    private final AI ai;
    private int energy;


    private Location location;

    /**
     * Create a new Cat at <code>initialLocation</code>. The
     * <code>initialLocation</code> must be valid and empty.
     *
     * @param initialLocation the location where the Gnat will be created
     */
    public Cat(AI catAI, Location initialLocation) {
        this.ai = catAI;
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }
    /**
     * Returns the cool down period between two consecutive actions. This
     * represents the number of steps passed between two actions.
     *
     * @return the number of steps between actions
     */
    @Override
    public int getCoolDownPeriod() {
        // Each Gnat acts every 1-20 steps randomly.
        return COOLDOWN;
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
        // The Cat selects a random direction and check if the next location at
        // the direction is valid and empty. If yes, then it moves to the
        // location, otherwise it waits.
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation)
            && Util.isLocationEmpty(world, targetLocation)) {
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }

    /**
     * The energy that this food contains as a plant
     * Except cats do not eat plants
     * @return plant energy of this food
     */
    @Override
    public int getPlantCalories() {
        // This Cat is not a plant.
        return 0;
    }

    /**
     * The energy that this food contains as an animal
     *
     * @return meat energy of this food
     */
    @Override
    public int getMeatCalories() {
        return MEAT_CALORIES;
    }

    /**
     * The visualization of this Item in the world.
     *
     * @return the image of this Item
     */
    @Override
    public ImageIcon getImage() {
        return catImage;
    }

    /**
     * Gets a String that serves as a unique identifier for this type of Item.
     *
     * @return the name of this item
     */
    @Override
    public String getName() {
        return "Cat";
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
     * @param energyloss the amount of energy lost
     */
    @Override
    public void loseEnergy(int energyloss) {
        this.energy = this.energy - energyloss;
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
     * Returns the current energy of this living thing. A {@link LivingItem}
     * gains energy by eating and loses energy by performing actions. If its
     * energy level drops below or equal to 0, it dies.
     *
     * @return current energy level
     */
    @Override
    public int getEnergy() {
        return energy;
    }

    /**
     * Breeds a child of this {@link LivingItem}, the child must be the same
     * animal as the parent. A {@link LivingItem} can only breed when all of the
     * following conditions are satisfied:
     * <ol>
     * <li>There is an empty location adjacent to its location</li>
     * <li>If it is an {@link ArenaAnimal}, its energy level must exceeds its
     * breeding limit, specified by
     * {@link ArenaAnimal#getMinimumBreedingEnergy()}</li>
     * </ol>
     * After breeding, both the parent and the child should have half of the
     * energy of the parent's energy.
     *
     * @return child the offspring of this animal
     */
    @Override
    public LivingItem breed() {
        Cat child = new Cat(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

    /**
     * Eat a {@link Food}, the food must be adjacent to and have less strength
     * than this {@link LivingItem}.
     *
     * @param food the Food to be eaten
     */
    @Override
    public void eat(Food food) {
        // Note that energy does not exceed energy limit.
        energy = Math.min(MAX_ENERGY, energy + food.getMeatCalories());
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
        return 1;
    }

    /**
     * Returns the limit of the {@link LivingItem}'s energy. The
     * {@link LivingItem}'s energy should never exceed this limit.
     *
     * @return the energy limit of this animal
     */
    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    /**
     * Returns the range of the animal's vision. The range is measured in
     * Manhattan Distance, for example, if an animal has view range of 2, then
     * it can see all valid locations in the rectangle
     * {(x-2,y-2),(x+2,y-2),(x-2,y+2),(x+2,y+2)}, where (x,y) are the
     * coordinates of its current location.
     *
     * @return the view range of this animal
     */
    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }

    /**
     * Returns the minimum energy required for an animal to breed
     *
     * @return the minimum breeding energy of this animal
     */
    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }
}
