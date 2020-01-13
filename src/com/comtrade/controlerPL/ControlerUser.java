package com.comtrade.controlerPL;

import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;
import com.comtrade.systemOperation.user.*;

public class ControlerUser implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {

        MainSystemOperation mainSystemOperation =null;

        switch (transferClass.getConstantBLC()){
            case LOGGING:
                mainSystemOperation = new systemOperationUserLogin();
                break;
            case USER_RETURN_DATA:
                mainSystemOperation = new systemOperationUserReturnData();
                break;
            case USER_DATA_ENTRY:
                mainSystemOperation = new systemOperationUserDataEntry();
                break;
            case USER_DATA_UPDATE:
                mainSystemOperation = new systemOperationUserUpdate();
                break;
            case USER_REMOVE:
                mainSystemOperation = new systemOperationUserRemove();
                break;

            case USER_RETURN_NAME:
                mainSystemOperation = new systemOperationUserReturnName();
                break;
            case USER_RETURN_ID:
                mainSystemOperation = new systemOperationUserReturnID();
                break;

            default:
                break;




        }
        mainSystemOperation.ExecuteSystemOperation(transferClass);



    }
}
