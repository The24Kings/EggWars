package com.ryan.eggwars.commands;

import com.ryan.eggwars.gameplay.Cleanup;
import com.ryan.eggwars.gameplay.Setup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartAndStopGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (label.equalsIgnoreCase("eggwars") && args[0] != null) {
            if (args[0].equalsIgnoreCase("start")) {
                Setup.onGameStart();
            } else if (args[0].equalsIgnoreCase("stop")) {
                Cleanup.onGameEnd();
            }
        }
        
        return true;
    }
}
