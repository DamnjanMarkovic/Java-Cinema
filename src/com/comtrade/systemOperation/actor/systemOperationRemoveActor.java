package com.comtrade.systemOperation.actor;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Actor;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationRemoveActor extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Actor actor = (Actor) transferClass.getRequest();
        Broker broker = new Broker();
        broker.delete(actor, actor.getidActor());



    }
}
