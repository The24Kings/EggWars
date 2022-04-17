package com.ryan.eggwars.commands;

import com.ryan.eggwars.teams.TeamColor;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JoinTeam implements TabExecutor {
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (label.equalsIgnoreCase("join")) {
            if (!(sender instanceof Player)) return true;
            
            if (args[0] == null) sender.sendMessage("Please specify a team!");
            
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
        }
        
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        
        ArrayList<String> parameters = new ArrayList<>();

        if(args.length == 1) {
            for (TeamColor teamColor : TeamColor.values()) {
                parameters.add(teamColor.toString().toLowerCase());
            }
        }
        
        return parameters;
    }
}
