package com.comtrade.systemOperation.term;

import com.comtrade.broker.Broker;

import com.comtrade.domen.Term;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

public class systemOperationTermReturnDates extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        Term term = (Term) transferClass.getRequest();
        int messageInt = transferClass.getMessage();
        String message = String.valueOf(messageInt);
        Broker broker = new Broker();
        transferClass.setResponse(broker.vratiDatume(term, message));


    }
}
