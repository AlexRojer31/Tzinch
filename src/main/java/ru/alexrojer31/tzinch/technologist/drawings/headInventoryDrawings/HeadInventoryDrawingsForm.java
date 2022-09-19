package ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;

public class HeadInventoryDrawingsForm extends Form {

    private final TextField drawing = new TextField("№ Чертежа");

    public HeadInventoryDrawingsForm() {
        super(new BeanValidationBinder<>(HeadInventoryDrawing.class));
        binder.bindInstanceFields(this);
        add(
                drawing,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        drawing.setRequired(true);
    }
}
