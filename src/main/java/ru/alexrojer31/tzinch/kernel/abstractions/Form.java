package ru.alexrojer31.tzinch.kernel.abstractions;


import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public abstract class Form extends FormLayout implements Forms {

    protected Essences essences;

    protected Binder<Essences> binder;

    private final Button save = new Button("Сохранить");
    private final Button delete = new Button("Удалить");
    private final Button close   = new Button("Закрыть");

    public Form(Binder binder) {
        this.binder = binder;
    }

    public HorizontalLayout createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Form.Delete(this, essences)));
        close.addClickListener(event -> fireEvent(new Form.Close(this)));

        return new HorizontalLayout(save,
                delete,
                close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(essences);
            fireEvent(new Form.Save(this, essences));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setEssence(Essences essences) {
        this.essences = essences;
        binder.readBean(essences);
    }

    @Override
    public void setWidth(String width) {
        super.setWidth(width);
    }

    public static abstract class Event extends ComponentEvent<Form> {
        private final Essences essences;

        protected Event(Form source, Essences essences) {
            super(source, false);
            this.essences = essences;
        }

        public Essences getEssences() {
            return essences;
        }
    }

    public static class Save extends Form.Event {
        public Save(Form source, Essences essences) {
            super(source, essences);
        }
    }

    public static class Delete extends Form.Event {
        public Delete(Form source, Essences essences) {
            super(source, essences);
        }
    }

    public static class Close extends Form.Event {
        public Close(Form source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
