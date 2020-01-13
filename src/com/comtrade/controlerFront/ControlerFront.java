package com.comtrade.controlerFront;

import com.comtrade.controlerPL.*;
import com.comtrade.controlerPL.ControlerActor;
import com.comtrade.domen.TransferClass;

public class ControlerFront {

    private static ControlerFront instance;
    private ControlerFront(){

    }

    public static ControlerFront getInstance() {
        if (instance== null){
            instance= new ControlerFront();
        }
        return instance;
    }
    public void  execute (TransferClass transferClass){

        CommandBase commandBase=null;

        switch (transferClass.getConstantFC()){

            case USER:
                commandBase= new ControlerUser();
                break;
            case THEATER:
                commandBase= new ControlerTheater();
                break;
            case SHOW:
                commandBase= new ControlerShow();
                break;
            case TERM:
                commandBase= new ControlerTerm();
                break;
            case RESERVATION:
                commandBase= new ControlerReservation();
                break;
            case ACTOR:
                commandBase= new ControlerActor();
                break;

            default:
                break;

        }commandBase.execute(transferClass);



    }



}
