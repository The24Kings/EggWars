package com.ryan.eggwars.util;

import com.ryan.eggwars.gameplay.CaptureEgg;
import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class EggUtil {
    
    /**
     * Given that an egg is always placed on top of wool, get the {@link Team} that the egg belongs to.
     *
     * @param egg The egg that was placed
     * @return The {@link Team} that the egg belongs to.
     */
    public static Team getEggTeam(Block egg) {
        
        Location eggLocation = egg.getLocation();
        Location blockBelow = new Location(eggLocation.getWorld(), eggLocation.getBlockX(),
                eggLocation.getBlockY() - 1,
                eggLocation.getBlockZ());
        
        return TeamManager.getTeam(eggLocation.getWorld().getBlockAt(blockBelow).getType());
    }
    
    /**
     * Given that an egg is always placed on top of wool, get the {@link Team} that the egg belongs to.
     *
     * @param eggLocation The {@link Location} of the egg that was placed
     * @return The {@link Team} that the egg belongs to.
     */
    public static Team getEggTeam(Location eggLocation) {
        
        Location blockBelow = new Location(eggLocation.getWorld(), eggLocation.getBlockX(),
                eggLocation.getBlockY() - 1,
                eggLocation.getBlockZ());
        
        return TeamManager.getTeam(eggLocation.getWorld().getBlockAt(blockBelow).getType());
    }
    
    public static Team getWhoCaptured(Team team) {
        return CaptureEgg.capturedEggs.get(team);
    }
}