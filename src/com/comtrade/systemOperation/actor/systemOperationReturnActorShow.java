package com.comtrade.systemOperation.actor;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Actor;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationReturnActorShow extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Actor actor = (Actor) transferClass.getRequest();
        int idPredstave = transferClass.getMessage();
        Broker broker = new Broker();
        transferClass.setResponse(broker.vratiIDGlumacaUPredstavi(idPredstave));


    }
}
