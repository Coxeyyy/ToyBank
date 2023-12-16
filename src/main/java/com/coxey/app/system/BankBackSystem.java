package com.coxey.app.system;

import com.coxey.app.request.Request;
import com.coxey.app.request.RequestType;

public class BankBackSystem {
    private volatile long balanceBank = 10_000;

    public synchronized void getRequestFromHandler(Request request) {
        if(request.getRequestType() == RequestType.getCredit) {
            giveCredit(request.getAmount());
        }
        if(request.getRequestType() == RequestType.repaymentCredit) {
            repaymentCredit(request.getAmount());
        }
    }

    public synchronized void giveCredit(long amount) {
        if(balanceBank - amount < 0) {
            System.out.printf("Бэк система: Заявка отклонена. Сумма кредита больше, чем сумма БА: %d\r\n", balanceBank);
            return;
        }
        balanceBank -= amount;
        System.out.printf("Бэк система: Клиенту %s выдан кредит на сумму: %d  баланс банка: %d\r\n",
                Thread.currentThread().getName(), amount, balanceBank);
        notifyAll();
    }

    public synchronized void repaymentCredit(long amount) {
        balanceBank += amount;
        System.out.printf("Бэк система: Поток %s отправил сигнал о пополнении счета, balance = %d\r\n",
                Thread.currentThread().getName(), balanceBank);
        notifyAll();
    }
}