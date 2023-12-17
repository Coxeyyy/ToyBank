package com.coxey.app;

import com.coxey.app.request.Request;
import com.coxey.app.request.RequestType;

import java.util.function.Consumer;

public class Client implements Runnable {
    private final Request request;
    private final Consumer<Request> requestConsumer;

    public Client(String name, long amount, RequestType requestType,
                  Consumer<Request> requestConsumer) {
        this.requestConsumer = requestConsumer;
        request = new Request(name, amount, requestType);
    }

    @Override
    public void run() {
        System.out.println(request.getName() + ": " +
                "Заявка{clientThreadName = " + request.getName() +
                ", amount = " + request.getAmount() + ", requestType = " +
                request.getRequestType() + "} отправлена в банк");
        requestConsumer.accept(request);
    }
}
