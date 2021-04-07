package com.ryan.eggwars.util;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class EggUtil {
    
    public static Team getEggTeam(Block egg) {
        
        Location eggLocation = egg.getLocation();
        Location blockBelow = new Location(eggLocation.getWorld(), eggLocation.getBlockX(),
                eggLocation.getBlockY() - 1,
                eggLocation.getBlockZ());
        
        return TeamManager.getTeam(eggLocation.getWorld().getBlockAt(blockBelow).getType());
    }
}
