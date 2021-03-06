package com.ryan.eggwars.teams;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamManager {
    
    private static final ArrayList<Team> teams = new ArrayList<>();
    public static Team red = new Team("Red", TeamColor.RED);
    public static Team yellow = new Team("Yellow", TeamColor.YELLOW);
    public static Team green = new Team("Green", TeamColor.GREEN);
    public static Team blue = new Team("Blue", TeamColor.BLUE);
    
    /**
     * Adds the specified player to the specified team.
     *
     * @param player    The player the add to the team.
     * @param teamColor The color of the team to add the player to.
     */
    public static void joinTeam(Player player, TeamColor teamColor) {
        if (getTeam(player) != null) {
            player.sendMessage(ChatColor.RED + "Please leave your team before joining a new one!");
            return;
        }
        
        switch (teamColor) {
            case RED:
                red.addPlayer(player);
                break;
            
            case YELLOW:
                yellow.addPlayer(player);
                break;
            
            case GREEN:
                green.addPlayer(player);
                break;
            
            case BLUE:
                blue.addPlayer(player);
                break;
        }
        
        player.setDisplayName(teamColor.getChatColor() + player.getName());
        player.sendMessage("You have joined the " + TeamManager.getTeam(player).getColoredName() + ChatColor.WHITE + " team!");
    }
    
    /**
     * Removes the player from their team.
     *
     * @param player The player to remove from their team.
     */
    public static void leaveTeam(Player player) {
        Team team = TeamManager.getTeam(player);
        
        if (team == null) {
            player.sendMessage(ChatColor.RED + "You are not on a team.");
            return;
        }
        
        TeamColor teamColor = team.getTeamColor();
        
        switch (teamColor) {
            case RED:
                red.removePlayer(player);
                break;
            
            case YELLOW:
                yellow.removePlayer(player);
                break;
            
            case GREEN:
                green.removePlayer(player);
                break;
            
            case BLUE:
                blue.removePlayer(player);
                break;
        }
        
        player.setDisplayName(player.getName());
        player.sendMessage("Removed you from the " + team.getColoredName() + ChatColor.WHITE + " team.");
    }
    
    /**
     * Returns the team that the player is currently on.
     *
     * @param player The player to get check.
     * @return The team that the player is on.
     */
    public static Team getTeam(Player player) {
        
        for (Team team : teams) {
            if (team.containsPlayer(player)) {
                // TODO: do the thing
                return team;
            }
        }
        
        return null;
    }
    
    // TODO: can you add these when the list is created?
    public static void addTeamsToList() {
        teams.add(red);
        teams.add(yellow);
        teams.add(green);
        teams.add(blue);
    }
}
