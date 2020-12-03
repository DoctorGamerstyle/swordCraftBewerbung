package de.ffliss.bednet.oneVSone.util;

import de.ffliss.bednet.oneVSone.Main;
import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabasePlayer {

    private String uuid;
    private int kills;
    private int death;
    private String table;

    public DatabasePlayer(String uuid) {
        this.uuid = uuid;
        this.kills = getKills();
        this.death = getDeath();
        this.table = Main.dataTable;
    }

    public int getKills(){
        try {
            PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("SELECT Kills FROM " + this.table +" WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Kills");
            }
        } catch (Exception ex){
        }
        return 0;
    }

    public int getDeath(){
        try {
            PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("SELECT Death FROM " + this.table +" WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Death");
            }
        } catch (Exception ex){
        }
        return 0;
    }

    public void updateKills(int amound, boolean remove){
        if (remove){
            this.kills = getKills() - amound;

            if (this.kills <= 0){
                this.kills = 0;
            }
        }else {
            this.kills = getKills() + amound;
        }

        if (isUserExist()){
            try {
                PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("UPDATE " + this.table +" SET Kills = ? WHERE UUID = ?");
                ps.setInt(1, this.kills);
                ps.setString(2, this.uuid);
                ps.executeUpdate();
            } catch (Exception ex){}
        } else {
            try {
                PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("INSERT INTO " + this.table + " (UUID,Kills,Death) VALUES (?,?,?);");
                ps.setString(1, this.uuid);
                ps.setInt(2, this.kills);
                ps.setInt(3, this.death);
                ps.executeUpdate();
            } catch (Exception ex){

            }
        }


    }

    public void updateDeath(int amound, boolean remove){
        if (remove){
            this.death = getDeath() - amound;

            if (this.death <= 0){
                this.death = 0;
            }
        }else {
            this.death = getDeath() + amound;
        }

        if (isUserExist()){
            try {
                PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("UPDATE " + this.table +" SET Death = ? WHERE UUID = ?");
                ps.setInt(1, this.death);
                ps.setString(2, this.uuid);
                ps.executeUpdate();
            } catch (Exception ex){}
        } else {
            try {
                PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("INSERT INTO " + this.table + " (UUID,Kills,Death) VALUES (?,?,?);");
                ps.setString(1, this.uuid);
                ps.setInt(2, this.kills);
                ps.setInt(3, this.death);
                ps.executeUpdate();
            } catch (Exception ex){

            }
        }


    }

    public boolean isUserExist(){
        try {
            PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("SELECT Death FROM " + this.table + " WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex){}
        return false;
    }
}
