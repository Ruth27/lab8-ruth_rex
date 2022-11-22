package ca.ubc.ece.cpen221.worlds.commands;

import ca.ubc.ece.cpen221.worlds.World;
import ca.ubc.ece.cpen221.worlds.items.Item;
import ca.ubc.ece.cpen221.worlds.items.LivingItem;
import ca.ubc.ece.cpen221.worlds.items.vehicles.Vehicle;

import java.util.Set;

public class DestroyCommand implements Command {
    private final Vehicle v1;
    private final Item v2;


    public DestroyCommand(Vehicle v1, Item v2) {
        this.v1 = v1;
        this.v2 = v2;
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
        if (v1.getLocation().getDistance(v2.getLocation()) != 1) {
            throw new InvalidCommandException("Invalid EatCommand: Vehicles not adjacent");
        }

        if (v1.getStrength() < v2.getStrength()) {
            v1.loseEnergy(Integer.MAX_VALUE);
        }else{
            v2.loseEnergy(Integer.MAX_VALUE);
        }
    }
}
