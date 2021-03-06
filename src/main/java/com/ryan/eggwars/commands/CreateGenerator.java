package com.ryan.eggwars.commands;

import com.ryan.eggwars.generators.GeneratorManager;
import com.ryan.eggwars.generators.GeneratorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGenerator implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("eggwars") && args[0] != null && sender instanceof Player) {
            if (args[0].equalsIgnoreCase("island")) {
                GeneratorManager.createGenerator(GeneratorType.ISLAND, ((Player) sender).getLocation());
                
            } else if (args[0].equalsIgnoreCase("diamond")) {
                GeneratorManager.createGenerator(GeneratorType.DIAMOND, ((Player) sender).getLocation());
                
            } else if (args[0].equalsIgnoreCase("emerald")) {
                GeneratorManager.createGenerator(GeneratorType.EMERALD, ((Player) sender).getLocation());
                
            } else if (args[0].equalsIgnoreCase("amethyst")) {
                GeneratorManager.createGenerator(GeneratorType.AMETHYST, ((Player) sender).getLocation());
                
            }
        }
        return true;
    }
}
