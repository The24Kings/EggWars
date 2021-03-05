package com.ryan.eggwars.generators;

import com.ryan.eggwars.EggWars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class MiddleGenerator {
    
    private final Material itemType;
    private final int interval;
    private final Location location;
    private final World world;
    
    // numbers for calculating the yaw
    private static int yawIndex = 0;
    private static final int yawAmountOfIncrements = 100;
    // TODO: might be able to use the same x value since they won't be used at the same time
    private static double yawX = 0;
    private static final double yawPeriod = 2 * Math.PI;
    private static final double yawIncrement = yawPeriod / yawAmountOfIncrements;
    private static final double yawFrequency = 1.25;
    private static final ArrayList<Double> yawMeasurements = new ArrayList<>();
    
    // numbers for calculating the height
    private static int heightIndex = 0;
    private static final int heightAmountOfIncrements = 100;
    private static double heightX = 0;
    private static final double heightPeriod = 2 * Math.PI;
    private static final double heightIncrement = heightPeriod / heightAmountOfIncrements;
    private static final double heightFrequency = 1.25;
    private static final double heightRange = 1;
    private static double originalHeight;
    private static final ArrayList<Double> heightMeasurements = new ArrayList<>();
    
    public MiddleGenerator(Material itemType, int interval, Location location, World world) {
        this.itemType = itemType;
        this.interval = interval * 20;
        this.location = location;
        this.world = world;
    }
    
    private void spawnGenerator() {
        calculateHeightMeasurements();
        calculateYawMeasurements();
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        
        EntityEquipment entityEquipment = armorStand.getEquipment();
        entityEquipment.setHelmet(new ItemStack(itemType));
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        
        doHoveringEffect(armorStand);
    }
    
    private void startGenerator() {
        new BukkitRunnable() {
            @Override
            public void run() {
                world.dropItem(location, new ItemStack(itemType));
            }
        }.runTaskTimer(EggWars.getPlugin(), interval, interval);
    }
    
    private void doHoveringEffect(ArmorStand armorStand) {
        
        Location armorStandLocation = armorStand.getLocation();
        originalHeight = armorStandLocation.getY() + 1;
        
        new BukkitRunnable() {
            
            @Override
            public void run() {
                
                armorStandLocation.setYaw(yawMeasurements.get(yawIndex).floatValue());
                armorStandLocation.setY(heightMeasurements.get(heightIndex).floatValue() + originalHeight);
                armorStand.teleport(armorStandLocation);
                
                System.out.println("set height to " + heightMeasurements.get(heightIndex).floatValue() + originalHeight);
                
                yawIndex++;
                if (yawIndex >= yawAmountOfIncrements / yawFrequency) yawIndex = 0;
                heightIndex++;
                if (heightIndex >= heightAmountOfIncrements / heightFrequency) heightIndex = 0;
            }
        }.runTaskTimer(EggWars.getPlugin(), 0, 1);
    }
    
    private static void calculateYawMeasurements() {
        for (int i = 0; i < yawAmountOfIncrements / yawFrequency; i++) {
            
            double y = 0.5 * Math.sin(yawFrequency * yawX) + 0.5;
//            System.out.println(yawX + " " + Math.sin(yawX) * 360);
            yawMeasurements.add(y * 360);
            
            yawX += yawIncrement;
        }
        
        System.out.println("yaw: " + yawMeasurements);
        
    }
    
    private static void calculateHeightMeasurements() {
        for (int i = 0; i < heightAmountOfIncrements / heightFrequency; i++) {
            
            double y = heightRange / 2 * Math.sin(heightFrequency * heightX) / 2;
//            System.out.println(heightX + " " + Math.sin(heightX) * 360);
            heightMeasurements.add(y);
            
            heightX += heightIncrement;
        }
        
        System.out.println("*****************");
        System.out.println("height: " + heightMeasurements);
//        System.out.println(heightMeasurements.size());
    }
}
