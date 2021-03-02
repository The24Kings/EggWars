package com.ryan.eggwars.teams;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamManager {
    
    public static Team red = new Team("Red", TeamColor.RED);
    public static Team yellow = new Team("Yellow", TeamColor.YELLOW);
    public static Team green = new Team("Green", TeamColor.GREEN);
    public static Team blue = new Team("Blue", TeamColor.BLUE);
    private static final ArrayList<Team> teams = new ArrayList<>();
    
    public static void joinTeam(Player player, TeamColor teamColor) {
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
    }
    
    public static void leaveTeam(Player player) {
        TeamColor teamColor = TeamManager.getTeam(player).getTeamColor();
        
        if (teamColor == null) {
            player.sendMessage(ChatColor.RED + "You are not on a team.");
            return;
        }
        
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
        player.sendMessage("Removed you from the " + teamColor + teamColor.name().toLowerCase() + " team.");
    }
    
    public static Team getTeam(Player player) {
        
        for (Team team : teams) {
            if (team.containsPlayer(player)) {
                // TODO: do the thing
                return team;
            }
        }
        
        return null;
    }
    
    public static void addTeamsToList() {
        teams.add(red);
        teams.add(yellow);
        teams.add(green);
        teams.add(blue);
    }
}
