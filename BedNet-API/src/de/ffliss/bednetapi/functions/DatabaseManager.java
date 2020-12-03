package de.ffliss.bednetapi.functions;

import java.sql.*;

public class DatabaseManager {

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private Connection connection;


    public DatabaseManager(String database){
        this.host = "127.0.0.2";
        this.port = 3306;
        this.database = database;
        this.username = "minecraft";
        this.password = "192712Felix";
    }

    public DatabaseManager(String host, int port, String database) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = "root";
        this.password = "192712Felix";
    }

    public void connect(){
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", username, password);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void disconnect(){
        if (isConnected()){
            try {
                this.connection.close();
            } catch (Exception ex){

            }
        }
    }

    public boolean isConnected(){
        return (this.connection == null ? false : true);
    }

    public void update(String qry){
        try {
            PreparedStatement ps = this.connection.prepareStatement(qry);
            ps.executeUpdate();
        } catch (Exception ex){

        }
    }

    public PreparedStatement preparedStatement(String qry){
        try {
            return this.connection.prepareStatement(qry);
        } catch (Exception ex){}
        return null;
    }

    public ResultSet resultSet(String qry){
        try {
            PreparedStatement ps = this.connection.prepareStatement(qry);
            return  ps.executeQuery();
        } catch (Exception ex){

        }
        return null;
    }


}
