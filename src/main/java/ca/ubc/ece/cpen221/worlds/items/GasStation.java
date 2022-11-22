package ca.ubc.ece.cpen221.worlds.items;

import ca.ubc.ece.cpen221.worlds.Location;

import javax.swing.*;

public class GasStation implements Item {

    /**
     * The visualization of this Item in the world.
     *
     * @return the image of this Item
     */
    @Override
    public ImageIcon getImage() {
        return null;
    }

    /**
     * Gets a String that serves as a unique identifier for this type of Item.
     *
     * @return the name of this item
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Gets the location of this Item in the World.
     *
     * @return the location in the world
     */
    @Override
    public Location getLocation() {
        return null;
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
        return 0;
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
        return false;
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
}
