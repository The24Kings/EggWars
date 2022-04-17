package com.ryan.eggwars.commands;

import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveTeam implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("leave")) {
            if (!(sender instanceof Player)) return true;

            TeamManager.leaveTeam((Player) sender);
        }
        
        return true;
    }
}
