package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvents implements Listener {

    public DamageEvents() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (Main.getInstance().getGameHandler().isStarted()){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamageByBlock(EntityDamageByBlockEvent event){
        if (Main.getInstance().getGameHandler().isStarted()){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event){
        if (Main.getInstance().getGameHandler().isStarted()){
            return;
        }
        event.setCancelled(true);
    }

}
