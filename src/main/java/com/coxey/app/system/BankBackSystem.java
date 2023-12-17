package com.coxey.app.system;

import com.coxey.app.request.Request;

public class BankBackSystem {
    private long balanceBank = 10_000;
    private Request request;

    public void getRequestFromHandler(Request request) {
        this.request = request;
        var typeRequest = request.getRequestType();
        switch(typeRequest) {
            case GET_CREDIT:
                giveCredit(request.getAmount());
                break;
            case REPAYMENT_CREDIT:
                repaymentCredit(request.getAmount());
                break;
        }
    }

    public synchronized void giveCredit(long amount) {
        if(balanceBank - amount < 0) {
            System.out.printf("Бэк система: ЗАЯВКА ОТКЛОНЕНА от %s. Сумма кредита %d больше, чем остаток" +
                            " денег банка: %d\r\n", request.getName(), amount, balanceBank);
            return;
        }
        balanceBank -= amount;
        System.out.printf("Бэк система: %s УСПЕШНО ВЫДАН кредит на сумму: %d  баланс банка: %d\r\n",
                request.getName(), amount, balanceBank);
    }

    public synchronized void repaymentCredit(long amount) {
        balanceBank += amount;
        System.out.printf("Бэк система: Банковский счет от %s ПОПОЛНЕН, balance = %d\r\n",
                request.getName(), balanceBank);
    }
}