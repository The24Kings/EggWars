package com.ryan.eggwars.generators;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class GeneratorManager {
    
    private static final ArrayList<MiddleGenerator> diamondGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> emeraldGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> amethystGenerator = new ArrayList<>();
    
    private static final ArrayList<IslandGenerator> islandGenerators = new ArrayList<>();
    
    /**
     * Creates a new generator and adds it to the corresponding list in {@link GeneratorManager}.
     *
     * @param generatorType The {@link GeneratorType} of the new generator.
     * @param location      The location that this generator will spawn at.
     */
    public static void createGenerator(GeneratorType generatorType, Location location) {
        
        switch (generatorType) {
            case ISLAND:
                IslandGenerator islandGen = new IslandGenerator(location);
                islandGenerators.add(islandGen);
                islandGen.startGenerator();
                break;
            case DIAMOND:
                MiddleGenerator diamondGen = new MiddleGenerator(Material.DIAMOND, Material.DIAMOND_BLOCK, 15, location);
                diamondGenerators.add(diamondGen);
                diamondGen.spawnGenerator();
                diamondGen.startGenerator();
                break;
            case EMERALD:
                MiddleGenerator emeraldGen = new MiddleGenerator(Material.EMERALD, Material.EMERALD_BLOCK, 30, location);
                emeraldGenerators.add(emeraldGen);
                emeraldGen.spawnGenerator();
                emeraldGen.startGenerator();
                break;
            case AMETHYST:
                MiddleGenerator amethystGen = new MiddleGenerator(Material.PURPLE_DYE, Material.PURPLE_CONCRETE, 45, location);
                amethystGenerator.add(amethystGen);
                amethystGen.spawnGenerator();
                amethystGen.startGenerator();
                break;
        }
    }
    
    public static void clearAllGenerators() {
        for (MiddleGenerator gen : diamondGenerators) {
            gen.stopGenerator();
        }
    
        for (MiddleGenerator gen : emeraldGenerators) {
            gen.stopGenerator();
        }
        
        for (MiddleGenerator gen : amethystGenerator) {
            gen.stopGenerator();
        }
    
        for (IslandGenerator gen : islandGenerators) {
            gen.stopGenerator();
        }
        
        diamondGenerators.clear();
        emeraldGenerators.clear();
        amethystGenerator.clear();
        islandGenerators.clear();
    }
}
