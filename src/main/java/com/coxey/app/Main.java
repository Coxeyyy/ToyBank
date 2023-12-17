package com.coxey.app;

import com.coxey.app.request.Request;
import com.coxey.app.request.RequestHandler;
import com.coxey.app.request.RequestType;
import com.coxey.app.system.BankBackSystem;
import com.coxey.app.system.BankFrontSystem;
import com.coxey.app.system.BankOtherSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        BankFrontSystem bankFrontSystem = new BankFrontSystem(2);
        Consumer<Request> sendRequest = bankFrontSystem::addRequest;
        Supplier<Request> getRequest = bankFrontSystem::getRequest;
        BankBackSystem bankBackSystem = new BankBackSystem();
        ExecutorService executorService = Executors.newFixedThreadPool(7);

        Client client1 = new Client("Клиент 1", 5000,
                RequestType.REPAYMENT_CREDIT, sendRequest);
        Client client2 = new Client("Клиент 2", 10_000,
                RequestType.GET_CREDIT, sendRequest);
        Client client3 = new Client("Клиент 3", 50_000,
                RequestType.GET_CREDIT, sendRequest);
        Client client4 = new Client("Клиент 4", 1000,
                RequestType.REPAYMENT_CREDIT, sendRequest);
        Client client5 = new Client("Клиент 5", 2000,
                RequestType.GET_CREDIT, sendRequest);

        RequestHandler requestHandler1 = new RequestHandler("Обработчик заявок 1", getRequest,
                bankBackSystem::getRequestFromHandler);
        RequestHandler requestHandler2 = new RequestHandler("Обработчик заявок 2", getRequest,
                bankBackSystem::getRequestFromHandler);

        BankOtherSystem otherSystem1 = new BankOtherSystem(bankBackSystem, 5000);
        BankOtherSystem otherSystem2 = new BankOtherSystem(bankBackSystem, 7000);
        BankOtherSystem otherSystem3 = new BankOtherSystem(bankBackSystem, 9000);
        List<BankOtherSystem> listOtherSystem = new ArrayList<>();
        listOtherSystem.add(otherSystem1);
        listOtherSystem.add(otherSystem2);
        listOtherSystem.add(otherSystem3);


        System.out.println("--------------Игрушечный банк--------------");

        try {
            executorService.invokeAll(listOtherSystem);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.execute(client1);
        executorService.execute(client2);
        executorService.execute(client3);
        executorService.execute(client4);
        executorService.execute(client5);
        executorService.execute(requestHandler1);
        executorService.execute(requestHandler2);
    }
}
