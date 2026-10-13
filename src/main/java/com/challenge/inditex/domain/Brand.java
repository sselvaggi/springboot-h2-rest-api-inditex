package com.challenge.inditex.domain;

public enum Brand {
    ZARA(1L, "ZARA");

    private final long id;
    private final String name;

    Brand(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(long id) {
        for (Brand b : values()) {
            if (b.id == id) {
                return b.name;
            }
        }
        return "UNKNOWN";
    }
}