package de.ffliss.bednetapi.functions;

import de.ffliss.bednetapi.BedNetAPI;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class CoinManager {

    private String uuid;
    private int coins;

    public CoinManager(String uuid) {
        this.uuid = uuid;
        this.coins = getCoins();
    }

    public int getCoins(){
        try {
            PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("SELECT Coins FROM Player WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Coins");
            }
        } catch (Exception ex){
        }
        return 0;
    }

    public void updateCoins(int amound, boolean remove){
        if (remove){
            this.coins = this.coins - amound;

            if (this.coins <= 0){
                this.coins = 0;
            }
        }else {
            this.coins = this.coins + amound;
        }

        if (isUserExist()){
            try {
                PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("UPDATE Player SET Coins = ? WHERE UUID = ?");
                ps.setInt(1, this.coins);
                ps.setString(2, this.uuid);
                ps.executeUpdate();
            } catch (Exception ex){}
        } else {
            try {
                PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("INSERT INTO Player (UUID,Name,Coins) VALUES (?,?,?);");
                ps.setString(1, this.uuid);
                ps.setString(2, Bukkit.getPlayer(UUID.fromString(this.uuid)).getName());
                ps.setInt(3, this.coins);
                ps.executeUpdate();
            } catch (Exception ex){

            }
        }


    }

    public boolean isUserExist(){
        try {
            PreparedStatement ps = BedNetAPI.getInstance().getGlobalDatabaseManager().preparedStatement("SELECT Coins FROM Player WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception ex){}
        return false;
    }
}
