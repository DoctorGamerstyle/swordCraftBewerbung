package de.ffliss.bednetapi.cmd;

import de.dytanic.cloudnet.driver.service.ServiceId;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player)){
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission(BedNetAPI.getInstance().getPermissions().admin)){
            player.sendMessage("§cKeine Rechte!");
            return true;
        }

        ServiceId serviceId = Wrapper.getInstance().getServiceId();

        player.sendMessage("§a===============§7[§bServer info§7]§a===============");
        player.sendMessage("§a");
        player.sendMessage("§aServer Name§7:§6 " + serviceId.getName());
        player.sendMessage("§a");
        player.sendMessage("§aTask Name§7:§6 " + serviceId.getTaskName());
        player.sendMessage("§a");
        player.sendMessage("§aPort§7: §6" + Wrapper.getInstance().getServiceConfiguration().getPort());
        player.sendMessage("§a");
        if (BedNetAPI.getInstance().getGlobalDatabaseManager().isConnected()){
            player.sendMessage("§aMySQL Status§7: §aVerbunden");
        }else {
            player.sendMessage("§aMySQL Status§7: §cnicht Verbunden");
        }
        return false;
    }
}
