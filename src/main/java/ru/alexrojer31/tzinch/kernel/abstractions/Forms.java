package ru.alexrojer31.tzinch.kernel.abstractions;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.shared.Registration;

public interface Forms {
    void setEssence(Essences essences);
    void setWidth(String width);
    <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener);
    default void update() {};
}
