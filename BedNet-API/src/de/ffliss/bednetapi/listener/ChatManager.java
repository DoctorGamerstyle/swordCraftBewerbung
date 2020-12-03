package de.ffliss.bednetapi.listener;

import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {

    public ChatManager() {
        BedNetAPI.getInstance().getServer().getPluginManager().registerEvents(this, BedNetAPI.getInstance());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String message = event.getMessage();
        event.setFormat(event.getPlayer().getDisplayName() + "§8> §7" + message);
    }
}
