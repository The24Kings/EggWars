package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.EggWars;
import com.ryan.eggwars.generators.GeneratorManager;
import com.ryan.eggwars.generators.GeneratorType;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

public class Setup {
    
    private static final ArrayList<Location> diamondGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> emeraldGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> amethystGenSpawnpoints = new ArrayList<>();
    
    private static final ArrayList<Location> eggSpawnpoints = new ArrayList<>();
    
    public static void onPluginStart() {
        createGeneratorSpawns();
        createEggSpawns();
        spawnGenerators();
    }
    
    public static void onGameStart() {
        GeneratorManager.startGenerators();
        spawnEggs();
    }
    
    private static void spawnEggs() {
        for (Location location : eggSpawnpoints) {
            location.getBlock().setType(Material.DRAGON_EGG);
        }
    }
    
    public static void clearEggs() {
        for (Location location : eggSpawnpoints) {
            location.getBlock().setType(Material.AIR);
        }
    }
    
    private static void createEggSpawns() {
        eggSpawnpoints.add(new Location(EggWars.world, 0, 101, 41));
        eggSpawnpoints.add(new Location(EggWars.world, 0, 101, -41));
        eggSpawnpoints.add(new Location(EggWars.world, 41, 101, 0));
        eggSpawnpoints.add(new Location(EggWars.world, -41, 101, 0));
    }
    
    private static void spawnGenerators() {
    
        for (Location location : diamondGenSpawnpoints) {
            GeneratorManager.createGenerator(GeneratorType.DIAMOND, location);
        }
    
        for (Location location : emeraldGenSpawnpoints) {
            GeneratorManager.createGenerator(GeneratorType.EMERALD, location);
        }
    
        for (Location location : amethystGenSpawnpoints) {
            GeneratorManager.createGenerator(GeneratorType.AMETHYST, location);
        }
    }
    
    private static void createGeneratorSpawns() {
        diamondGenSpawnpoints.add(new Location(EggWars.world, 50, 101, 50));
        diamondGenSpawnpoints.add(new Location(EggWars.world, 50, 101, -50));
        diamondGenSpawnpoints.add(new Location(EggWars.world, -50, 101, 50));
        diamondGenSpawnpoints.add(new Location(EggWars.world, -50, 101, -50));
    
    
        emeraldGenSpawnpoints.add(new Location(EggWars.world, 8, 101, 8));
        emeraldGenSpawnpoints.add(new Location(EggWars.world, 8, 101, -8));
        emeraldGenSpawnpoints.add(new Location(EggWars.world, -8, 101, 8));
        emeraldGenSpawnpoints.add(new Location(EggWars.world, -8, 101, -8));
        
        amethystGenSpawnpoints.add(new Location(EggWars.world, 15, 91, 15));
        amethystGenSpawnpoints.add(new Location(EggWars.world, -15, 91, 15));
        amethystGenSpawnpoints.add(new Location(EggWars.world, 15, 91, -15));
        amethystGenSpawnpoints.add(new Location(EggWars.world, -15, 91, -15));
    
    }
}
