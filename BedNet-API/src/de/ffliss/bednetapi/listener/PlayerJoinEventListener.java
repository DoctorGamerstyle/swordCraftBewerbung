package de.ffliss.bednetapi.listener;

import de.dytanic.cloudnet.wrapper.Wrapper;
import de.ffliss.bednetapi.BedNetAPI;
import de.ffliss.bednetapi.functions.CoinManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    public PlayerJoinEventListener() {
        BedNetAPI.getInstance().getServer().getPluginManager().registerEvents(this, BedNetAPI.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        BedNetAPI.getInstance().getColors().setColor(player);
        BedNetAPI.getInstance().coinManagers.put(player.getUniqueId().toString(), new CoinManager(player.getUniqueId().toString()));
        event.setJoinMessage("");

        if (Wrapper.getInstance().getServiceId().getTaskName().equalsIgnoreCase("Bauserver")){
            if (!player.hasPermission(BedNetAPI.getInstance().getPermissions().builder)){
                player.kickPlayer("§cKeie Rechte für diesen Server!");
            }
        }
        if (Wrapper.getInstance().getServiceId().getTaskName().equalsIgnoreCase("survival")){
            if (!player.hasPermission(BedNetAPI.getInstance().getPermissions().mod)){
                player.kickPlayer("§cKeie Rechte für diesen Server!");
            }
        }
    }
}
