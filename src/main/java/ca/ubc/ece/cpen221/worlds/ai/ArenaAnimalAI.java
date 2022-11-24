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

public class ArenaAnimalAI implements AI {
    private int energy;

    public ArenaAnimalAI(int energy) {
        this.energy = energy;
    }

    public boolean isLocationEmpty(ArenaWorld world, ArenaAnimal animal, Location location) {
        if (!Util.isValidLocation(world, location)) {
            return false;
        }
        Set<Item> possibleMoves = world.searchSurroundings(animal);
        for (Item item : possibleMoves) {
            if (item.getLocation().equals(location)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(animal.getLocation(), dir);
        Set<Item> possibleEats = world.searchSurroundings(animal);
        Location current = animal.getLocation();
        for (Item item : possibleEats) {
            if ((item.getName().equals("Gnat") || item.getName().equals("Rabbit"))
                && (current.getDistance(item.getLocation()) == 1)) {
                return new EatCommand(animal, item); // arena animals eat gnats
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
