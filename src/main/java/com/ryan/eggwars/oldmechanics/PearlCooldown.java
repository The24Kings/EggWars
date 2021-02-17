package com.ryan.eggwars.oldmechanics;

import io.papermc.paper.event.player.PlayerItemCooldownEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PearlCooldown implements Listener {

    @EventHandler
    public void onCooldown(PlayerItemCooldownEvent event) {
        if (event.getType() == Material.ENDER_PEARL) event.setCooldown(0);
    }
}
