package ca.ubc.ece.cpen221.worlds.ai;

import ca.ubc.ece.cpen221.worlds.ArenaWorld;
import ca.ubc.ece.cpen221.worlds.Direction;
import ca.ubc.ece.cpen221.worlds.Location;
import ca.ubc.ece.cpen221.worlds.Util;
import ca.ubc.ece.cpen221.worlds.commands.*;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CatAI extends AbstractAI {
    private final int closest = 5; // max number; greater than cat's view range
    private int energy;

    public CatAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(animal.getLocation(), dir);
        Set<Item> possibleEats = world.searchSurroundings(animal);
        Location current = animal.getLocation();
        for (Item item : possibleEats) {
            if ((item.getName().equals("Gnat") || item.getName().equals("grass"))
                && (current.getDistance(item.getLocation()) == 1)) {
                return new EatCommand(animal, item); // arena animals eat cats
                // and rabbits
            }
        }
        if (Util.isValidLocation(world, targetLocation)
            && this.isLocationEmpty(world, animal, targetLocation)) {

            if(animal.getEnergy() >= animal.getMinimumBreedingEnergy() && ThreadLocalRandom.current().nextInt(0,10) < 1){
                return new BreedCommand(animal, targetLocation);
            }

            return new MoveCommand(animal, targetLocation);
        }
        return new WaitCommand();
    }
}
