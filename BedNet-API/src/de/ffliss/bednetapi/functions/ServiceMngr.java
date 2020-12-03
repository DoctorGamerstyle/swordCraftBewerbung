package de.ffliss.bednetapi.functions;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetHelper;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.Bukkit;

public class ServiceMngr {

    private Wrapper wrapper;

    public ServiceMngr(){
        this.wrapper = Wrapper.getInstance();
    }

    public void createNewServer(String type){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "create by " + type + " 1 --start");
    }

    public void setStateToIngame(){
        BukkitCloudNetHelper.changeToIngame();
    }

    public void setState(String state){
        BukkitCloudNetHelper.setState(state);
    }

    public void stopThisServer(){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cloudnet service " + this.wrapper.getServiceId().getTaskName() + " stop");
    }


}
