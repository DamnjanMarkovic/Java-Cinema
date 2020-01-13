package com.comtrade.domen;

import com.comtrade.controlerFront.ControlerFront;

public class TransferClass {

    private Object request;
    private int message;
    private ConstantFC constantFC;
    private ConstantBLC constantBLC;
    private Object response;

    public static TransferClass transferKlasa(Object object, int message, ConstantFC constantFC, ConstantBLC constantBLC){

        TransferClass transferClass = new TransferClass();
        transferClass.setRequest(object);
        transferClass.setMessage(message);
        transferClass.setConstantFC(constantFC);
        transferClass.setConstantBLC(constantBLC);
        ControlerFront.getInstance().execute(transferClass);


        return transferClass;
    }


    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public ConstantFC getConstantFC() {
        return constantFC;
    }

    public void setConstantFC(ConstantFC constantFC) {
        this.constantFC = constantFC;
    }

    public ConstantBLC getConstantBLC() {
        return constantBLC;
    }

    public void setConstantBLC(ConstantBLC constantBLC) {
        this.constantBLC = constantBLC;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
