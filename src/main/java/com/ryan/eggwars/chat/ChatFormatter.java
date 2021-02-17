package com.ryan.eggwars.chat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        System.out.println(event.getFormat());
        
        // TODO: make the color of the name represent their team's color
        // Makes name colored, chat message white
        event.setFormat(ChatColor.RED + "--%1$s-- " + ChatColor.WHITE + "%2$s");
    }
    
}
