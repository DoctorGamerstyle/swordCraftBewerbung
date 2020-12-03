package de.ffliss.bednet.oneVSone;

import de.ffliss.bednet.oneVSone.Listener.*;
import de.ffliss.bednet.oneVSone.commands.Stats_cmd;
import de.ffliss.bednet.oneVSone.game.GameHandler;
import de.ffliss.bednet.oneVSone.util.DatabasePlayer;
import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    private static Main instance;
    public final static String dataTable = "ovso";
    public HashMap<String, DatabasePlayer> databasePlayers = new HashMap<>();
    private GameHandler gameHandler;

    @Override
    public void onEnable() {

        instance = this;
        createTable();

        this.gameHandler = new GameHandler();

        new cancelled();
        new PlayerQuitEventListener();
        new PlayerJoinEventListener();
        new PlayerLoginEventListener();
        new PlayerMoveEventListener();
        new PlayerDeathEventListener();
        new DamageEvents();

        getCommand("stats").setExecutor(new Stats_cmd());

        ((CraftServer) Bukkit.getServer()).getServer().setMotd("§aGarten");

        gameHandler.start();
        gameHandler.game();
        gameHandler.stop();

    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public static Main getInstance() {
        return instance;
    }

    private void createTable(){
        BedNetAPI.getInstance().getGlobalDatabaseManager().update("CREATE TABLE IF NOT EXISTS "+ dataTable +" (UUID VARCHAR(100), Kills INT(100), Death INT(100));");
    }
}
