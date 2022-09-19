package ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings;

public enum ConductorDrawingType {

    ONE("Одиночка"), TWO("Спарка");

    private final String title;

    ConductorDrawingType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
