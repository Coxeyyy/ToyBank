package com.coxey.app;

import com.coxey.app.request.Request;
import com.coxey.app.request.RequestType;
import com.coxey.app.system.BankFrontSystem;

public class Client extends Thread {
    private RequestType requestType;
    private long amount;
    private BankFrontSystem frontSystem;
    private Request request;

    public Client(String name, long amount, RequestType requestType, BankFrontSystem frontSystem) {
        super(name);
        this.amount = amount;
        this.requestType = requestType;
        this.frontSystem = frontSystem;
        request = new Request(amount, requestType);
    }

    public void sendRequest(Request request) {
        frontSystem.addRequest(request);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " +
                "Заявка{clientThreadName = " + Thread.currentThread().getName() +
                ", amount = " + amount + ", requestType = " + requestType +
                "} отправлена в банк");
        sendRequest(request);
    }
}
