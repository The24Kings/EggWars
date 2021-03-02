package com.ryan.eggwars.commands;

import com.ryan.eggwars.teams.TeamColor;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinTeam implements CommandExecutor {
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (label.equalsIgnoreCase("join")) {
            if (!(sender instanceof Player)) return true;
            
            Player player = (Player) sender;
            
            if (args[0].equalsIgnoreCase("red")) {
                TeamManager.joinTeam(player, TeamColor.RED);
            } else if (args[0].equalsIgnoreCase("yellow")) {
                TeamManager.joinTeam(player, TeamColor.YELLOW);
    
            } else if (args[0].equalsIgnoreCase("green")) {
                TeamManager.joinTeam(player, TeamColor.GREEN);
    
            } else if (args[0].equalsIgnoreCase("blue")) {
                TeamManager.joinTeam(player, TeamColor.BLUE);
    
            }
            
            sender.sendMessage("You have joined the " + TeamManager.getTeam(player).getColoredName() + " team!");
        }
        
        return true;
    }
}
