package ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class SlagExtensionDrawingsLayer extends Layer<SlagExtensionDrawing> {

    private final SlagExtensionDrawingsREST rest;

    public SlagExtensionDrawingsLayer(SlagExtensionDrawingsREST rest) {
        super("Чертежи дорнов",
                new Grid<>(SlagExtensionDrawing.class, false),
                new SlagExtensionDrawingForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(SlagExtensionDrawing::getSize).setHeader("Типоразмер");
        grid.addColumn(SlagExtensionDrawing::getDiameter).setHeader("Диаметр, мм");
        grid.addColumn(SlagExtensionDrawing::getDrawing).setHeader("№ Чертежа");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((SlagExtensionDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((SlagExtensionDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<SlagExtensionDrawing> drawings = rest.getAll();
        Collection<SlagExtensionDrawing> collection = new ArrayList<>();
        drawings.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new SlagExtensionDrawing());
        dialog.open();
    }

}
