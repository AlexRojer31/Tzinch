package ru.alexrojer31.tzinch.kernel.abstractions;


import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class View extends HorizontalLayout {

    public View() {
        this.setDefaultVerticalComponentAlignment(Alignment.START);
        this.setSpacing(true);
        this.setJustifyContentMode(JustifyContentMode.START);
        this.setSizeFull();
    }

}
