package com.ryan.eggwars.chat;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        System.out.println(event.getFormat());
        
        ChatColor nameColor;
        Team team = TeamManager.getTeam(event.getPlayer());
        
        if (team != null) {
            nameColor = team.getChatColor();
        } else {
            nameColor = ChatColor.GRAY;
        }
        
        // TODO: make the color of the name represent their team's color
        // Makes name colored, chat message white
        event.setFormat(nameColor + "--%1$s-- " + ChatColor.WHITE + "%2$s");
    }
    
}
