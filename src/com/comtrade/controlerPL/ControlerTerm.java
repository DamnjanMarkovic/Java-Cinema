package com.comtrade.controlerPL;

import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;
import com.comtrade.systemOperation.term.*;

public class ControlerTerm implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {

        MainSystemOperation mainSystemOperation =null;

        switch (transferClass.getConstantBLC()){

            case TERM_DATA_RETURN:
                mainSystemOperation = new systemOperationTermReturnData();
                break;
            case TERM_DATA_ENTRY:
                mainSystemOperation = new systemOperationTermDataEntry();
                break;
            case TERM_RETURN_TIME:
                mainSystemOperation = new systemOperationTermReturnDataTime();
                break;
            case TERM_REMOVE:
                mainSystemOperation = new systemOperationTermRemove();
                break;
            case TERM_UPDATE:
                mainSystemOperation = new systemOperationTermUpdateData();
                break;
            case TERM_RETURN_ID:
                mainSystemOperation = new systemOperationTermReturnID();
                break;
            case DATA_RETURN_FRONT_PAGE:
                mainSystemOperation = new systemOperationTermReturnDataOpening();
                break;
            case TERM_RETURN_TERM:
                mainSystemOperation = new systemOperationTermReturnDataTerm();
                break;




            default:
                break;


        }
        mainSystemOperation.ExecuteSystemOperation(transferClass);



    }
}
