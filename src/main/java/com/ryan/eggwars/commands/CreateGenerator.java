package com.ryan.eggwars.commands;

import com.ryan.eggwars.generators.GeneratorManager;
import com.ryan.eggwars.generators.GeneratorType;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGenerator implements CommandExecutor {
    
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
}
