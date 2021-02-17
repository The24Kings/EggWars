package com.ryan.eggwars;

import com.ryan.eggwars.chat.ChatFormatter;
import com.ryan.eggwars.oldmechanics.AttackCooldown;
import com.ryan.eggwars.oldmechanics.PearlCooldown;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Eggwars extends JavaPlugin {
    
    private static Eggwars plugin;
    
    public static Eggwars getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AttackCooldown(), this);
        getServer().getPluginManager().registerEvents(new PearlCooldown(), this);
        getServer().getPluginManager().registerEvents(new Testing(), this);
        getServer().getPluginManager().registerEvents(new ChatFormatter(), this);
        
        TeamManager.addTeamsToList();
        
        plugin = this;
    }
    
    @Override
    public void onDisable() {
    }
    
    
    
}