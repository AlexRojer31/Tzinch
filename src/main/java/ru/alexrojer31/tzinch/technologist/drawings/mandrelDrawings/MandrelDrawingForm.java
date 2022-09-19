package ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings;

import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;

public class MandrelDrawingForm extends Form {

    private final IntegerField size = new IntegerField("Типоразмер");
    private final IntegerField diameter = new IntegerField("Диаметр, мм");
    private final IntegerField neck = new IntegerField("Горловина, мм");
    private final TextField drawing = new TextField("№ Чертежа");

    public MandrelDrawingForm() {
        super(new BeanValidationBinder<>(MandrelDrawing.class));
        binder.bindInstanceFields(this);
        add(
                size,
                diameter,
                neck,
                drawing,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        size.setValue(0);
        diameter.setValue(0);
        neck.setValue(0);
        drawing.setRequired(true);
    }
}
