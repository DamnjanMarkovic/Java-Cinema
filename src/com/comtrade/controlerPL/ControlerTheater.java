package com.comtrade.controlerPL;

import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;
import com.comtrade.systemOperation.theater.*;

public class ControlerTheater implements com.comtrade.controlerPL.CommandBase {
    @Override
    public void execute(TransferClass transferClass) {

        MainSystemOperation mainSystemOperation =null;

        switch (transferClass.getConstantBLC()){

            case THEATER_RETURN_DATA:
                mainSystemOperation = new systemOperationTheaterReturnData();
                break;
            case THEATER_DATA_ENTRY:
                mainSystemOperation = new systemOperationTheaterEntry();
                break;
            case THEATER_REMOVE:
                mainSystemOperation = new systemOperationTheaterRemove();
                break;
            case THEATER_DATA_UPDATE:
                mainSystemOperation = new systemOperationTheaterUpdate();
                break;
            case THEATER_RETURN_NAME:
                mainSystemOperation = new systemOperationTheaterName();
                break;
            case THEATER_RETURN_ID:
                mainSystemOperation = new systemOperationTheaterReturnID();
                break;
            case THEATER_RETURN_NAMES:
                mainSystemOperation = new systemOperationTheaterReturnNames();
                break;


            default:
                break;




        }
        mainSystemOperation.ExecuteSystemOperation(transferClass);



    }
}
