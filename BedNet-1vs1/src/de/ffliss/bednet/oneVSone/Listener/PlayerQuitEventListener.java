package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import de.ffliss.bednet.oneVSone.game.GameHandler;
import de.ffliss.bednetapi.BedNetAPI;
import de.ffliss.bednetapi.functions.CoinManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitEventListener implements Listener {

    public PlayerQuitEventListener() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        GameHandler gameHandler = Main.getInstance().getGameHandler();

        event.setQuitMessage("§7[§51vs1§7]§a" + player.getDisplayName() + "§a hat das Spiel verlassen");


        if (gameHandler.isStarted()){
            if (gameHandler.getPlayer1() == player){
                Bukkit.broadcastMessage("§7[§51vs1§7]§6" + gameHandler.getPlayer2().getDisplayName() + " §aHat das Spiel gewonnen!");
                new CoinManager(gameHandler.getPlayer2().getUniqueId().toString()).updateCoins(100, false);
                gameHandler.getPlayer2().sendMessage("§7[§51vs1§7]§aDu hast für den Sieg §6100 Coins §abekommen");
            }

            if (gameHandler.getPlayer2() == player){
                Bukkit.broadcastMessage("§7[§51vs1§7]§6" + gameHandler.getPlayer1().getDisplayName() + " §aHat das Spiel gewonnen!");
                new CoinManager(gameHandler.getPlayer1().getUniqueId().toString()).updateCoins(100, false);
                gameHandler.getPlayer1().sendMessage("§7[§51vs1§7]§aDu hast für den Sieg §6100 Coins §abekommen");
            }

            gameHandler.setStarted(false);
            gameHandler.setStoping(true);
        }

        if (gameHandler.getPlayer1() == player){
            gameHandler.setPlayer1(null);
        }

        if (gameHandler.getPlayer2() == player){
            gameHandler.setPlayer2(null);
        }
    }
}
