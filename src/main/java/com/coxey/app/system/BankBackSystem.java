package com.coxey.app.system;

import com.coxey.app.request.Request;

import java.util.concurrent.atomic.AtomicLong;

public class BankBackSystem {
    private final AtomicLong balanceBank = new AtomicLong(10_000);

    public void getRequestFromHandler(Request request) {
        var typeRequest = request.getRequestType();
        switch(typeRequest) {
            case GET_CREDIT:
                giveCredit(request.getAmount(), request);
                break;
            case REPAYMENT_CREDIT:
                repaymentCredit(request.getAmount(), request);
                break;
        }
    }

    public void giveCredit(long amount, Request request) {
        if(balanceBank.get() - amount < 0) {
            System.out.printf("Бэк система: ЗАЯВКА ОТКЛОНЕНА от %s. Сумма кредита %d больше, чем остаток" +
                            " банка. Остаток банка: %d\r\n", request.getName(), amount, balanceBank.get());
            return;
        }
        boolean success = false;
        while(!success) {
            long expValue = balanceBank.get();
            long newValue = expValue - amount;
            success = balanceBank.compareAndSet(expValue, newValue);
        }
        System.out.printf("Бэк система: %s УСПЕШНО ВЫДАН кредит на сумму: %d  баланс банка: %d\r\n",
                request.getName(), amount, balanceBank.get());
    }

    public void repaymentCredit(long amount, Request request) {
        boolean success = false;
        while(!success) {
            long expValue = balanceBank.get();
            long newValue = expValue + amount;
            success = balanceBank.compareAndSet(expValue, newValue);
        }
        System.out.printf("Бэк система: Банковский счет от %s ПОПОЛНЕН, balance = %d\r\n",
                request.getName(), balanceBank.get());
    }

    public void addBalanceBank(long amount) {
        boolean success = false;
        while(!success) {
            long expValue = balanceBank.get();
            long newValue = expValue + amount;
            success = balanceBank.compareAndSet(expValue, newValue);
        }
        System.out.println("Баланс банка после внесения изменения от другой системы: " +balanceBank.get());
    }
}