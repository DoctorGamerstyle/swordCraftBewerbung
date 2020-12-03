package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveEventListener implements Listener {

    public PlayerMoveEventListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){

        if (Main.getInstance().getGameHandler().isStarted() || Main.getInstance().getGameHandler().isStoping()){
            return;
        }

        Player p = event.getPlayer();

        double x = event.getFrom().getX();
        double z = event.getFrom().getZ();

        float yaw = event.getFrom().getYaw();
        float pitch = event.getFrom().getPitch();

        if ((event.getTo().getX() != x) || (event.getTo().getZ() != z)){

            Location loc = new Location(p.getLocation().getWorld(), x, event.getTo().getY(), z, yaw, pitch);

            p.teleport(loc);
        }
    }
}
