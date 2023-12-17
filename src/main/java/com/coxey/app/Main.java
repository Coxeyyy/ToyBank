package com.coxey.app;

import com.coxey.app.request.Request;
import com.coxey.app.request.RequestHandler;
import com.coxey.app.request.RequestType;
import com.coxey.app.system.BankBackSystem;
import com.coxey.app.system.BankFrontSystem;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        BankFrontSystem bankFrontSystem = new BankFrontSystem(2);
        Consumer<Request> sendRequest = bankFrontSystem::addRequest;
        Supplier<Request> getRequest = bankFrontSystem::getRequest;
        BankBackSystem bankBackSystem = new BankBackSystem();

        Thread client1 = new Thread(new Client("Клиент 1", 5000,
                RequestType.REPAYMENT_CREDIT, sendRequest));
        Thread client2 = new Thread(new Client("Клиент 2", 10_000,
                RequestType.GET_CREDIT, sendRequest));
        Thread client3 = new Thread(new Client("Клиент 3", 50_000,
                RequestType.GET_CREDIT, sendRequest));
        Thread client4 = new Thread(new Client("Клиент 4", 1000,
                RequestType.REPAYMENT_CREDIT, sendRequest));
        Thread client5 = new Thread(new Client("Клиент 5", 2000,
                RequestType.GET_CREDIT, sendRequest));

        Thread requestHandler1 = new Thread(new RequestHandler("Обработчик заявок 1", getRequest,
                bankBackSystem::getRequestFromHandler));
        Thread requestHandler2 = new Thread(new RequestHandler("Обработчик заявок 2", getRequest,
                bankBackSystem::getRequestFromHandler));

        System.out.println("--------------Игрушечный банк--------------");

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();
        requestHandler1.start();
        requestHandler2.start();
    }
}
