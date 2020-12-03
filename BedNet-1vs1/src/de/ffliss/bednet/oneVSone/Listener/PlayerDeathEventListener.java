package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import de.ffliss.bednet.oneVSone.game.GameHandler;
import de.ffliss.bednet.oneVSone.util.DatabasePlayer;
import de.ffliss.bednetapi.functions.CoinManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathEventListener implements Listener {

    public PlayerDeathEventListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        if (!(event.getEntity() instanceof Player)){
            return;
        }

        if (!(event.getEntity().getKiller() instanceof Player)){
            return;
        }

        event.setDeathMessage("");

        Player player = event.getEntity();
        Player killer = player.getKiller();

        GameHandler gameHandler = Main.getInstance().getGameHandler();

        DatabasePlayer player1 = Main.getInstance().databasePlayers.get(player.getUniqueId().toString());
        DatabasePlayer killer1 = Main.getInstance().databasePlayers.get(killer.getUniqueId().toString());


        if (gameHandler.isStarted()){
                Bukkit.broadcastMessage("§7[§51vs1§7]§6" + killer.getDisplayName() + " §aHat das Spiel gewonnen!");
                new CoinManager(killer.getUniqueId().toString()).updateCoins(100, false);
                killer.sendMessage("§7[§51vs1§7]§aDu hast für den Sieg §6100 Coins §abekommen");
                killer1.updateKills(1, false);
                player1.updateDeath(1, false);
                gameHandler.setStarted(false);
                gameHandler.setStoping(true);
        }

        player.setHealth(20.0);
        player.spigot().respawn();
    }
}
