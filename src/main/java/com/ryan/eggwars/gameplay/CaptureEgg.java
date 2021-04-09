package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import com.ryan.eggwars.util.EggUtil;
import com.ryan.eggwars.util.NameUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class CaptureEgg {
    
    /**
     * K: Player who picked up; V: Team whose egg was picked up
     */
    public static final HashMap<Player, Team> pickedUpEggs = new HashMap<>();
    /**
     * K: Team who's egg was captured; V: Who captured the egg
     */
    public static final HashMap<Team, Team> capturedEggs = new HashMap<>();
    
    public static void handleEggWasPunched(Player player, Block egg) {
        
        Team playerTeam = TeamManager.getTeam(player);
        Team eggTeam = EggUtil.getEggTeam(egg);
        
        if (playerTeam == null) {
            player.sendMessage("not on team");
            return;
        }
        
        // the player already has an egg
        if (pickedUpEggs.containsKey(player)) {
            player.sendMessage(ChatColor.RED + "You cannot pick up multiple eggs!");
            return;
        }
    
        // the egg was captured
        if (capturedEggs.containsKey(eggTeam)) {
            pickupOrReturnEgg(player, eggTeam, egg);
            return;
        }
        
        // picking up an egg
        if (eggTeam.getTeamColor() != playerTeam.getTeamColor()) {
            egg.setType(Material.AIR);
            givePlayerEgg(player, eggTeam);
    
            player.getWorld().sendMessage(Component.text(eggTeam.getBoldedName() + ChatColor.RESET +
                    " team's egg was picked up by " + NameUtil.getBoldedName(player) + ChatColor.RESET + "!"));
            
        } else {
            player.sendMessage(ChatColor.RED + "You can't pick up your own egg!");
        }
    }
    
    public static void handleEggWasPlaced(Player player, Material blockPlacedOn, Block egg, Team eggTeam) {
        // adds the location of the egg to a list of eggs and their teams
        
        if (eggTeam.getWool() == blockPlacedOn) {
            player.getWorld().sendMessage(Component.text(eggTeam.getBoldedName() + ChatColor.RESET + " team's egg was captured by " +
                    NameUtil.getBoldedName(player) + ChatColor.RESET + "!"));
            
            // in case its simply moving bases after being captured
            capturedEggs.remove(eggTeam);
            capturedEggs.put(eggTeam, TeamManager.getTeam(player));
            pickedUpEggs.remove(player);
        }
    }
    
    
    
    private static void pickupOrReturnEgg(Player player, Team eggTeam, Block egg) {
        player.sendMessage("picked up a placed egg");
        
        Team playerTeam = TeamManager.getTeam(player);
        
        if (playerTeam == eggTeam) {
            returnEgg(player, eggTeam);
            
        } else {
            // if a team is picking up another team's egg from another team's base
            // Ex: blue captures yellow, red steals yellow's egg from blue
            Team teamWhoCaptured = EggUtil.getWhoCaptured(eggTeam);
            
            if (teamWhoCaptured == playerTeam) {
                player.sendMessage(ChatColor.RED + "You can't pick up an egg you captured!");
                return;
            }
            
            player.getWorld().sendMessage(Component.text(eggTeam.getBoldedName() + ChatColor.RESET +
                    " team's egg was picked up by " + NameUtil.getBoldedName(player) + ChatColor.RESET + " from " +
                    teamWhoCaptured.getBoldedName() + ChatColor.RESET + " team!"));
            
            givePlayerEgg(player, eggTeam);
            clearNestSpot(eggTeam);
        }
    }
    
    private static void returnEgg(Player player, Team eggTeam) {
        Team teamWhoCaptured = capturedEggs.get(eggTeam);
        
        clearNestSpot(eggTeam);
        
        capturedEggs.remove(eggTeam);
        eggTeam.getEggSpawn().getBlock().setType(Material.DRAGON_EGG);
        
        player.getWorld().sendMessage(Component.text(eggTeam.getBoldedName() + ChatColor.RESET +
                " team's egg was returned from " + teamWhoCaptured.getBoldedName() + ChatColor.RESET + " team by " +
                NameUtil.getBoldedName(player) + ChatColor.RESET + "!"));
    }
    
    private static void givePlayerEgg(Player player, Team eggTeam) {
        ItemStack egg = new ItemStack(Material.DRAGON_EGG);
        ItemMeta eggMeta = egg.getItemMeta();
        
        eggMeta.displayName(Component.text(eggTeam.getName() + "'s Egg", eggTeam.getTextColor()));
        egg.setItemMeta(eggMeta);
        player.getInventory().setItem(EquipmentSlot.OFF_HAND, egg);
        pickedUpEggs.put(player, eggTeam);
    }
    
    private static void clearNestSpot(Team eggTeam) {
        Team teamWhoCaptured = capturedEggs.get(eggTeam);
    
        // if the nest location is the correct spot for this teams egg, clear it
        for (Location location : teamWhoCaptured.nestLocations) {
            if (EggUtil.getEggTeam(location) == eggTeam) {
                location.getBlock().setType(Material.AIR);
            }
        }
    }
}
