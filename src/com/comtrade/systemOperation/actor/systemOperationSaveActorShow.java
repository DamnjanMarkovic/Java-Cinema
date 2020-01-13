package com.comtrade.systemOperation.actor;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Actor_Show;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationSaveActorShow extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Actor_Show actorShow = (Actor_Show) transferClass.getRequest();
        Broker broker = new Broker();
        broker.save(actorShow);


    }
}
