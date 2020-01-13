package com.comtrade.systemOperation;

import com.comtrade.domen.TransferClass;
import com.comtrade.connection.Connect;

import java.sql.SQLException;


public abstract class MainSystemOperation {

    public void ExecuteSystemOperation(TransferClass transferClass){

        try{
            startTransaction();
            ExecuteSpecificOperation(transferClass);
            confirmTransaction();

        }catch (Exception e){
            cancelTransaction();
        }finally {
            closeConnection();
        }

    }

    private void closeConnection() {
        try {
            Connect.getConnect().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cancelTransaction() {
        Connect.getConnect().cancelTransaction();

    }

    private void confirmTransaction() {
        Connect.getConnect().confirmTransaction();
    }

    private void startTransaction() {        Connect.getConnect().startTransaction();    }


    public  abstract void ExecuteSpecificOperation(TransferClass transferClass);
}
