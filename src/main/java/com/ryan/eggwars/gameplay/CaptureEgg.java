package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamColor;
import com.ryan.eggwars.teams.TeamManager;
import com.ryan.eggwars.util.EggUtil;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CaptureEgg {
    
    HashMap<Player, TeamColor> capturedEggs = new HashMap<>();
    
    // TODO: this
    public static void handleEggWasPunched(Player player, Block egg) {
        
        player.sendMessage("You punch egg");
        
        if (TeamManager.getTeam(player) == null) {
            player.sendMessage("not on team?");
            return;
        }
        
        Team playerTeam = TeamManager.getTeam(player);
        Team eggTeam = EggUtil.getEggTeam(egg);
        
        if (eggTeam.getTeamColor() != playerTeam.getTeamColor()) {
            player.sendMessage("You captured " + eggTeam.getColoredName() + ChatColor.RESET + " team's egg!");
            
        } else {
            player.sendMessage(ChatColor.RED + "You can't capture your own egg!");
        }
        
    }
}
