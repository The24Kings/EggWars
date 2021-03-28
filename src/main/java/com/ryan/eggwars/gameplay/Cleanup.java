package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.generators.GeneratorManager;

public class Cleanup {
    
    public static void onPluginStop() {
        Setup.clearEggs();
        GeneratorManager.clearAllGenerators(true);
    }
    
    public static void onGameEnd() {
        Setup.clearEggs();
        GeneratorManager.clearAllGenerators(false);
    }
}
