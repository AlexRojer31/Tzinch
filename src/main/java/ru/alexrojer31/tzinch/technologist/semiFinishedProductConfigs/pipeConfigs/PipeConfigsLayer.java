package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;
import ru.alexrojer31.tzinch.technologist.EsrTechnologicalCalculationsService;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfigsREST;

import java.util.ArrayList;
import java.util.Collection;

public class PipeConfigsLayer extends Layer<PipeConfig> {

    private final PipeConfigsREST rest;
    private final EsrTechnologicalCalculationsService service = new EsrTechnologicalCalculationsService();

    public PipeConfigsLayer(PipeConfigsREST rest,
                            CastBlankConfigsREST castBlankConfigsREST) {
        super("Конфигурации труб",
                new Grid<>(PipeConfig.class, false),
                new PipeConfigForm(castBlankConfigsREST));
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(PipeConfig::getDiameter).setHeader("Диаметр, мм");
        grid.addColumn(PipeConfig::getWall).setHeader("Стенка, мм");
        grid.addColumn(c -> service.calculatePipeWeight(c, 1000)).setHeader("Масса 1 п.м., кг");
        grid.addColumn(c -> service.calculateCastDiameter(c.getCastBlankConfig()) + "x" + service.calculateCastWall(c.getCastBlankConfig())).setHeader("D x S, мм");
        grid.addColumn(c -> service.calculateYieldRatio(c)).setHeader("КВГ");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((PipeConfig) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        rest.delete((PipeConfig) event.getEssences());
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<PipeConfig> semiFinished = rest.getAll();
        Collection<PipeConfig> collection = new ArrayList<>();
        semiFinished.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new PipeConfig());
        dialog.open();
    }
}
