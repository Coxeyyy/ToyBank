package com.coxey.app.system;


import com.coxey.app.request.Request;

import java.util.ArrayDeque;
import java.util.Queue;

public class BankFrontSystem {
    private Queue<Request> requestQueue;
    private int capacity;

    public BankFrontSystem(int capacity) {
        this.capacity = capacity;
        requestQueue = new ArrayDeque<>();
    }

    public synchronized void addRequest(Request request) {
        while(requestQueue.size() >= capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        requestQueue.add(request);
        notifyAll();
    }

    public synchronized Request getRequest() {
        while(requestQueue.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        var returnRequest = requestQueue.poll();
        notifyAll();
        return returnRequest;
    }
}
