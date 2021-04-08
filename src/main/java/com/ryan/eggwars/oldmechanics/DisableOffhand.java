package com.ryan.eggwars.oldmechanics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class DisableOffhand implements Listener {
    
    @EventHandler
    public static void onOffhandChange(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
        event.getPlayer().sendMessage("swap cancelled");
    }
}
