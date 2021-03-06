package com.ryan.eggwars.commands;

import com.ryan.eggwars.teams.TeamColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class JoinTeamTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        
        ArrayList<String> parameters = new ArrayList<>();
        
        for (TeamColor teamColor : TeamColor.values()) {
            parameters.add(teamColor.toString().toLowerCase());
        }
        
        return parameters;
    }
}
