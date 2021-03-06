package com.ryan.eggwars.generators;

import com.ryan.eggwars.EggWars;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class IslandGenerator {
    
    /**
     * Starts generating items.
     */
    public void startGenerator() {
        Random random = new Random();
        final int[] i = {0};
        
        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println(i[0]);
                i[0]++;
            }
        }.runTaskTimer(EggWars.getPlugin(), 0, random.nextInt(100));
    }
}
