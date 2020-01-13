package com.comtrade.controlerPL;

import com.comtrade.domen.TransferClass;
import com.comtrade.systemOperation.MainSystemOperation;

import com.comtrade.systemOperation.reservation.systemOperationReservationNoTickets;
import com.comtrade.systemOperation.reservation.systemOperationReservation_ReservationsList;
import com.comtrade.systemOperation.reservation.systemOperationReservationSave;

public class ControlerReservation implements CommandBase {
    @Override
    public void execute(TransferClass transferClass) {

        MainSystemOperation mainSystemOperation =null;

        switch (transferClass.getConstantBLC()){
            case RESERVATION_NO_TICKETS:
                mainSystemOperation = new systemOperationReservationNoTickets();
                break;
            case RESERVATION_ENTRY:
                mainSystemOperation = new systemOperationReservationSave();
                break;
            case RESERVATION_RETURN:
                mainSystemOperation = new systemOperationReservation_ReservationsList();
                break;


            default:
                break;


        }
        mainSystemOperation.ExecuteSystemOperation(transferClass);



    }
}
