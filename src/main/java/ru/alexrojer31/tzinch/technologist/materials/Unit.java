package ru.alexrojer31.tzinch.technologist.materials;

public enum Unit {
    KG("кг"), TH("шт"), M("м"), L("л"), M2("м2");

    private final String title;

    Unit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
