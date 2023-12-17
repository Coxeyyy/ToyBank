package com.coxey.app.system;


import com.coxey.app.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BankFrontSystem {
    /**
     * По заданию требуется очередь, которая будет содержать в себе не более 2 заявок
     * Из знаний, полученных на лекции, знаю, что есть потокобезопасные коллекции,
     * в которых есть классы, которые реализуют блокирующие очереди
     * Делаем вывод, что нам нужен какой-то класс из блокирующих очередей
     * Прочитав информацию о классах блокирующих очередей,
     * я сделал вывод, что лучше всего использовать ArrayBlockingQueue<>()
     * Почему сделан такой вывод
     * 1) Над нужна очередь, где мы будем применять принцип FIFO, чтобы те
     * заявки которые пришли первые, первыми обработались
     * 2) Нам нужно указать initialCapacity, чтобы мы смогли установить
     * количество заявок, которые могут храниться в очереди (это по заданию)
     */
    private final BlockingQueue<Request> requestQueue;

    public BankFrontSystem(int capacity) {
        requestQueue = new ArrayBlockingQueue<>(capacity);
    }

    public void addRequest(Request request) {
        try {
            requestQueue.put(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Request getRequest() {
        try {
            return requestQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
