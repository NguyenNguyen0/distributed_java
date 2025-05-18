package model;

import java.io.Serializable;

public enum RoomType implements Serializable {
    STANDARD("Standard"),
    DELUXE("Deluxe"),
    SUITE("Suite"),
    PENTHOUSE("Penthouse");

    private final String name;

    RoomType(String name) {
        this.name = name;
    }
}
