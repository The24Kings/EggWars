package com.ryan.eggwars.generators;

import org.bukkit.Location;

import java.util.ArrayList;

//TODO add protection around gens
//TODO add cap to amount resources at a given gen
public class GeneratorManager {
    
    private static final ArrayList<IslandGenerator> islandGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> diamondGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> emeraldGenerators = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> amethystGenerator = new ArrayList<>();
    private static final ArrayList<MiddleGenerator> middleGenerators = new ArrayList<>();
    
    /**
     * Creates a new generator and adds it to the corresponding list in {@link GeneratorManager}.
     *
     * @param generatorType The {@link GeneratorType} of the new generator.
     * @param location      The location that this generator will spawn at.
     */
    public static void createGenerator(GeneratorType generatorType, Location location) {

        switch (generatorType) {
            case ISLAND -> {
                IslandGenerator islandGen = new IslandGenerator(location);
                islandGenerators.add(islandGen);
            }
            case DIAMOND -> {
                MiddleGenerator diamondGen = new MiddleGenerator(GeneratorType.DIAMOND, 30, location);
                diamondGenerators.add(diamondGen);
                diamondGen.spawnGenerator();
                middleGenerators.add(diamondGen);
            }
            case EMERALD -> {
                MiddleGenerator emeraldGen = new MiddleGenerator(GeneratorType.EMERALD, 60, location);
                emeraldGenerators.add(emeraldGen);
                emeraldGen.spawnGenerator();
                middleGenerators.add(emeraldGen);
            }
            case AMETHYST -> {
                MiddleGenerator amethystGen = new MiddleGenerator(GeneratorType.AMETHYST, 90, location);
                amethystGenerator.add(amethystGen);
                amethystGen.spawnGenerator();
                middleGenerators.add(amethystGen);
            }
        }
    }
    
    public static void startGenerators() {
        for (MiddleGenerator generator : middleGenerators) {
            generator.startGenerator();
        }
        
        IslandGenerator.startGenerators();
    }
    
    /**
     * Destroy all existing generators.
     */
    public static void clearAllGenerators(boolean isDisabling) {
        
        for (MiddleGenerator gen : middleGenerators) {
            gen.stopGenerator(isDisabling);
        }
        
        IslandGenerator.stopGenerators(isDisabling);
        
        middleGenerators.clear();
        diamondGenerators.clear();
        emeraldGenerators.clear();
        amethystGenerator.clear();
        islandGenerators.clear();
    }
    
    /**
     * Gets all the {@link MiddleGenerator MiddleGenerators}.
     *
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
     *
     * @return An {@link ArrayList} of all the IslandGenerators
     */
    public static ArrayList<IslandGenerator> getIslandGenerators() {
        return islandGenerators;
    }
}
