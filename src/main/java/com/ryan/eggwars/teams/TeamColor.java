package com.ryan.eggwars.teams;

import org.bukkit.ChatColor;

public enum TeamColor {
    
    RED(ChatColor.RED),
    YELLOW(ChatColor.YELLOW),
    GREEN(ChatColor.GREEN),
    BLUE(ChatColor.BLUE);
    
    private final ChatColor chatColor;
    
    TeamColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }
    
    public ChatColor getChatColor() {
        return chatColor;
    }
}
