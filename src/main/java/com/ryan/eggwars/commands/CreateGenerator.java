package com.ryan.eggwars.commands;

import com.ryan.eggwars.generators.GeneratorManager;
import com.ryan.eggwars.generators.GeneratorType;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateGenerator implements TabExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        Location playerLocation = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
        
        if (label.equalsIgnoreCase("gen") && args[0] != null) {
            if (args[0].equalsIgnoreCase("island")) {
                GeneratorManager.createGenerator(GeneratorType.ISLAND, playerLocation);
                
            } else if (args[0].equalsIgnoreCase("diamond")) {
                GeneratorManager.createGenerator(GeneratorType.DIAMOND, playerLocation);
                
            } else if (args[0].equalsIgnoreCase("emerald")) {
                GeneratorManager.createGenerator(GeneratorType.EMERALD, playerLocation);
                
            } else if (args[0].equalsIgnoreCase("amethyst")) {
                GeneratorManager.createGenerator(GeneratorType.AMETHYST, playerLocation);
                
            } else if (args[0].equalsIgnoreCase("clear")) {
                System.out.println("clear command sent");
                GeneratorManager.clearAllGenerators(false);
            }
        }
        return true;
    }
    
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
