package ca.ubc.ece.cpen221.worlds.ai;


import ca.ubc.ece.cpen221.worlds.*;
import ca.ubc.ece.cpen221.worlds.commands.*;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;


import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

    private int closest = 10; // max number; greater than rabbit's view range
    private int temp;
    private boolean rabbitFound;

    public RabbitAI() {
    }

    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(animal.getLocation(), dir);
        Set<Item> possibleEats = world.searchSurroundings(animal);

        Location current = animal.getLocation();

        for (Item item : possibleEats) {
            if ((item.getName().equals("grass"))
                && (current.getDistance(item.getLocation()) == 1)) {
                return new EatCommand(animal, item);
            }
        }
        if (Util.isValidLocation(world, targetLocation)
            && this.isLocationEmpty(world, animal, targetLocation)) {

            if(animal.getEnergy() >= animal.getMinimumBreedingEnergy() && ThreadLocalRandom.current().nextInt(0,100) < 20){
                return new BreedCommand(animal, targetLocation);
            }

            return new MoveCommand(animal, targetLocation);
        }

        return new WaitCommand();
    }
}
