package com.ryan.eggwars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class RandomTools implements Listener, CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        
        if (label.equalsIgnoreCase("eggtool") && args[0] != null) {
            if (args[0].equalsIgnoreCase("clearitems")) {
                for (Entity entity : player.getWorld().getEntities()) {
                    if (entity.getType() == EntityType.DROPPED_ITEM) {
                        entity.remove();
                    }
                }
            }
        }
        
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> parameters = new ArrayList<>();
    
        parameters.add("clearitems");
    
        return parameters;
    }
}
