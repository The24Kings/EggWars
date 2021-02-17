package com.ryan.eggwars.oldmechanics;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AttackCooldown implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        setAttackSpeed(event.getPlayer(), 16);
    }
    
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        // resetting so it doesn't cause problems in the future I guess
        setAttackSpeed(event.getPlayer(), 4);
    }
    
    private void setAttackSpeed(Player player, double attackSpeed) {
        // 4 is default speed, 16 should disable
        // https://minecraft.gamepedia.com/Attribute#Attributes_for_players
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attribute == null) return;
        
        double baseValue = attribute.getBaseValue();
        
        if (baseValue != attackSpeed) {
            attribute.setBaseValue(attackSpeed);
            player.saveData();
        }
    }
}
