package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.EggWars;
import com.ryan.eggwars.generators.GeneratorManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Cleanup {
    
    public static void onPluginStop() {
        Setup.clearEggs();
        GeneratorManager.clearAllGenerators(true);
        clearItems();
    }
    
    public static void onGameEnd() {
        Setup.clearEggs();
        GeneratorManager.clearAllGenerators(false);
        CaptureEgg.capturedEggs.clear();
        CaptureEgg.pickedUpEggs.clear();
        clearItems();
    }
    
    public static void clearItems() {
        for (Entity entity : EggWars.world.getEntities()) {
            if (entity.getType() == EntityType.DROPPED_ITEM) {
                entity.remove();
            }
        }
    }
}
