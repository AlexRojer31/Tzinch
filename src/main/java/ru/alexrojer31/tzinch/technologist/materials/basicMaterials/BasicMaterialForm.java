package ru.alexrojer31.tzinch.technologist.materials.basicMaterials;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.technologist.materials.Unit;

public class BasicMaterialForm extends Form {

    private final TextField title = new TextField("Наименование");
    private final TextField standard = new TextField("Стандарт");
    private final TextField description = new TextField("Описание");
    private final ComboBox<Unit> unit = new ComboBox<>("Единица измерения");

    public BasicMaterialForm() {
        super(new BeanValidationBinder<>(BasicMaterial.class));
        binder.bindInstanceFields(this);
        add(
                title,
                standard,
                description,
                unit,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        unit.setItems(Unit.values());
        unit.setItemLabelGenerator(Unit::getTitle);
        unit.setRequired(true);
        title.setRequired(true);
        standard.setRequired(true);
        description.setRequired(true);
    }

}
