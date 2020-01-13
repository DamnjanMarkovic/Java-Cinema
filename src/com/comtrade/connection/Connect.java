package com.comtrade.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private Connection connection;
    private static Connect connect;

    public static Connect getConnect() {
        if (connect ==null){
            connect = new Connect();
        }
        return connect;
    }

    private Connect(){

    }

    public void startTransaction(){
        try {
    
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teatar", "root","");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void confirmTransaction(){

        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cancelTransaction(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void closeConnection() throws SQLException {
        try {
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
