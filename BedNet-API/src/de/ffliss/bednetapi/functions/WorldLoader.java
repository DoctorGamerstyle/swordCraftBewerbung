package de.ffliss.bednetapi.functions;


import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.util.ArrayList;
import java.util.List;

public class WorldLoader {

    private List<String> loadedWorlds = new ArrayList<String>();

    public WorldLoader(){}

    public void loadWorld(String name){
        if (!this.loadedWorlds.contains(name)) {
            WorldCreator creator = new WorldCreator(name);
            creator.createWorld();
            this.loadedWorlds.add(name);
        }
    }

    public void unloadWorld(String name) {
        if (loadedWorlds.contains(name)) {
            Bukkit.getServer().unloadWorld(Bukkit.getWorld(name), false);
            loadedWorlds.remove(name);
        }
    }

    public void loadLobby(){
        String name = "Lobby";
        if (!this.loadedWorlds.contains(name)) {
            WorldCreator creator = new WorldCreator(name);
            creator.createWorld();
            this.loadedWorlds.add(name);
        }
    }
}
