package ca.ubc.ece.cpen221.worlds.ai;

import ca.ubc.ece.cpen221.worlds.ArenaWorld;
import ca.ubc.ece.cpen221.worlds.Direction;
import ca.ubc.ece.cpen221.worlds.Location;
import ca.ubc.ece.cpen221.worlds.Util;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.EatCommand;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;

import java.util.Set;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
    private final int closest = 2; // max number; greater than fox's view range
    private int energy;

    public FoxAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(animal.getLocation(), dir);
        Set<Item> possibleEats = world.searchSurroundings(animal);
        Location current = animal.getLocation();
        for (Item item : possibleEats) {
            if ((item.getName().equals("Cat") || item.getName().equals("Rabbit"))
                && (current.getDistance(item.getLocation()) == 1)) {
                return new EatCommand(animal, item); // arena animals eat cats
                // and rabbits
            }
        }
        if (Util.isValidLocation(world, targetLocation)
            && this.isLocationEmpty(world, animal, targetLocation)) {
            return new MoveCommand(animal, targetLocation);
        }
        return new WaitCommand();
    }

}
