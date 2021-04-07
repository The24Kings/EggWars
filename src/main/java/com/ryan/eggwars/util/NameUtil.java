package com.ryan.eggwars.util;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NameUtil {
    
    /**
     * Returns the player's name with bold and the team's color.
     *
     * @param player Player to get the name of
     * @return The bolded and colored name.
     */
    public static String getBoldedName(Player player) {
        Team team = TeamManager.getTeam(player);
        
        if (team == null) {
            return ChatColor.BOLD + player.getName();
        } else {
            return team.getChatColor() + "" + ChatColor.BOLD + player.getName();
        }
    }
}
