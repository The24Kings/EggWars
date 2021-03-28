package com.ryan.eggwars.generators;

import com.ryan.eggwars.EggWars;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class MiddleGenerator {
    
    private final GeneratorType generatorType;
    private final ItemStack itemType;
    private final ItemStack displayItem;
    private final int interval;
    private final int intervalSeconds;
    private final Location location;
    private Location dropLocation;
    private final World world;
    private ArmorStand genStand;
    private ArmorStand mainText;
    private ArmorStand secondaryText;
    private boolean stoppedGen = false;
    
    // numbers for calculating the yaw
    private int yawIndex = 0;
    private static final int yawAmountOfIncrements = 100;
    // TODO: might be able to use the same x value since they won't be used at the same time
    private static double yawX = 0;
    private static final double yawPeriod = 2 * Math.PI;
    private static final double yawIncrement = yawPeriod / yawAmountOfIncrements;
    private static final double yawFrequency = 1.25;
    private static final ArrayList<Double> yawMeasurements = new ArrayList<>();
    
    // numbers for calculating the height
    private int heightIndex = 0;
    private static final int heightAmountOfIncrements = 100;
    private static double heightX = 0;
    private static final double heightPeriod = 2 * Math.PI;
    private static final double heightIncrement = heightPeriod / heightAmountOfIncrements;
    private static final double heightFrequency = 1.25;
    private static final double heightRange = 1;
    private double originalHeight;
    private static final ArrayList<Double> heightMeasurements = new ArrayList<>();
    
    public MiddleGenerator(GeneratorType generatorType, int intervalSeconds, Location location) {
        switch (generatorType) {
            case DIAMOND:
                this.itemType = new ItemStack(Material.DIAMOND);
                this.displayItem = new ItemStack(Material.DIAMOND_BLOCK);
                break;
                
            case EMERALD:
                this.itemType = new ItemStack(Material.EMERALD);
                this.displayItem = new ItemStack(Material.EMERALD_BLOCK);
                break;
            case AMETHYST:
                this.itemType = new ItemStack(Material.PURPLE_DYE);
                this.displayItem = new ItemStack(Material.PURPLE_CONCRETE);
                break;
                
            default:
                this.itemType = new ItemStack(Material.STONE);
                this.displayItem = new ItemStack(Material.STONE);
        }
        
        this.generatorType = generatorType;
        this.interval = intervalSeconds * 20;
        this.intervalSeconds = intervalSeconds;
        this.world = location.getWorld();
        this.location = new Location(world,
                location.getBlockX() + 0.5,
                location.getBlockY() + 0.5,
                location.getBlockZ() + 0.5);
        this.dropLocation = new Location(world,
                location.getBlockX() + 0.5,
                location.getBlockY() + 1,
                location.getBlockZ() + 0.5);
    }
    
    /**
     * Spawns the generator at the location specified in the constructor.
     */
    public void spawnGenerator() {
        calculateHeightMeasurements();
        calculateYawMeasurements();
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        
        EntityEquipment entityEquipment = armorStand.getEquipment();
        entityEquipment.setHelmet(new ItemStack(displayItem));
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        genStand = armorStand;
        
        doHoveringEffect(armorStand);
    }
    
    /**
     * Starts generating items.
     */
    public void startGenerator() {
        
        doCountdown();
        
        new BukkitRunnable() {
            
            int i = 0;
            
            @Override
            public void run() {
                if (stoppedGen) cancel();
                
                if (i > interval) i = 0;
                
                if (i == interval) {
                    Item droppedItem = world.dropItem(dropLocation, itemType);
                    droppedItem.setVelocity(new Vector(0, 0, 0));
                }
                i++;
            }
        }.runTaskTimer(EggWars.getPlugin(), 1, 1);
    }
    
    /**
     * Stops the generator.
     */
    public void stopGenerator(boolean isDisabling) {
    
        genStand.remove();
        mainText.remove();
        secondaryText.remove();
    
        // gens will stop on their own
        if (!isDisabling) {
            stoppedGen = true;
            Bukkit.getScheduler().runTaskLater(EggWars.getPlugin(), () -> stoppedGen = false, 20);
        }
    }
    
    private void doCountdown() {
        
        Location textLocation = location;
        textLocation.setY(textLocation.getY() + 3.5);
        mainText = (ArmorStand) world.spawnEntity(textLocation, EntityType.ARMOR_STAND);
        
        textLocation.setY(textLocation.getY() - 0.3);
        secondaryText = (ArmorStand) world.spawnEntity(textLocation, EntityType.ARMOR_STAND);
        
        mainText.setCustomNameVisible(true);
        mainText.setMarker(true);
        mainText.setGravity(false);
        mainText.setVisible(false);
        
        secondaryText.setCustomNameVisible(true);
        secondaryText.setMarker(true);
        secondaryText.setGravity(false);
        secondaryText.setVisible(false);
        
        switch (generatorType) {
            case DIAMOND:
                mainText.setCustomName(ChatColor.AQUA + "Diamond Generator");
                break;
            
            case EMERALD:
                mainText.setCustomName(ChatColor.GREEN + "Emerald Generator");
                break;
                
            case AMETHYST:
                mainText.setCustomName(ChatColor.LIGHT_PURPLE + "Amethyst Generator");
                break;
                
            default:
                mainText.setCustomName("something went wrong");
        }
        
        
        new BukkitRunnable() {
            
            int timeRemaining = 0;
    
            @Override
            public void run() {
                if (stoppedGen) {
                    mainText.remove();
                    secondaryText.remove();
                    cancel();
                }
                
                if (timeRemaining == 0) {
                    timeRemaining = intervalSeconds;
                }
                
                secondaryText.setCustomName(ChatColor.YELLOW + "Time Remaining: " + timeRemaining);
                
                timeRemaining--;
            }
        }.runTaskTimer(EggWars.getPlugin(), 0, 20);
    }
    
    /**
     * Makes the generator begin to move.
     *
     * @param armorStand The armor stand to move.
     */
    private void doHoveringEffect(ArmorStand armorStand) {
        
        Location armorStandLocation = armorStand.getLocation();
        originalHeight = armorStandLocation.getY() + 1;
        
        new BukkitRunnable() {
            
            @Override
            public void run() {
                if (stoppedGen) cancel();
                
                armorStandLocation.setYaw(yawMeasurements.get(yawIndex).floatValue());
                armorStandLocation.setY(heightMeasurements.get(heightIndex).floatValue() + originalHeight);
                armorStand.teleport(armorStandLocation);
                
                yawIndex++;
                if (yawIndex >= yawAmountOfIncrements / yawFrequency) yawIndex = 0;
                heightIndex++;
                if (heightIndex >= heightAmountOfIncrements / heightFrequency) heightIndex = 0;
            }
        }.runTaskTimer(EggWars.getPlugin(), 0, 1);
    }
    
    /**
     * Runs the calculations for the rotation of the generator.
     */
    private static void calculateYawMeasurements() {
        for (int i = 0; i < yawAmountOfIncrements / yawFrequency; i++) {
            
            double y = 0.5 * Math.sin(yawFrequency * yawX) + 0.5;
            yawMeasurements.add(y * 360);
            
            yawX += yawIncrement;
        }
    }
    
    /**
     * Runs the calculations for the height movement of the generator.
     */
    private static void calculateHeightMeasurements() {
        for (int i = 0; i < heightAmountOfIncrements / heightFrequency; i++) {
            
            double y = heightRange / 2 * Math.sin(heightFrequency * heightX) / 2;
            heightMeasurements.add(y);
            
            heightX += heightIncrement;
        }
    }
}
