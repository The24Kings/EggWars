package com.ryan.eggwars.teams;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Team {
    
    public ArrayList<Location> nestLocations = new ArrayList<>();
    private final Location eggSpawn;
    private final String name;
    private final TeamColor teamColor;
    private final ArrayList<UUID> players = new ArrayList<>();
    private Team lastSeenEggLocation = this;
    
    public Team(String name, TeamColor teamColor, Location eggSpawn) {
        this.name = name;
        this.teamColor = teamColor;
        this.eggSpawn = eggSpawn;
    }
    
    /**
     * Returns the teams corresponding {@link TeamColor}.
     *
     * @return The {@link TeamColor} of the team.
     */
    public TeamColor getTeamColor() {
        return teamColor;
    }
    
    /**
     * Returns the team's corresponding {@link ChatColor}.
     *
     * @return The {@link ChatColor} of the team.
     */
    public ChatColor getChatColor() {
        return teamColor.getChatColor();
    }
    
    /**
     * Returns the team's corresponding {@link TextColor}.
     *
     * @return The {@link TextColor} of the team.
     */
    public TextColor getTextColor() {
        switch (teamColor) {
            case RED:
                return TextColor.color(232, 56, 56);
            case BLUE:
                return TextColor.color(58, 124, 240);
            case GREEN:
                return TextColor.color(70, 224, 70);
            case YELLOW:
                return TextColor.color(224, 224, 9);
            default:
                return TextColor.color(0, 0, 0);
        }
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
     * Returns the name of the team with the {@link ChatColor} and bold added.
     *
     * @return The colored named of the team.
     */
    public String getBoldedName() {
        return teamColor.getChatColor() + "" + ChatColor.BOLD + name;
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
    
    /**
     * Sets the {@link Team} that last had this teams egg.
     * @param team The {@link Team} that last had the egg.
     */
    public void setLastSeenEggLocation(Team team) {
        lastSeenEggLocation = team;
    }
    
    /**
     * Gets the {@link Team} that last had the egg.
     * @return The {@link Team} that last had the egg.
     */
    public Team getLastSeenEggLocation() {
        return lastSeenEggLocation;
    }
    
    /**
     * Gets the original spawn location of this team's egg.
     * @return The original {@link Location} of this teams egg.
     */
    public Location getEggSpawn() {
        return eggSpawn;
    }
    
    /**
     * Spawns this team's egg.
     */
    public void spawnEgg() {
        eggSpawn.getBlock().setType(Material.DRAGON_EGG);
    }
    
    /**
     * Despawns this team's egg
     */
    public void despawnEgg() {
        eggSpawn.getBlock().setType(Material.AIR);
    }
    
    public void clearNest() {
        for (Location location : nestLocations) {
            if (location.getBlock().getType() == Material.DRAGON_EGG) {
                location.getBlock().setType(Material.AIR);
            }
        }
    }
}


