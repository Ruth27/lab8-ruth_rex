package ca.ubc.ece.cpen221.worlds.commands;

import ca.ubc.ece.cpen221.worlds.World;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.vehicles.Vehicle;

public class DestroyCommand implements Command {
    private final Vehicle vehicle;
    private final Item item;


    public DestroyCommand(Vehicle vehicle, Item item) {
        this.vehicle = vehicle;
        this.item = item;
    }

    /**
     * Execute a command in the world. If a command violates a rule or invariant
     * in the World (for example, moving to a nonempty location), it throws an
     * {@link InvalidCommandException}
     *
     * @param world the current World
     * @throws InvalidCommandException if the command violates a rule
     */
    @Override
    public void execute(World world) throws InvalidCommandException {
        if (vehicle.getLocation().getDistance(item.getLocation()) != 1) {
            throw new InvalidCommandException("Invalid EatCommand: Vehicles not adjacent");
        }

        if (vehicle.getStrength() < item.getStrength()) {
            vehicle.loseEnergy(Integer.MAX_VALUE);
        }else{
            item.loseEnergy(Integer.MAX_VALUE);
        }
    }
}
