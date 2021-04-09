package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.EggWars;
import com.ryan.eggwars.generators.GeneratorManager;
import com.ryan.eggwars.generators.GeneratorType;
import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.Location;

import java.util.ArrayList;

public class Setup {
    
    private static final ArrayList<Location> diamondGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> emeraldGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> amethystGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> islandGenSpawnpoints = new ArrayList<>();
    
    public static void onPluginStart() {
        createGeneratorSpawns();
        createNestLocations();
    }
    
    private static void createNestLocations() {
        TeamManager.red.nestLocations.add(new Location(EggWars.world, -60, 105, 0));
        TeamManager.red.nestLocations.add(new Location(EggWars.world, -62, 105, -1));
        TeamManager.red.nestLocations.add(new Location(EggWars.world, -62, 105, 1));
    
        TeamManager.blue.nestLocations.add(new Location(EggWars.world, 0, 105, -60));
        TeamManager.blue.nestLocations.add(new Location(EggWars.world, -1, 105, -62));
        TeamManager.blue.nestLocations.add(new Location(EggWars.world, 1, 105, -62));
    
        TeamManager.yellow.nestLocations.add(new Location(EggWars.world, 0, 105, 60));
        TeamManager.yellow.nestLocations.add(new Location(EggWars.world, -1, 105, 62));
        TeamManager.yellow.nestLocations.add(new Location(EggWars.world, 1, 105, 62));
    
        TeamManager.green.nestLocations.add(new Location(EggWars.world, 60, 105, 0));
        TeamManager.green.nestLocations.add(new Location(EggWars.world, 62, 105, 1));
        TeamManager.green.nestLocations.add(new Location(EggWars.world, 62, 105, -1));
    }
    
    public static void onGameStart() {
        spawnGenerators();
        spawnEggs();
        GeneratorManager.startGenerators();
    }
    
    private static void spawnEggs() {
        for (Team team : TeamManager.teams) {
            team.spawnEgg();
        }
    }
    
    public static void clearEggs() {
        if (TeamManager.teams.isEmpty()) return;
        for (Team team : TeamManager.teams) {
            team.despawnEgg();
        }
    
        for (Team team: TeamManager.teams) {
            team.clearNest();
        }
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
        
        for (Location location : islandGenSpawnpoints) {
            GeneratorManager.createGenerator(GeneratorType.ISLAND, location);
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
        
        islandGenSpawnpoints.add(new Location(EggWars.world, 0, 101, 55));
        islandGenSpawnpoints.add(new Location(EggWars.world, 0, 101, -55));
        islandGenSpawnpoints.add(new Location(EggWars.world, 55, 101, 0));
        islandGenSpawnpoints.add(new Location(EggWars.world, -55, 101, 0));
    }
}
