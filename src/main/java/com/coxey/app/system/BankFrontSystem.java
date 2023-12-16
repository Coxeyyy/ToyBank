package com.coxey.app.system;


import com.coxey.app.request.Request;

import java.util.ArrayDeque;
import java.util.Queue;

public class BankFrontSystem {
    private Queue<Request> requestQueue = new ArrayDeque<>();

    public Queue<Request> getRequestQueue() {
        return requestQueue;
    }

    public synchronized void addRequest(Request request) {
        if(requestQueue.size() == 2) {
            while(!requestQueue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        requestQueue.add(request);
        while(!requestQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized Request getRequest() {
        notifyAll();
        return requestQueue.poll();
    }
}
