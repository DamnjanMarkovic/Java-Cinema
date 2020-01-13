package com.comtrade.controlerPL;

import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;
import com.comtrade.systemOperation.show.*;

public class ControlerShow implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {
        MainSystemOperation mainSystemOperation =null;

        switch (transferClass.getConstantBLC()){


            case SHOW_RETURN_DATA:
                mainSystemOperation = new systemOperationShowReturnColums();
                break;
            case SHOW_DATA_ENTRY:
                mainSystemOperation = new systemOperationShowDataEntry();
                break;
            case SHOW_REMOVE:
                mainSystemOperation = new systemOperationShowRemove();
                break;
            case SHOW_NAMES_UPDATE:
                mainSystemOperation = new systemOperationShowUpdate();
                break;
            case SHOW_NAMES:
                mainSystemOperation = new systemOperationShowNames();
                break;
            case SHOW_RETURN_ID:
                mainSystemOperation = new systemOperationShowReturnID();
                break;
            case SHOW_RETURN_NAMES:
                mainSystemOperation = new systemOperationShowReturnNames();
                break;



            default:
                break;


        }
        mainSystemOperation.ExecuteSystemOperation(transferClass);

    }
}
