package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;
import ru.alexrojer31.tzinch.technologist.EsrTechnologicalCalculationsService;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawingsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfigsREST;

import java.util.ArrayList;
import java.util.Collection;

public class CastBlankConfigsLayer extends Layer<CastBlankConfig> {

    private final CastBlankConfigsREST rest;
    private final EsrTechnologicalCalculationsService service = new EsrTechnologicalCalculationsService();

    public CastBlankConfigsLayer(CastBlankConfigsREST rest,
                                 CrystallizerDrawingsREST crystallizerDrawingsREST,
                                 MandrelDrawingsREST mandrelDrawingsREST,
                                 SlagExtensionDrawingsREST slagExtensionDrawingsREST,
                                 ConsumableElectrodeConfigsREST consumableElectrodeConfigsREST) {
        super("Конфигурации литых заготовок",
                new Grid<>(CastBlankConfig.class, false),
                new CastBlankConfigForm(crystallizerDrawingsREST,
                        mandrelDrawingsREST,
                        slagExtensionDrawingsREST,
                        consumableElectrodeConfigsREST));
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(CastBlankConfig::getSize).setHeader("Типоразмер, мм");
        grid.addColumn(c -> service.calculateCastDiameter(c) + "x" + service.calculateCastWall(c)).setHeader("D x S, мм");
        grid.addColumn(c -> service.calculateCastWeight(c, 1000)).setHeader("Масса 1 п.м., кг");
        grid.addColumn(c -> service.calculateFilling(c)).setHeader("Кз, %");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((CastBlankConfig) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        int success = rest.delete((CastBlankConfig) event.getEssences());
        if (success == 1) {
            Notification.show("Объект связан, удалите связи");
        }
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<CastBlankConfig> semiFinished = rest.getAll();
        Collection<CastBlankConfig> collection = new ArrayList<>();
        semiFinished.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new CastBlankConfig());
        dialog.open();
    }
}
