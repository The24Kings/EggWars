package com.ryan.eggwars;

import com.ryan.eggwars.commands.*;
import com.ryan.eggwars.gameplay.Cleanup;
import com.ryan.eggwars.gameplay.Setup;
import com.ryan.eggwars.listeners.ChatFormatter;
import com.ryan.eggwars.listeners.EggEvents;
import com.ryan.eggwars.oldmechanics.AttackCooldown;
import com.ryan.eggwars.oldmechanics.CancelDroppingEgg;
import com.ryan.eggwars.oldmechanics.DisableOffhand;
import com.ryan.eggwars.oldmechanics.PearlCooldown;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class EggWars extends JavaPlugin {
    
    //public static World world = Bukkit.getWorld(UUID.fromString("00b6692e-8014-431b-8a3d-e5acf57be968"));
    public static World world;
    
    private static EggWars plugin;
    
    public static EggWars getPlugin() {
        return plugin;
    }
    
    @Override
    public void onEnable() {
    
        plugin = this;
        world = Bukkit.getWorld("eggwars");
    
        if (world == null) {
            System.out.println(ChatColor.RED + "world is null");
        }
        
        registerEventsAndCommands();
        TeamManager.addTeamsToList();
        Setup.onPluginStart();
    }
    
    @Override
    public void onDisable() {
        Cleanup.onPluginStop();
    }
    
    private void registerEventsAndCommands() {
        
        getServer().getPluginManager().registerEvents(new AttackCooldown(), this);
        getServer().getPluginManager().registerEvents(new PearlCooldown(), this);
        getServer().getPluginManager().registerEvents(new DisableOffhand(), this);
        getServer().getPluginManager().registerEvents(new CancelDroppingEgg(), this);
        getServer().getPluginManager().registerEvents(new Testing(), this);
        getServer().getPluginManager().registerEvents(new ChatFormatter(), this);
        getServer().getPluginManager().registerEvents(new EggEvents(), this);
        
        getCommand("join").setExecutor(new JoinTeam());
        getCommand("join").setTabCompleter(new JoinTeamTabCompleter());
        
        getCommand("leave").setExecutor(new LeaveTeam());
        
        getCommand("gen").setExecutor(new CreateGenerator());
        getCommand("gen").setTabCompleter(new CreateGeneratorTabCompleter());
        
        getCommand("eggtool").setExecutor(new EggTools());
        getCommand("eggtool").setTabCompleter(new EggTools());
        
        getCommand("eggwars").setExecutor(new StartAndStopGame());
    }
    
}