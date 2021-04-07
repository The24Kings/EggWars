package com.ryan.eggwars.listeners;

import com.ryan.eggwars.gameplay.CaptureEgg;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnEggPunch implements Listener {
    
    @EventHandler
    public static void onEggPunch(PlayerInteractEvent event) {
        if (!event.hasBlock()) return;
        
        if (event.getClickedBlock().getType() == Material.DRAGON_EGG) {
            event.setCancelled(true);
            CaptureEgg.handleEggWasPunched(event.getPlayer(), event.getClickedBlock());
        }
    }
    
//    @EventHandler
//    public static void onEggTeleport(BlockFromToEvent event) {
//        if (event.getBlock().getType() == Material.DRAGON_EGG) {
//            event.setCancelled(true);
//        }
//    }
}
