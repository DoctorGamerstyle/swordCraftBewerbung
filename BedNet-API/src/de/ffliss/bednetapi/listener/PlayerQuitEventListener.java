package de.ffliss.bednetapi.listener;

import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventListener implements Listener {

    public PlayerQuitEventListener() {
        BedNetAPI.getInstance().getServer().getPluginManager().registerEvents(this, BedNetAPI.getInstance());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        BedNetAPI.getInstance().coinManagers.remove(player.getUniqueId().toString());
        event.setQuitMessage("");
    }
}
