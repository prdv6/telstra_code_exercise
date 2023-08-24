package com.telstra.codechallenge.common;

public enum Order {
    ASC("asc"), DESC("desc");

    private String value;

    Order(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}