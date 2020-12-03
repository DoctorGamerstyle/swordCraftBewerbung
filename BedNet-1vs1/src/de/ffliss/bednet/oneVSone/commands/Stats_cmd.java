package de.ffliss.bednet.oneVSone.commands;

import de.ffliss.bednet.oneVSone.Main;
import de.ffliss.bednet.oneVSone.util.DatabasePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats_cmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)){
            return true;
        }

        Player player = (Player) commandSender;

        DatabasePlayer databasePlayer = new DatabasePlayer(player.getUniqueId().toString());

        if (!databasePlayer.isUserExist()){
            player.sendMessage("§a===============§7[§51vs1 Stats§7]§a===============");
            player.sendMessage("§a");
            player.sendMessage("§6Kills§7:§a 0");
            player.sendMessage("§a");
            player.sendMessage("§6Tode§7:§a 0");
            player.sendMessage("§a");
            player.sendMessage("§6K/D§7:§a 0");
            return true;
        }

        double KD = 0;
        try {
            KD = databasePlayer.getKills() / databasePlayer.getDeath();
        } catch (Exception ex){
            KD = 0;
        }

        player.sendMessage("§a===============§7[§51vs1 Stats§7]§a===============");
        player.sendMessage("§a");
        player.sendMessage("§6Kills§7:§a " + databasePlayer.getKills());
        player.sendMessage("§a");
        player.sendMessage("§6Tode§7:§a " + databasePlayer.getDeath());
        player.sendMessage("§a");
        player.sendMessage("§6K/D§7:§a " + KD);

        return true;
    }
}
