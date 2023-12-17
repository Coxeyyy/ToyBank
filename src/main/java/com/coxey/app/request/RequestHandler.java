package com.coxey.app.request;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RequestHandler implements Runnable {
    private String name;
    private Supplier<Request> requestSupplier;
    private Consumer<Request> requestConsumer;

    public RequestHandler(String name, Supplier<Request> requestSupplier,
                          Consumer<Request> requestConsumer) {
        this.name = name;
        this.requestSupplier = requestSupplier;
        this.requestConsumer = requestConsumer;
    }

    @Override
    public void run() {
        while(true) {
            Request request1 = requestSupplier.get();
            if(request1 == null) {
                continue;
            }
            System.out.println(name + ":" +" принял заявку" + " от " + request1.getName() +
                    " сумма: " + request1.getAmount() + " тип заявки: " + request1.getRequestType());
            requestConsumer.accept(request1);
        }
    }
}
