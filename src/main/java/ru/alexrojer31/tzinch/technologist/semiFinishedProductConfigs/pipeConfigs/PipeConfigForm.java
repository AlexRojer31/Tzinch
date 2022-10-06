package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.technologist.EsrTechnologicalCalculationsService;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfigsREST;

import java.util.Collection;

public class PipeConfigForm extends Form {

    private final EsrTechnologicalCalculationsService service = new EsrTechnologicalCalculationsService();

    private final CastBlankConfigsREST castBlankConfigsREST;

    private final IntegerField diameter = new IntegerField("Диаметр, мм");
    private final IntegerField wall = new IntegerField("Стенка, мм");
    private final ComboBox<CastBlankConfig> castBlankConfig = new ComboBox<>("Литая заготовка (DxS), мм");

    public PipeConfigForm(CastBlankConfigsREST castBlankConfigsREST) {
        super(new BeanValidationBinder<>(PipeConfig.class));
        this.castBlankConfigsREST = castBlankConfigsREST;
        binder.bindInstanceFields(this);
        add(
                diameter,
                wall,
                castBlankConfig,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        diameter.setValue(0);
        wall.setValue(0);
        castBlankConfig.setRequired(true);
        castBlankConfig.setItems((Collection<CastBlankConfig>) castBlankConfigsREST.getAll());
        castBlankConfig.setItemLabelGenerator( c -> service.calculateCastDiameter(c) + "x" + service.calculateCastWall(c));
    }

    @Override
    public void update() {
        castBlankConfig.setItems((Collection<CastBlankConfig>) castBlankConfigsREST.getAll());
    }
}
