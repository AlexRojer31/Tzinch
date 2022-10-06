package ru.alexrojer31.tzinch.technologist.materials.basicMaterials;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.kernel.abstractions.Layer;

import java.util.ArrayList;
import java.util.Collection;

public class BasicMaterialsLayer extends Layer<BasicMaterial> {

    private final BasicMaterialsREST rest;

    public BasicMaterialsLayer(BasicMaterialsREST rest) {
        super("Основные материалы",
                new Grid<>(BasicMaterial.class, false),
                new BasicMaterialForm());
        this.rest = rest;
        updateList();
    }

    @Override
    protected void configureGrid() {
        super.configureGrid();
        grid.addColumn(BasicMaterial::getTitle).setHeader("Наименование");
        grid.addColumn(BasicMaterial::getStandard).setHeader("Стандарт");
        grid.addColumn(BasicMaterial::getDescription).setHeader("Описание");
        grid.addColumn(c -> c.getUnit().getTitle()).setHeader("Единица измерения");
    }

    @Override
    protected void save(Form.Save event) {
        int success = rest.save((BasicMaterial) event.getEssences());
        if (success == 1) {
            Notification.show("Объект изменен, обновите контент");
        }
        updateList();
        closeEditor();
    }
    @Override
    protected void delete(Form.Delete event) {
        rest.delete((BasicMaterial) event.getEssences());
        updateList();
        closeEditor();
    }

    @Override
    protected void updateList() {
        Iterable<BasicMaterial> materials = rest.getAll();
        Collection<BasicMaterial> collection = new ArrayList<>();
        materials.forEach(collection::add);
        grid.setItems(collection);
    }

    @Override
    protected void openEditor() {
        form.setEssence(new BasicMaterial());
        dialog.open();
    }

}
