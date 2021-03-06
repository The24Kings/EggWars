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
                IslandGenerator islandGen = new IslandGenerator();
                islandGenerators.add(islandGen);
                break;
            case DIAMOND:
                MiddleGenerator diamondGen = new MiddleGenerator(Material.DIAMOND, 15, location, location.getWorld());
                diamondGenerators.add(diamondGen);
                break;
            case EMERALD:
                MiddleGenerator emeraldGen = new MiddleGenerator(Material.EMERALD, 30, location, location.getWorld());
                emeraldGenerators.add(emeraldGen);
                break;
            case AMETHYST:
                MiddleGenerator amethystGen = new MiddleGenerator(Material.PURPLE_DYE, 45, location, location.getWorld());
                amethystGenerator.add(amethystGen);
                break;
        }
    }
}
