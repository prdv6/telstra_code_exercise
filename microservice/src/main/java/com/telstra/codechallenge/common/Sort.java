package com.telstra.codechallenge.common;

public enum Sort {
    FOLLOWERS("followers"), REPOSITORIES("repositories"), JOINED("joined");

    private String value;

    Sort(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
