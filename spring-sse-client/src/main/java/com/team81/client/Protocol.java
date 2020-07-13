package com.team81.client;

/**
 * Created by alon on 7/13/2020.
 */
public enum Protocol {

    HTTP("http"),
    HTTPS("https");

    private final String value;

    Protocol(String value) {
        this.value = value;
    }

    public String getValue() {
            return value;
        }
}
