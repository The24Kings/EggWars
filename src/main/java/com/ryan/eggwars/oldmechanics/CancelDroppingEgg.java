package com.ryan.eggwars.oldmechanics;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class CancelDroppingEgg implements Listener {
    
    @EventHandler
    public static void onEggDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.DRAGON_EGG) event.setCancelled(true);
    }
}
