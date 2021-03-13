package com.ryan.eggwars.listeners;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {
    
    // TODO: Switch to paper's AsyncChatEvent if it ever decides to show up.
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatColor nameColor;
        Team team = TeamManager.getTeam(event.getPlayer());
        
        if (team != null) {
            nameColor = team.getChatColor();
        } else {
            nameColor = ChatColor.GRAY;
        }
        
        event.setFormat("<" + nameColor + "%1$s" +ChatColor.WHITE + "> %2$s");
    }
    
}
