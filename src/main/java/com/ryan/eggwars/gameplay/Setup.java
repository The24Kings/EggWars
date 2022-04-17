package com.ryan.eggwars.gameplay;

import com.ryan.eggwars.EggWars;
import com.ryan.eggwars.generators.GeneratorManager;
import com.ryan.eggwars.generators.GeneratorType;
import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class Setup {

    //TODO make the gens dynamic through configs
    private static final ArrayList<Location> diamondGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> emeraldGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> amethystGenSpawnpoints = new ArrayList<>();
    private static final ArrayList<Location> islandGenSpawnpoints = new ArrayList<>();

    public static ArrayList<Location> nestLocations = new ArrayList<>();
    public static ArrayList<Location> shopLocations = new ArrayList<>();
    
    public static void onPluginStart() {
        createGeneratorSpawns();
        createNestLocations();
    }
    
    private static void createNestLocations() {
        //RED
        nestLocations.add(new Location(EggWars.world, -60, 104, 0));
        nestLocations.add(new Location(EggWars.world, -62, 104, -1));
        nestLocations.add(new Location(EggWars.world, -62, 104, 1));

        //BLUE
        nestLocations.add(new Location(EggWars.world, 0, 104, -60));
        nestLocations.add(new Location(EggWars.world, -1, 104, -62));
        nestLocations.add(new Location(EggWars.world, 1, 104, -62));

        //YELLOW
        nestLocations.add(new Location(EggWars.world, 0, 104, 60));
        nestLocations.add(new Location(EggWars.world, -1, 104, 62));
        nestLocations.add(new Location(EggWars.world, 1, 104, 62));

        //YELLOW
        nestLocations.add(new Location(EggWars.world, 60, 104, 0));
        nestLocations.add(new Location(EggWars.world, 62, 104, 1));
        nestLocations.add(new Location(EggWars.world, 62, 104, -1));
    }

    public static void createShopLocations() {
        //RED
        shopLocations.add(new Location(EggWars.world, -50, 101, -5));

        //BLUE
        shopLocations.add(new Location(EggWars.world, 5, 101, -49));

        //YELLOW
        shopLocations.add(new Location(EggWars.world, -5, 101, 50));

        //GREEN
        shopLocations.add(new Location(EggWars.world, 50, 101, 5));
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

    /**
     * Removes all active eggs on the map
     */
    public static void clearEggs() {
        if (TeamManager.teams.isEmpty()) return;

        for (Team team : TeamManager.teams) {
            team.despawnEgg();
        }

        for (Location location : nestLocations) {
            location.add(0,1, 0);
            if (location.getBlock().getType() == Material.DRAGON_EGG) {
                location.getBlock().setType(Material.AIR);
            }
            location.subtract(0, 1, 0);
        }
    }

    /**
     * Spawns item shops on all islands
     */
    public void spawnShop() {

    }

    /**
     * Kills all item shop villagers
     */
    public void clearShops() {

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
