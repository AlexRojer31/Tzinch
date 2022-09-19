package ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class HeadInventoryDrawingsLayer extends Layer<HeadInventoryDrawing> {

    private final HeadInventoryDrawingsREST rest;

    public HeadInventoryDrawingsLayer(HeadInventoryDrawingsREST rest) {
        super("Чертежи инвентарных голов",
                new Grid<>(HeadInventoryDrawing.class, false),
                new HeadInventoryDrawingsForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(HeadInventoryDrawing::getDrawing).setHeader("№ Чертежа");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((HeadInventoryDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((HeadInventoryDrawing) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<HeadInventoryDrawing> drawings = rest.getAll();
        Collection<HeadInventoryDrawing> collection = new ArrayList<>();
        drawings.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new HeadInventoryDrawing());
        dialog.open();
    }

}
