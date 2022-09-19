package ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class CrystallizerDrawingsLayer extends Layer<CrystallizerDrawing> {

    private final CrystallizerDrawingsREST rest;

    public CrystallizerDrawingsLayer(CrystallizerDrawingsREST rest) {
        super("Чертежи кристаллизаторов",
                new Grid<>(CrystallizerDrawing.class, false),
                new CrystallizerDrawingForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(CrystallizerDrawing::getSize).setHeader("Типоразмер");
        grid.addColumn(CrystallizerDrawing::getDiameter).setHeader("Диаметр, мм");
        grid.addColumn(CrystallizerDrawing::getDrawing).setHeader("№ Чертежа");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((CrystallizerDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((CrystallizerDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<CrystallizerDrawing> drawings = rest.getAll();
        Collection<CrystallizerDrawing> collection = new ArrayList<>();
        drawings.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new CrystallizerDrawing());
        dialog.open();
    }

}
