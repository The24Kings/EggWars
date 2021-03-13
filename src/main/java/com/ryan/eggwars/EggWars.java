package com.ryan.eggwars;

import com.ryan.eggwars.commands.*;
import com.ryan.eggwars.listeners.ChatFormatter;
import com.ryan.eggwars.oldmechanics.AttackCooldown;
import com.ryan.eggwars.oldmechanics.PearlCooldown;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EggWars extends JavaPlugin {
    
    private static EggWars plugin;
    
    public static EggWars getPlugin() {
        return plugin;
    }
    
    @Override
    public void onEnable() {
        
        registerEvents();
        TeamManager.addTeamsToList();
        
        plugin = this;
    }
    
    @Override
    public void onDisable() {
    }
    
    private void registerEvents() {
        
        getServer().getPluginManager().registerEvents(new AttackCooldown(), this);
        getServer().getPluginManager().registerEvents(new PearlCooldown(), this);
        getServer().getPluginManager().registerEvents(new Testing(), this);
        getServer().getPluginManager().registerEvents(new ChatFormatter(), this);
        
        getCommand("join").setExecutor(new JoinTeam());
        getCommand("join").setTabCompleter(new JoinTeamTabCompleter());
        
        getCommand("leave").setExecutor(new LeaveTeam());
        
        getCommand("gen").setExecutor(new CreateGenerator());
        getCommand("gen").setTabCompleter(new CreateGeneratorTabCompleter());
        
        getCommand("eggtool").setExecutor(new RandomTools());
        getCommand("eggtool").setTabCompleter(new RandomTools());
    }
    
}