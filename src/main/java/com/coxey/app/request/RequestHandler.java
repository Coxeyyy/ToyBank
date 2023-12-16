package com.coxey.app.request;

import com.coxey.app.system.BankBackSystem;
import com.coxey.app.system.BankFrontSystem;

public class RequestHandler extends Thread {
    private BankFrontSystem frontSystem;
    private BankBackSystem backSystem;
    private boolean flag = true;

    public RequestHandler(BankFrontSystem frontSystem, BankBackSystem backSystem) {
        this.frontSystem = frontSystem;
        this.backSystem = backSystem;
    }

    private void sendRequestBankBackSystem(Request request) {
        backSystem.getRequestFromHandler(request);
    }

    @Override
    public void run() {
        while(flag) {
            /*try {
                Thread.sleep(150);
                Request request = frontSystem.getRequest();
                if(request == null) {
                    flag = false;
                    continue;
                }
                System.out.println("Обработчик заявок " + Thread.currentThread().getName() +" принял заявку" +
                        " сумма:" + request.getAmount() + " тип заявки: " + request.getRequestType());
                sendRequestBankBackSystem(request);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            if(frontSystem.getRequestQueue().size() != 0) {
                Request request = frontSystem.getRequest();
                if(request == null) {
                    flag = false;
                    continue;
                }
                System.out.println("Обработчик заявок " + Thread.currentThread().getName() +" принял заявку" +
                        " сумма:" + request.getAmount() + " тип заявки: " + request.getRequestType());
                sendRequestBankBackSystem(request);
            }
            //backSystem.getRequestFromHandler(request);
        }
    }
}
