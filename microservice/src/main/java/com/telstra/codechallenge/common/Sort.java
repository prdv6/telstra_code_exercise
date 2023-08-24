package com.telstra.codechallenge.common;

public enum Sort {
    STARS("stars"), FORKS("forks"), HELP_WANTED_ISSUES("help-wanted-issues"), UPDATED("updated");

    private String value;

    Sort(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
