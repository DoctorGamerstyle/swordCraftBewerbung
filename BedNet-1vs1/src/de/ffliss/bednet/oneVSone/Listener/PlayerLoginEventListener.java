package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginEventListener implements Listener {

    public PlayerLoginEventListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        if (Bukkit.getOnlinePlayers().size() == 2 ){
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, "§7[§51vs1§7]§cDieser Server ist voll!");
        }
    }
}
