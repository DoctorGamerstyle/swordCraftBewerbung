package de.ffliss.bednetapi;

import de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetHelper;
import de.dytanic.cloudnet.ext.bridge.player.CloudPlayer;
import de.dytanic.cloudnet.ext.bridge.player.NetworkConnectionInfo;
import de.ffliss.bednetapi.cmd.CoinsCommand;
import de.ffliss.bednetapi.cmd.TestCommand;
import de.ffliss.bednetapi.functions.*;
import de.ffliss.bednetapi.listener.ChatManager;
import de.ffliss.bednetapi.listener.PlayerJoinEventListener;
import de.ffliss.bednetapi.listener.PlayerQuitEventListener;
import de.ffliss.bednetapi.util.Colors;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class BedNetAPI extends JavaPlugin {

    private static BedNetAPI main;
    private WorldLoader worldLoader;
    private Colors colors;
    private DatabaseManager databaseManager;
    private Permissions permissions;
    private ServiceMngr serviceMngr;
    public HashMap<String, CoinManager> coinManagers = new HashMap<>();

    @Override
    public void onEnable() {

        this.main = this;
        this.worldLoader = new WorldLoader();
        this.colors = new Colors();
        this.databaseManager = new DatabaseManager("minecraft");
        this.permissions = new Permissions();
        this.serviceMngr = new ServiceMngr();

        getCommand("serverInfo").setExecutor(new TestCommand());

        new ChatManager();
        new PlayerJoinEventListener();
        new PlayerQuitEventListener();

        getCommand("coins").setExecutor(new CoinsCommand());

        this.databaseManager.connect();
        createTable();
    }

    public static BedNetAPI getInstance(){
        return main;
    }

    public WorldLoader getWorldLoader() {
        return worldLoader;
    }

    public Colors getColors() {
        return colors;
    }

    public DatabaseManager getGlobalDatabaseManager() {
        return databaseManager;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public ServiceMngr getServiceMngr() {
        return serviceMngr;
    }

    private void createTable(){
        this.databaseManager.update("CREATE TABLE IF NOT EXISTS Player (UUID VARCHAR(100), Name VARCHAR(20), Coins INT(100));");
    }

    public void moveToHub(Player player){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF("Lobby-1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    }
}
