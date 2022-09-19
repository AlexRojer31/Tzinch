package ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class AuxiliaryMaterialsLayer extends Layer<AuxiliaryMaterial> {

    private final AuxiliaryMaterialsREST rest;

    public AuxiliaryMaterialsLayer(AuxiliaryMaterialsREST rest) {
        super("Вспомогательные материалы",
                new Grid<>(AuxiliaryMaterial.class, false),
                new AuxiliaryMaterialForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(AuxiliaryMaterial::getTitle).setHeader("Наименование");
        grid.addColumn(AuxiliaryMaterial::getStandard).setHeader("Стандарт");
        grid.addColumn(AuxiliaryMaterial::getDescription).setHeader("Описание");
        grid.addColumn(AuxiliaryMaterial::getNorm).setHeader("Норма расхода");
        grid.addColumn(c -> c.getUnit().getTitle()).setHeader("Единица измерения");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((AuxiliaryMaterial) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        rest.delete((AuxiliaryMaterial) event.getEssences());
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<AuxiliaryMaterial> materials = rest.getAll();
        Collection<AuxiliaryMaterial> collection = new ArrayList<>();
        materials.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new AuxiliaryMaterial());
        dialog.open();
    }

}
