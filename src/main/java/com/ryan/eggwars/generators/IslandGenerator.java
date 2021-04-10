package com.ryan.eggwars.generators;

import com.ryan.eggwars.EggWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class IslandGenerator {
    
    // higher = less likely
    private static final int ironChance = 10;
    private static final int goldChance = 25;
    private static boolean stoppedGens = false;
    private final Location location;
    private final World world;
    
    public IslandGenerator(Location location) {
        this.world = location.getWorld();
        this.location = new Location(world,
                location.getBlockX() + 0.5,
                location.getBlockY() + 1,
                location.getBlockZ() + 0.5);
    }
    
    /**
     * Starts generating items.
     */
    public static void startGenerators() {
        
        Random random = new Random();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (stoppedGens) cancel();
                
                if (random.nextInt(ironChance) == 1) {
                    for (IslandGenerator generator : GeneratorManager.getIslandGenerators()) {
                        Item droppedItem = generator.world.dropItem(generator.location, new ItemStack(Material.IRON_INGOT));
                        droppedItem.setVelocity(new Vector(0, 0, 0));
                    }
                }
                
                if (random.nextInt(goldChance) == 1) {
                    for (IslandGenerator generator : GeneratorManager.getIslandGenerators()) {
                        Item droppedItem = generator.world.dropItem(generator.location, new ItemStack(Material.GOLD_INGOT));
                        droppedItem.setVelocity(new Vector(0, 0, 0));
                    }
                }
            }
        }.runTaskTimer(EggWars.getPlugin(), 10, 10);
    }
    
    /**
     * Stops all generators.
     */
    public static void stopGenerators(boolean isDisabling) {
        if (isDisabling) return;
        stoppedGens = true;
        Bukkit.getScheduler().runTaskLater(EggWars.getPlugin(), () -> stoppedGens = false, 10);
    }
}
