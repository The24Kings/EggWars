package com.ryan.eggwars.teams;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
    
    /**
     * Returns the {@link TeamColor} of the team.
     *
     * @return The {@link TeamColor} of the team.
     */
    public TeamColor getTeamColor() {
        return teamColor;
    }
    
    /**
     * Returns the {@link ChatColor} of the team.
     *
     * @return The {@link TeamColor} of the team.
     */
    public ChatColor getChatColor() {
        return teamColor.getChatColor();
    }
    
    /**
     * Returns the team's corresponding wool color
     * @return The {@link Material} of the corresponding wool.
     */
    public Material getWool() {
        switch (teamColor) {
            case RED:
                return Material.RED_WOOL;
            case BLUE:
                return Material.BLUE_WOOL;
            case GREEN:
                return Material.LIME_WOOL;
            case YELLOW:
                return Material.YELLOW_WOOL;
            default:
                return null;
        }
    }
    
    /**
     * Returns the name of the team.
     *
     * @return The name of the team.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the name of the team with the {@link ChatColor} added.
     *
     * @return The colored named of the team.
     */
    public String getColoredName() {
        return teamColor.getChatColor() + name;
    }
    
    /**
     * Returns all the players on the team.
     *
     * @return List of the UUIDs of the players on the team.
     */
    public ArrayList<UUID> getPlayers() {
        return players;
    }
    
    /**
     * Checks if this team has any players on it.
     *
     * @return True if the team has players on it.
     */
    public boolean hasPlayers() {
        return !players.isEmpty();
    }
    
    /**
     * Checks if a certain player is on the team.
     *
     * @param player The player to check.
     * @return True if the player is on the team.
     */
    public boolean containsPlayer(Player player) {
        return players.contains(player.getUniqueId());
    }
    
    /**
     * Adds the specified player to the team. You should probably be using {@link TeamManager#joinTeam(Player, TeamColor) TeamManager.joinTeam}.
     *
     * @param player The player to add to the team.
     */
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
    }
    
    /**
     * Removes the specified player from the team. You should probably be using {@link TeamManager#leaveTeam(Player) TeamManager.leaveTeam}.
     *
     * @param player The player to remove from the team.
     */
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }
}


