package com.coxey.app;

import com.coxey.app.request.RequestHandler;
import com.coxey.app.request.RequestType;
import com.coxey.app.system.BankBackSystem;
import com.coxey.app.system.BankFrontSystem;

public class Main {
    public static void main(String[] args) {
        BankFrontSystem bankFrontSystem = new BankFrontSystem();
        BankBackSystem bankBackSystem = new BankBackSystem();
        Client client1 = new Client("Клиент 1",5000, RequestType.repaymentCredit, bankFrontSystem);
        Client client2 = new Client("Клиент 2",10_000, RequestType.getCredit, bankFrontSystem);
        Client client3 = new Client("Клиент 3",50_000, RequestType.getCredit, bankFrontSystem);
        Client client4 = new Client("Клиент 4",1000, RequestType.repaymentCredit, bankFrontSystem);
        Client client5 = new Client("Клиент 5",2000, RequestType.getCredit, bankFrontSystem);

        RequestHandler requestHandler1 = new RequestHandler(bankFrontSystem, bankBackSystem);
        RequestHandler requestHandler2 = new RequestHandler(bankFrontSystem, bankBackSystem);

        System.out.println("--------------Игрушечный банк--------------");

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();
        requestHandler1.start();
        requestHandler2.start();

        try {
            client1.join();
            client2.join();
            client3.join();
            client4.join();
            client5.join();
            requestHandler1.join();
            requestHandler2.join();
        } catch (InterruptedException e) {
            System.out.println("Ошибка InterruptedException");
        }
    }
}
