package com.ryan.eggwars.listeners;

import com.ryan.eggwars.teams.Team;
import com.ryan.eggwars.teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatColor nameColor;
        Team team = TeamManager.getTeam(event.getPlayer());

        if (team != null) {
            nameColor = team.getChatColor();
        } else {
            nameColor = ChatColor.GRAY;
        }
        
//        event.composer(this);
        
        event.setFormat("<" + nameColor + "%1$s" +ChatColor.WHITE + "> %2$s");
    }
    
//    @Override
//    public @NotNull Component composeChat(@NotNull Player source, @NotNull Component displayName, @NotNull Component message) {
////        ChatColor nameColor;
//        Team team = TeamManager.getTeam(source);
//
////        if (team != null) {
////            nameColor = team.getChatColor();
////        } else {
////            nameColor = ChatColor.GRAY;
////        }
//
//        return Component.text("<" + displayName.asComponent().children().get(0) + ChatColor.WHITE + "> " + message);
//    }
}
