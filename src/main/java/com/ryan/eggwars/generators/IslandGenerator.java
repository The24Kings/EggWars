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
    
    private boolean stoppedGen = false;
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
    public void startGenerator() {
        
        Random random = new Random();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (stoppedGen) cancel();
                
                if (random.nextInt(4) == 1) {
                    Item droppedItem = world.dropItem(location, new ItemStack(Material.IRON_INGOT));
                    droppedItem.setVelocity(new Vector(0, 0, 0));
                }
                
                if (random.nextInt(15) == 1) {
                    Item droppedItem = world.dropItem(location, new ItemStack(Material.GOLD_INGOT));
                    droppedItem.setVelocity(new Vector(0, 0, 0));
                }
            }
        }.runTaskTimer(EggWars.getPlugin(), 10, 10);
    }
    
    /**
     * Stops the generator.
     */
    public void stopGenerator(boolean isDisabling) {
        if (isDisabling) return;
        stoppedGen = true;
        Bukkit.getScheduler().runTaskLater(EggWars.getPlugin(), () -> stoppedGen = false, 10);
    }
}
