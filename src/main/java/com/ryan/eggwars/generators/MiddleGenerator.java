package com.ryan.eggwars.generators;

import com.ryan.eggwars.EggWars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class MiddleGenerator {
    
    private final ItemStack itemType;
    private final Material displayItem;
    private final int interval;
    private final Location location;
    private final World world;
    private ArmorStand genStand;
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
    
    public MiddleGenerator(Material itemType, Material displayItem, int interval, Location location) {
        this.itemType = new ItemStack(itemType);
        this.interval = interval * 20;
        this.world = location.getWorld();
        this.displayItem = displayItem;
        Location shiftedLocation = new Location(world,
                location.getBlockX() + 0.5,
                location.getBlockY(),
                location.getBlockZ() + 0.5);
        this.location = shiftedLocation;
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
        
        new BukkitRunnable() {
            @Override
            public void run() {
                if (stoppedGen) {
                    stoppedGen = false;
                    cancel();
                }
    
                Item droppedItem = world.dropItem(location, itemType);
                droppedItem.setVelocity(new Vector(0, 0, 0));
            }
        }.runTaskTimer(EggWars.getPlugin(), interval, interval);
    }
    
    /**
     * Stops the generator.
     */
    public void stopGenerator() {
        System.out.println("generator stopped");
        stoppedGen = true;
        genStand.remove();
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
