package de.ffliss.bednet.oneVSone.game;

import de.ffliss.bednet.oneVSone.Main;
import de.ffliss.bednetapi.BedNetAPI;
import de.ffliss.bednetapi.functions.CoinManager;
import de.ffliss.bednetapi.functions.WorldLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GameHandler {

    private Player player1;
    private Player player2;

    private int countdown;
    private int gameTime;

    private boolean started = false;
    private boolean stoping = false;
    private boolean starting = false;
    private boolean stop = false;

    public GameHandler(){
        this.gameTime = 300;
        this.countdown = 10;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStoping() {
        return stoping;
    }

    public void setStoping(boolean stoping) {
        this.stoping = stoping;
    }

    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        if (!starting){
            this.countdown = 10;
        }
        this.starting = starting;
    }

    public void start(){
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (isStarting()) {
                    player1.setLevel(countdown);
                    player2.setLevel(countdown);

                    countdown = countdown - 1;

                    if (countdown <= 5) {
                        player1.playSound(player1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, 2);
                        player2.playSound(player2.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, 2);
                    }

                    if (countdown == -1) {
                        //BedNetAPI.getInstance().getServiceMngr().createNewServer("1vs1");
                        BedNetAPI.getInstance().getServiceMngr().setStateToIngame();
                        setStarted(true);
                        setStarting(false);
                        getInventory(player1);
                        getInventory(player2);
                    }
                }
            }
        },20,20);
    }

    public void game(){
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (isStarted()) {
                    player1.setLevel(gameTime);
                    player2.setLevel(gameTime);

                    gameTime = gameTime - 1;

                    if (gameTime <= 5) {
                        player1.playSound(player1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, 2);
                        player2.playSound(player2.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, 2);
                    }

                    if (gameTime == 0) {
                        Bukkit.broadcastMessage("§7[§51vs1§7]§aDieses spiel endet unentschieden, jeder bekommt §650 Coins");
                        new CoinManager(player1.getUniqueId().toString()).updateCoins(50, false);
                        new CoinManager(player2.getUniqueId().toString()).updateCoins(50, false);
                        setStarted(false);
                        setStoping(true);
                    }
                }
            }
        },20,20);

    }

    public void stop(){
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (isStoping()) {

                    countdown = countdown - 1;

                    if (countdown <= 5) {
                        Bukkit.broadcastMessage("§cServer neustart in§7: §a" + countdown);
                    }

                    if (countdown == 0) {
                        try {
                            player1.kickPlayer("");
                        } catch (Exception ex){}

                        try {
                            player2.kickPlayer("");
                        } catch (Exception ex){}

                        Bukkit.getServer().shutdown();
                        BedNetAPI.getInstance().getServiceMngr().stopThisServer();
                    }
                }
            }
        },20,20);
    }

    public void getInventory(Player player){
        Inventory inv = player.getInventory();
        inv.setItem(0, new ItemStack(Material.IRON_SWORD));
        inv.setItem(1, new ItemStack(Material.BOW));
        inv.setItem(2, new ItemStack(Material.MUSHROOM_STEW));
        inv.setItem(3, new ItemStack(Material.MUSHROOM_STEW));
        inv.setItem(4, new ItemStack(Material.MUSHROOM_STEW));
        inv.setItem(5, new ItemStack(Material.MUSHROOM_STEW));
        inv.setItem(6, new ItemStack(Material.GOLDEN_APPLE));
        inv.setItem(7, new ItemStack(Material.GOLDEN_APPLE));
        inv.setItem(8, new ItemStack(Material.ARROW, 64));

        inv.setItem(100, new ItemStack(Material.IRON_BOOTS));
        inv.setItem(101, new ItemStack(Material.IRON_LEGGINGS));
        inv.setItem(102, new ItemStack(Material.IRON_CHESTPLATE));
        inv.setItem(103, new ItemStack(Material.IRON_HELMET));

    }

    public void addPlayer(Player player){
        if (this.player1 == null){
            this.player1 = player;
            this.player1.teleport(getSpawnLocation1());
            return;
        }
        if (this.player2 == null){
            this.player2 = player;
            this.player2.teleport(getSpawnLocation2());
            return;


        }
    }

    public Location getSpawnLocation1(){

        return new Location(Bukkit.getWorld("world"),0.5,56,34.5);
    }

    public Location getSpawnLocation2(){

        return new Location(Bukkit.getWorld("world"),0.5,56,-33.5);
    }
}
