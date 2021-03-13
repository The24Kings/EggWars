package com.ryan.eggwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CreateGeneratorTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> parameters = new ArrayList<>();
    
        parameters.add("island");
        parameters.add("diamond");
        parameters.add("emerald");
        parameters.add("amethyst");
        parameters.add("clear");
    
        return parameters;
    }
}
