package com.comtrade.controlerPL;

import com.comtrade.systemOperation.actor.systemOperationActorSave;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;
import com.comtrade.systemOperation.actor.*;

public class ControlerActor implements com.comtrade.controlerPL.CommandBase {
    @Override
    public void execute(TransferClass transferClass) {

        MainSystemOperation mainSystemOperation =null;

        switch (transferClass.getConstantBLC()){


            case ACTOR_RETURN_DATA:
                mainSystemOperation = new systemOperationActorReturnData();
                break;
            case ACTOR_RETURN_SHOW:
                mainSystemOperation = new systemOperationReturnActorShow();
                break;
            case ACTOR_RETURN_ID:
                mainSystemOperation = new systemOperationActorReturnID();
                break;

            case ACTOR_SAVE:
                mainSystemOperation = new systemOperationActorSave();
                break;
            case ACTOR_SHOW_SAVE:
                mainSystemOperation = new systemOperationSaveActorShow();
                break;
            case ACTOR_REMOVE:
                mainSystemOperation = new systemOperationRemoveActor();
                break;

            default:
                break;

        }
        mainSystemOperation.ExecuteSystemOperation(transferClass);

    }
}
