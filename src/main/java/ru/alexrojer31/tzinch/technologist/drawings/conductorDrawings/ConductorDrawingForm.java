package ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;

public class ConductorDrawingForm extends Form {

    private final IntegerField size = new IntegerField("Типоразмер, мм");
    private final IntegerField diameter = new IntegerField("Диаметр электродов, мм");
    private final IntegerField quantity = new IntegerField("Количество электродов, шт");
    private final IntegerField delta = new IntegerField("Расстояние между электродами, мм");
    private final IntegerField middle = new IntegerField("Срединный диаметр, мм");
    private final ComboBox<ConductorDrawingType> type = new ComboBox<>("Тип");
    private final TextField drawing = new TextField("№ Чертежа");
    private final TextField plate = new TextField("Переходные плиты");

    public ConductorDrawingForm() {
        super(new BeanValidationBinder<>(ConductorDrawing.class));
        binder.bindInstanceFields(this);
        add(
                size,
                diameter,
                quantity,
                delta,
                middle,
                type,
                drawing,
                plate,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        size.setValue(0);
        diameter.setValue(0);
        quantity.setValue(0);
        delta.setValue(0);
        middle.setValue(0);
        type.setItems(ConductorDrawingType.values());
        type.setItemLabelGenerator(ConductorDrawingType::getTitle);
        type.setRequired(true);
        drawing.setRequired(true);
        plate.setValue("");
    }
}
