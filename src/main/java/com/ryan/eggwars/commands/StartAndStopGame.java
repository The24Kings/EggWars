package com.ryan.eggwars.commands;

import com.ryan.eggwars.gameplay.Cleanup;
import com.ryan.eggwars.gameplay.Setup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StartAndStopGame implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (label.equalsIgnoreCase("eggwars")) {
            if (!(sender instanceof Player)) return true;

            if (args[0] == null) sender.sendMessage("You can only start or stop the game!!");

            if (args[0].equalsIgnoreCase("start")) {
                Setup.onGameStart();
            } else if (args[0].equalsIgnoreCase("stop")) {
                Cleanup.onGameEnd();
            }
        }
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> parameters = new ArrayList<>();

        if(args.length == 1) {
            parameters.add("start");
            parameters.add("stop");
        }

        return parameters;
    }
}
