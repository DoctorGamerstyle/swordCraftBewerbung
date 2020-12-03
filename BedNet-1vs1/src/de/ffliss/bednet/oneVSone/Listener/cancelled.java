package de.ffliss.bednet.oneVSone.Listener;

import de.ffliss.bednet.oneVSone.Main;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class cancelled implements Listener {

    private Main Plugin;

    public cancelled() {
        this.Plugin=Main.getInstance();
        Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
    }

    @EventHandler
    public void onPing(ServerListPingEvent event){
        event.setMaxPlayers(2);
        event.setMotd("§aGarten");
    }

    @EventHandler
    public void onWheatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK){
            if(event.getPlayer().getItemInHand().getType()==Material.BOW || event.getPlayer().getItemInHand().getType()==Material.FISHING_ROD){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
            if(event.getPlayer().getItemInHand().getType()==Material.MUSHROOM_STEW){
                if(event.getPlayer().getHealth() != event.getPlayer().getMaxHealth()){
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 5));
                    event.getPlayer().setItemInHand(new ItemStack(Material.BOWL));
                }
            }
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.TNT){
            Entity tnt = event.getPlayer().getWorld().spawn(event.getBlock().getLocation(), TNTPrimed.class);
            event.getBlock().setType(Material.AIR);
            ((TNTPrimed)tnt).setFuseTicks(0);
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onFade(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeafDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.BOWL) {
            Entity Item = event.getItemDrop();
            Item.remove();
        } else {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onSpawn(EntitySpawnEvent Event) {

        if (Event.getEntityType() != EntityType.VILLAGER && Event.getEntityType() != EntityType.IRON_GOLEM) {
            Event.getEntity().remove();
            Event.setCancelled(true);
        }

    }
}
