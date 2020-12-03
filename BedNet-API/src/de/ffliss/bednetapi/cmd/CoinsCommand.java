package de.ffliss.bednetapi.cmd;

import de.ffliss.bednetapi.BedNetAPI;
import de.ffliss.bednetapi.functions.CoinManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)){
            return true;
        }

        Player player = (Player) commandSender;

        if (args.length == 0){
            CoinManager manager = BedNetAPI.getInstance().coinManagers.get(player.getUniqueId().toString());
            player.sendMessage("§aDein Aktuelles Guthaben§7: §6" + manager.getCoins() + " Coins");
            return  true;
        }

        if (!player.hasPermission(BedNetAPI.getInstance().getPermissions().mod)){
            player.sendMessage("§cKeine Rechte!");
            return true;
        }

        if (args.length >= 3){
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                Player target = Bukkit.getPlayer(args[0]);
                CoinManager manager = BedNetAPI.getInstance().coinManagers.get(target.getUniqueId().toString());
                if (args[1].equalsIgnoreCase("add")){
                    manager.updateCoins(Integer.valueOf(args[2]), false);
                    player.sendMessage("§aDu hast dem Spieler §3" + target.getName() + " §6" + Integer.valueOf(args[2]) + " Coins §agegeben");
                    return true;
                }
                if (args[1].equalsIgnoreCase("remove")){
                    manager.updateCoins(Integer.valueOf(args[2]), true);
                    player.sendMessage("§aDu hast dem Spieler §3" + target.getName() + " §6" + Integer.valueOf(args[2]) + " Coins §cabgezogen");
                    return true;
                }

            }else {
                player.sendMessage("§cDieser Spieler ist nicht Online");
                return true;
            }
        }
        player.sendMessage("§c/coins <name> <add/remove> <amount>");
        return true;
    }
}
