package com.ryan.eggwars.teams;

import com.ryan.eggwars.EggWars;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamManager {
    
    public static final ArrayList<Team> teams = new ArrayList<>();
    public static Team red = new Team("Red", TeamColor.RED, new Location(EggWars.world, -41, 101, 0));
    public static Team yellow = new Team("Yellow", TeamColor.YELLOW, new Location(EggWars.world, 0, 101, 41));
    public static Team green = new Team("Green", TeamColor.GREEN, new Location(EggWars.world, 41, 101, 0));
    public static Team blue = new Team("Blue", TeamColor.BLUE, new Location(EggWars.world, 0, 101, -41));
    
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
        
        Component name = Component.text(teamColor.getChatColor() + player.getName());
        player.displayName(name);
        player.playerListName(name);
        player.sendMessage("You have joined the " + TeamManager.getTeam(player).getBoldedName() + ChatColor.RESET + " team.");
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
        
//        TeamColor teamColor = team.getTeamColor();
//
//        switch (teamColor) {
//            case RED:
//                red.removePlayer(player);
//                break;
//
//            case YELLOW:
//                yellow.removePlayer(player);
//                break;
//
//            case GREEN:
//                green.removePlayer(player);
//                break;
//
//            case BLUE:
//                blue.removePlayer(player);
//                break;
//        }
        
        team.removePlayer(player);
        
        Component name = Component.text(player.getName());
        player.displayName(name);
        player.playerListName(name);
        player.sendMessage("Removed you from the " + team.getBoldedName() + ChatColor.RESET + " team.");
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
                return team;
            }
        }
        return null;
    }
    
    public static Team getTeam(TeamColor teamColor) {
        switch (teamColor) {
            case RED:
                return red;
            case BLUE:
                return blue;
            case GREEN:
                return green;
            case YELLOW:
                return yellow;
            default:
                return null;
        }
    }
    
    public static Team getTeam(Material wool) {
        switch (wool) {
            case RED_WOOL:
                return red;
            case BLUE_WOOL:
                return blue;
            case LIME_WOOL:
                return green;
            case YELLOW_WOOL:
                return yellow;
            default:
                return null;
        }
    }
    
    public static void addTeamsToList() {
        teams.add(red);
        teams.add(yellow);
        teams.add(green);
        teams.add(blue);
    }
}
