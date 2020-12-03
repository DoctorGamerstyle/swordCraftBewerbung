package de.ffliss.bednetapi.util;

import de.ffliss.bednetapi.BedNetAPI;
import de.ffliss.bednetapi.functions.CoinManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Colors{

    private Scoreboard scoreboard;

    public Colors() {
       this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
       
        if (scoreboard.getTeam("admin") == null){
            this.scoreboard.registerNewTeam("admin");
        }
        this.scoreboard.getTeam("admin").setPrefix("§4Admin §7| §4");
        this.scoreboard.getTeam("admin").setColor(ChatColor.DARK_RED);


        if (scoreboard.getTeam("dev") == null){
            this.scoreboard.registerNewTeam("dev");
        }
        this.scoreboard.getTeam("dev").setPrefix("§3Dev §7| §3");
        this.scoreboard.getTeam("dev").setColor(ChatColor.DARK_AQUA);

        if (scoreboard.getTeam("build") == null){
            this.scoreboard.registerNewTeam("build");
        }
        this.scoreboard.getTeam("build").setPrefix("§eBuilder §7| §e");
        this.scoreboard.getTeam("build").setColor(ChatColor.YELLOW);

        if (scoreboard.getTeam("mod") == null){
            this.scoreboard.registerNewTeam("mod");
        }
        this.scoreboard.getTeam("mod").setPrefix("§9Mod §7| §9");
        this.scoreboard.getTeam("mod").setColor(ChatColor.BLUE);

        if (scoreboard.getTeam("vip") == null){
            this.scoreboard.registerNewTeam("vip");
        }
        this.scoreboard.getTeam("vip").setPrefix("§5");
        this.scoreboard.getTeam("vip").setColor(ChatColor.DARK_PURPLE);

        if (scoreboard.getTeam("prem") == null){
            this.scoreboard.registerNewTeam("prem");
        }
        this.scoreboard.getTeam("prem").setPrefix("§6");
        this.scoreboard.getTeam("prem").setColor(ChatColor.GOLD);

        if (scoreboard.getTeam("default") == null){
            this.scoreboard.registerNewTeam("default");
        }
        this.scoreboard.getTeam("default").setPrefix("§b");
        this.scoreboard.getTeam("default").setColor(ChatColor.GREEN);
    }

    public void setColor(Player player){
        if (player.hasPermission(BedNetAPI.getInstance().getPermissions().admin)){
            player.setDisplayName("§4Admin §7| §4" + player.getName());
            scoreboard.getTeam("admin").addPlayer(player);
            return;
        }
        if (player.hasPermission(BedNetAPI.getInstance().getPermissions().dev)){
            player.setDisplayName("§3Dev §7| §3" + player.getName());
            scoreboard.getTeam("dev").addPlayer(player);
            return;
        }
        if (player.hasPermission(BedNetAPI.getInstance().getPermissions().builder)){
            player.setDisplayName("§eBuilder §7| §e" + player.getName());
            scoreboard.getTeam("build").addPlayer(player);
            return;
        }
        if (player.hasPermission(BedNetAPI.getInstance().getPermissions().mod)){
            player.setDisplayName("§9Mod §7| §9" + player.getName());
            scoreboard.getTeam("mod").addPlayer(player);
            return;
        }
        if (player.hasPermission(BedNetAPI.getInstance().getPermissions().vip)){
            player.setDisplayName("§5" + player.getName());
            scoreboard.getTeam("vip").addPlayer(player);
            return;
        }
        if (player.hasPermission(BedNetAPI.getInstance().getPermissions().prem)){
            player.setDisplayName("§6" + player.getName());
            scoreboard.getTeam("prem").addPlayer(player);
            return;
        }
        player.setDisplayName("§b" + player.getName());
        scoreboard.getTeam("default").addPlayer(player);
        return;

    }


}
