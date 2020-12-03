package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import de.ffliss.bednet.oneVSone.util.DatabasePlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

    public PlayerJoinEventListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("§7[§51vs1§7]§a" + player.getDisplayName() + "§a ist dem Spiel beigetreten");

        Main.getInstance().getGameHandler().addPlayer(player);

        DatabasePlayer databasePlayer = new DatabasePlayer(player.getUniqueId().toString());

        Main.getInstance().databasePlayers.put(player.getUniqueId().toString(), databasePlayer);

        player.setGameMode(GameMode.SURVIVAL);

        if (Main.getInstance().getGameHandler().getPlayer1() != null && Main.getInstance().getGameHandler().getPlayer2() != null){
            Main.getInstance().getGameHandler().setStarting(true);
        }
    }
}
