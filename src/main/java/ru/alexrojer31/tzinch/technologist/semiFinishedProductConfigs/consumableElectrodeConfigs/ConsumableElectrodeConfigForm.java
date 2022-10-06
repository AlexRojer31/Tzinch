package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawingsREST;

import java.util.Collection;

public class ConsumableElectrodeConfigForm extends Form {

    private final ConductorDrawingsREST conductorDrawingsREST;
    private final  HeadInventoryDrawingsREST headInventoryDrawingsREST;

    private final IntegerField size = new IntegerField("Типоразмер, мм");
    private final ComboBox<ConductorDrawing> conductorDrawing = new ComboBox<>("Кондуктор");
    private final ComboBox<HeadInventoryDrawing> headInventoryDrawing = new ComboBox<>("Инвентарная голова");

    public ConsumableElectrodeConfigForm(ConductorDrawingsREST conductorDrawingsREST,
                                         HeadInventoryDrawingsREST headInventoryDrawingsREST) {
        super(new BeanValidationBinder<>(ConsumableElectrodeConfig.class));
        this.conductorDrawingsREST = conductorDrawingsREST;
        this.headInventoryDrawingsREST = headInventoryDrawingsREST;
        binder.bindInstanceFields(this);
        add(
                size,
                conductorDrawing,
                headInventoryDrawing,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        size.setValue(0);
        conductorDrawing.setRequired(true);
        conductorDrawing.setItems((Collection<ConductorDrawing>) conductorDrawingsREST.getAll());
        conductorDrawing.setItemLabelGenerator(c -> {
            int D = c.getMiddle();
            int d = c.getDiameter();
            int n = c.getQuantity();
            String drawing = c.getDrawing();
            return D + " мм - " + n + "шт/" + d + "мм (" + drawing + ")";
        });
        headInventoryDrawing.setRequired(true);
        headInventoryDrawing.setItems((Collection<HeadInventoryDrawing>) headInventoryDrawingsREST.getAll());
        headInventoryDrawing.setItemLabelGenerator(HeadInventoryDrawing::getDrawing);
    }

    @Override
    public void update() {
        conductorDrawing.setItems((Collection<ConductorDrawing>) conductorDrawingsREST.getAll());
        headInventoryDrawing.setItems((Collection<HeadInventoryDrawing>) headInventoryDrawingsREST.getAll());
    }
}
