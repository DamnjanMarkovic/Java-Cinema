package com.comtrade.systemOperation.show;

import com.comtrade.broker.Broker;
import com.comtrade.domen.Show;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationShowRemove extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Show show = (Show) transferClass.getRequest();
        Broker broker = new Broker();
        broker.delete(show, show.getidShow());


    }
}
