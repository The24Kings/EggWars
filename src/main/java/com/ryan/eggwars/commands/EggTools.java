package com.ryan.eggwars.commands;

import com.ryan.eggwars.gameplay.CaptureEgg;
import com.ryan.eggwars.gameplay.Setup;
import org.bukkit.command.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class EggTools implements Listener, TabExecutor {
    
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
            } else if (args[0].equalsIgnoreCase("cleareggs")) {
                Setup.clearEggs();
            } else if (args[0].equalsIgnoreCase("lists")) {
                player.sendMessage("PICKED UP");
                player.sendMessage(CaptureEgg.pickedUpEggs.toString());
                player.sendMessage("CAPTURED");
                player.sendMessage(CaptureEgg.capturedEggs.toString());
            }
        }
        
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> parameters = new ArrayList<>();

        if(args.length == 1) {
            parameters.add("clearitems");
            parameters.add("cleareggs");
            parameters.add("lists");
        }
    
        return parameters;
    }
}
