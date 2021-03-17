package com.ryan.eggwars.generators;

import org.bukkit.Location;

import java.util.ArrayList;

public class GeneratorManager {
    
    private static final ArrayList<MiddleGenerator> diamondGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> emeraldGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> amethystGenerator = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> middleGenerators = new ArrayList<>();
    
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
                MiddleGenerator diamondGen = new MiddleGenerator(GeneratorType.DIAMOND, 15, location);
                diamondGenerators.add(diamondGen);
                diamondGen.spawnGenerator();
                middleGenerators.add(diamondGen);
                break;
                
            case EMERALD:
                MiddleGenerator emeraldGen = new MiddleGenerator(GeneratorType.EMERALD, 30, location);
                emeraldGenerators.add(emeraldGen);
                emeraldGen.spawnGenerator();
                middleGenerators.add(emeraldGen);
                break;
                
            case AMETHYST:
                MiddleGenerator amethystGen = new MiddleGenerator(GeneratorType.AMETHYST, 45, location);
                amethystGenerator.add(amethystGen);
                amethystGen.spawnGenerator();
                middleGenerators.add(amethystGen);
                break;
        }
    }
    
    public static void startGenerators() {
        for (MiddleGenerator generator: middleGenerators) {
            generator.startGenerator();
        }
        
        for (IslandGenerator generator : islandGenerators) {
            generator.startGenerator();
        }
    }
    
    /**
     * Destroy all existing generators.
     */
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
    
    /**
     * Gets all the {@link MiddleGenerator MiddleGenerators}.
     * @return An {@link ArrayList} of all the MiddleGenerators
     */
    public static ArrayList<MiddleGenerator> getMiddleGenerators() {
        ArrayList<MiddleGenerator> generators = diamondGenerators;
        generators.addAll(emeraldGenerators);
        generators.addAll(amethystGenerator);
        return generators;
    }
    
    /**
     * Gets all the {@link IslandGenerator IslandGenerators}.
     * @return An {@link ArrayList} of all the IslandGenerators
     */
    public static ArrayList<IslandGenerator> getIslandGenerators() {
        return islandGenerators;
    }
}
