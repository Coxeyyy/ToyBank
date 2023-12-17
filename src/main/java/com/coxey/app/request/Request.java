package com.coxey.app.request;

import java.util.Objects;

public class Request {
    private String name;
    private long amount;
    private RequestType requestType;

    public Request(String name, long amount, RequestType requestType) {
        this.name = name;
        this.amount = amount;
        this.requestType = requestType;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }


    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return amount == request.amount && requestType == request.requestType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, requestType);
    }
}