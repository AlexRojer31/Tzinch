package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawingsREST;

import java.util.ArrayList;
import java.util.Collection;

public class ConsumableElectrodeConfigsLayer extends Layer<ConsumableElectrodeConfig> {

    private final ConsumableElectrodeConfigsREST rest;

    public ConsumableElectrodeConfigsLayer(ConsumableElectrodeConfigsREST rest,
                                           ConductorDrawingsREST conductorDrawingsREST,
                                           HeadInventoryDrawingsREST headInventoryDrawingsREST) {
        super("Конфигурации расходуемых электродов",
                new Grid<>(ConsumableElectrodeConfig.class, false),
                new ConsumableElectrodeConfigForm(conductorDrawingsREST, headInventoryDrawingsREST));
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(ConsumableElectrodeConfig::getSize).setHeader("Типоразмер");
        grid.addColumn(c -> {
            ConductorDrawing conductor = c.getConductorDrawing();
            int D = conductor.getMiddle();
            int d = conductor.getDiameter();
            int n = conductor.getQuantity();
            return D + " мм - " + n + "шт/" + d + "мм";
        }).setHeader("Компоновка электрода");
        grid.addColumn(c -> c.getConductorDrawing().getDrawing()).setHeader("Кондуктор");
        grid.addColumn(c -> c.getConductorDrawing().getPlate()).setHeader("Переходные плиты");
        grid.addColumn(c -> c.getHeadInventoryDrawing().getDrawing()).setHeader("Инвентарная голова");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((ConsumableElectrodeConfig) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((ConsumableElectrodeConfig) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<ConsumableElectrodeConfig> semiFinished = rest.getAll();
        Collection<ConsumableElectrodeConfig> collection = new ArrayList<>();
        semiFinished.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new ConsumableElectrodeConfig());
        dialog.open();
    }
}
