package ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class MandrelDrawingsLayer extends Layer<MandrelDrawing> {

    private final MandrelDrawingsREST rest;

    public MandrelDrawingsLayer(MandrelDrawingsREST rest) {
        super("Чертежи дорнов",
                new Grid<>(MandrelDrawing.class, false),
                new MandrelDrawingForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(MandrelDrawing::getSize).setHeader("Типоразмер");
        grid.addColumn(MandrelDrawing::getDiameter).setHeader("Диаметр, мм");
        grid.addColumn(MandrelDrawing::getNeck).setHeader("Горловина, мм");
        grid.addColumn(MandrelDrawing::getDrawing).setHeader("№ Чертежа");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((MandrelDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((MandrelDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<MandrelDrawing> drawings = rest.getAll();
        Collection<MandrelDrawing> collection = new ArrayList<>();
        drawings.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new MandrelDrawing());
        dialog.open();
    }

}
