package ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class ConductorDrawingsLayer extends Layer<ConductorDrawing> {

    private final ConductorDrawingsREST rest;

    public ConductorDrawingsLayer(ConductorDrawingsREST rest) {
        super("Чертежи кондукторов",
                new Grid<>(ConductorDrawing.class, false),
                new ConductorDrawingForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(ConductorDrawing::getSize).setHeader("Типоразмер");
        grid.addColumn(ConductorDrawing::getDiameter).setHeader("Диаметр электродов, мм");
        grid.addColumn(ConductorDrawing::getQuantity).setHeader("Количество электродов, шт");
        grid.addColumn(ConductorDrawing::getDelta).setHeader("Расстояние между электродами, мм");
        grid.addColumn(ConductorDrawing::getMiddle).setHeader("Срединный диаметр, мм");
        grid.addColumn(ConductorDrawing::getDrawing).setHeader("№ Чертежа");
        grid.addColumn(ConductorDrawing::getPlate).setHeader("Переходные плиты");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((ConductorDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((ConductorDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<ConductorDrawing> drawings = rest.getAll();
        Collection<ConductorDrawing> collection = new ArrayList<>();
        drawings.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new ConductorDrawing());
        dialog.open();
    }

}
