package com.ryan.eggwars.teams;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Team {
    
    private final String name;
    private final TeamColor teamColor;
    private final ArrayList<UUID> players = new ArrayList<>();
    
    public Team(String name, TeamColor teamColor) {
        this.name = name;
        this.teamColor = teamColor;
    }
    
    public TeamColor getTeamColor() {
        return teamColor;
    }
    
    public ChatColor getChatColor() {
        return teamColor.getChatColor();
    }
    
    public String getName() {
        return name;
    }
    
    public String getColoredName() {
        return teamColor.getChatColor() + name;
    }
    
    public ArrayList<UUID> getPlayers() {
        return players;
    }
    
    public boolean hasPlayers() {
        return !players.isEmpty();
    }
    
    public boolean containsPlayer(Player player) {
        return players.contains(player.getUniqueId());
    }
    
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
    }
    
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }
}


