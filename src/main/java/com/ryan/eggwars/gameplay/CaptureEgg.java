package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import com.ryan.eggwars.util.EggUtil;
import com.ryan.eggwars.util.NameUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CaptureEgg {
    
    private static final HashMap<Player, Team> capturedEggs = new HashMap<>();
    
    // TODO: this
    public static void handleEggWasPunched(Player player, Block egg) {
        
        Team playerTeam = TeamManager.getTeam(player);
        Team eggTeam = EggUtil.getEggTeam(egg);
        
        if (playerTeam == null) {
            player.sendMessage("not on team");
            return;
        }
        
        if (eggTeam.getTeamColor() != playerTeam.getTeamColor()) {
            player.getWorld().sendMessage(Component.text(eggTeam.getBoldedName() + ChatColor.RESET +
                    " team's egg was picked up by " + NameUtil.getBoldedName(player) + ChatColor.RESET + "."));
            
            capturedEggs.put(player, eggTeam);
            givePlayerEgg(player, eggTeam);
            egg.setType(Material.AIR);
            
        } else {
            player.sendMessage(ChatColor.RED + "You can't capture your own egg!");
        }
        
    }
    
    private static void givePlayerEgg(Player player, Team eggTeam) {
        ItemStack egg = new ItemStack(Material.DRAGON_EGG);
        ItemMeta eggMeta = egg.getItemMeta();
        
        eggMeta.displayName(Component.text(eggTeam.getName() + "'s Egg", eggTeam.getTextColor()));
        egg.setItemMeta(eggMeta);
        
        player.getInventory().setItem(EquipmentSlot.OFF_HAND, egg);
    }
}
