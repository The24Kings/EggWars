package com.ryan.eggwars.util;

import com.ryan.eggwars.teams.TeamColor;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class EggUtil {
    
    public static TeamColor getEggTeam(Block egg) {
        
        Location eggLocation = egg.getLocation();
        Location blockBelow = new Location(eggLocation.getWorld(), eggLocation.getBlockX(),
                eggLocation.getBlockY() - 1,
                eggLocation.getBlockZ());
        
        switch (eggLocation.getWorld().getBlockAt(blockBelow).getType()) {
            case RED_WOOL:
                return TeamColor.RED;
                
            case BLUE_WOOL:
                return TeamColor.BLUE;
                
            case GREEN_WOOL:
                return TeamColor.GREEN;
                
            case YELLOW_WOOL:
                return TeamColor.YELLOW;
                
            default:
                return null;
        }
    }
}
