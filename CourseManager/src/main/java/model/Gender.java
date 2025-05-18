package model;

public enum Gender {

    MALE("Male"), FAMALE("Famale"), OTHER("Other");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
