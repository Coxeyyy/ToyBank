package com.coxey.app.system;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class BankOtherSystem implements Callable<Boolean> {
    private final BankBackSystem backSystem;
    private final long time;

    public BankOtherSystem(BankBackSystem backSystem, long time) {
        this.backSystem = backSystem;
        this.time = time;
    }

    @Override
    public Boolean call() throws Exception {
        Thread.sleep(time);
        long amount = ThreadLocalRandom.current().nextLong(2000,6001);
        backSystem.addBalanceBank(amount);
        return true;
    }
}