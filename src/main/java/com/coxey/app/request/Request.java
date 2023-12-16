package com.coxey.app.request;

import java.util.Objects;

public class Request {
    private long amount;
    private RequestType requestType;

    public Request(long amount, RequestType requestType) {
        this.amount = amount;
        this.requestType = requestType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
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