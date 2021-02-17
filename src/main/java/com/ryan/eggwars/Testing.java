package com.ryan.eggwars;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Testing implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        World world = event.getPlayer().getWorld();
//        world.dropItemNaturally(event.getPlayer().getLocation(), new ItemStack(Material.DIAMOND_CHESTPLATE));
    }
    
}
