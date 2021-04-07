package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.teams.TeamManager;
import com.ryan.eggwars.util.EggUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CaptureEgg {
    
    // TODO: this
    public static void handleEggWasPunched(Player player, Block egg) {
        
        player.sendMessage("You punch egg");
        
        if (TeamManager.getTeam(player) == null) {
            player.sendMessage("not on team?");
        }
        
        if (EggUtil.getEggTeam(egg) == TeamManager.getTeam(player).getTeamColor()) {
        
        }
    
    }
}
