package ru.alexrojer31.tzinch.kernel.abstractions;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class Layer<T extends Essence> extends VerticalLayout {

    protected final H1 title;
    protected final Button button = new Button("Добавить");
    protected final Button reload = new Button("Обновить");
    protected final Grid<T> grid;
    protected final Forms form;
    protected final Dialog dialog = new Dialog();
    protected final Notification notification = new Notification();
    protected final VerticalLayout layout = new VerticalLayout();
    protected final HorizontalLayout panel = new HorizontalLayout();

    public Layer(String title,
                 Grid<T> grid,
                 Forms form) {
        this.title = new H1(title);
        this.grid = grid;
        this.form = form;
        panel.add(
                reload,
                button
        );
        layout.add(
                this.title,
                panel

        );
        layout.setMargin(false);
        layout.setPadding(false);
        layout.setSpacing(false);
        panel.setMargin(false);
        panel.setSpacing(true);
        panel.setJustifyContentMode(JustifyContentMode.AROUND);
        panel.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
        reload.getStyle().set("margin-right", "20px");
        button.getStyle().set("margin-right", "20px");
        this.title.getStyle().set("margin-top", "-30px");
        add(
                layout,
                getContent()
        );
        configureGrid();
        configureForm();
        configAddButton();
        closeEditor();
        dialog.setDraggable(true);
    }

    protected Component getContent() {
        dialog.add((Component) form);
        HorizontalLayout content = new HorizontalLayout(grid, dialog);
        content.setSizeFull();
        return content;
    }

    protected void configureGrid() {
        grid.setWidthFull();
        grid.asSingleSelect().addValueChangeListener(event -> edit(event.getValue()));
    }

    protected void configureForm() {
        form.setWidth("25em");
        form.addListener(Form.Save.class, this::save);
        form.addListener(Form.Delete.class, this::delete);
        form.addListener(Form.Close.class, e -> closeEditor());
    }

    protected void configAddButton() {
        button.addClickListener(buttonClickEvent -> {
            if (dialog.isOpened()) {
                closeEditor();
            } else {
                form.update();
                openEditor();
            }
        });
        reload.addClickListener(buttonClickEvent -> updateList());
    }

    protected void edit(Essences essences) {
        if (essences == null) {
            closeEditor();
        } else {
            dialog.open();
            form.update();
            form.setEssence(essences);
        }
    }

    protected void closeEditor() {
        form.setEssence(null);
        form.update();
        dialog.close();
    }

    protected abstract void save(Form.Save event);

    protected abstract void delete(Form.Delete event);

    protected abstract void openEditor();

    protected abstract void updateList();
}
