package com.comtrade.systemOperation.user;

import com.comtrade.broker.Broker;
import com.comtrade.domen.User;
import com.comtrade.domen.GeneralDomen;
import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import java.util.ArrayList;
import java.util.List;

public class systemOperationUserLogin extends MainSystemOperation {
    @Override
    public void ExecuteSpecificOperation(TransferClass transferClass) {

        User user = (User) transferClass.getRequest();
        Broker broker = new Broker();
        transferClass.setResponse(returnData(broker.returnData(user), user.getUsername(), user.getPassword()));
    }
    public int returnData(List<GeneralDomen> listGeneralDomen, String username, String password) {
        int status = 0;
        List<User>userList = new ArrayList<>();
        for (GeneralDomen opsd: listGeneralDomen) {
            userList.add((User) opsd);
        }
        for (User user : userList             ) {
            if (user.getUsername().equalsIgnoreCase(username)&& user.getPassword().equalsIgnoreCase(password))
            status = user.getStatus();
        }
        return status;
    }
}
