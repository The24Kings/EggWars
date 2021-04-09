package com.ryan.eggwars.listeners;

import com.ryan.eggwars.gameplay.CaptureEgg;
import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EggEvents implements Listener {
    
    @EventHandler
    public static void onEggPunch(PlayerInteractEvent event) {
        if (!event.hasBlock()) return;
        
        if (event.getClickedBlock().getType() == Material.DRAGON_EGG) {
            event.setCancelled(true);
            CaptureEgg.handleEggWasPunched(event.getPlayer(), event.getClickedBlock());
        }
    }
    
    @EventHandler
    public static void onEggPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() == Material.DRAGON_EGG) {
            
            Team eggTeam = CaptureEgg.pickedUpEggs.get(event.getPlayer());
            Player player = event.getPlayer();
            Team playerTeam = TeamManager.getTeam(player);
            Material blockPlacedOn = event.getBlockAgainst().getType();
            
            if (eggTeam == null || playerTeam == null) {
                event.setCancelled(true);
                return;
            }
    
            // if its not placed on the correct team's wool or not on a nest
            if (!(blockPlacedOn == eggTeam.getWool()) || !playerTeam.nestLocations.contains(event.getBlockPlaced().getLocation())) {
                player.sendMessage(ChatColor.RED + "Place this egg in the correct spot in your nest!");
                event.setCancelled(true);
                
                boolean first = blockPlacedOn == eggTeam.getWool();
                boolean second = playerTeam.nestLocations.contains(event.getBlockPlaced().getLocation());
                
                player.sendMessage("first condition = " + first);
                player.sendMessage("second condition = " + second);
                return;
                
            } else {
                player.sendMessage("in correct spot");
            }
    
            CaptureEgg.handleEggWasPlaced(player, blockPlacedOn, event.getBlockPlaced(), eggTeam);
        }
    }
}
