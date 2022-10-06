package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.alexrojer31.tzinch.kernel.abstractions.Form;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawingsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfigsREST;

import java.util.Collection;

public class CastBlankConfigForm extends Form {

    private final CrystallizerDrawingsREST crystallizerDrawingsREST;
    private final MandrelDrawingsREST mandrelDrawingsREST;
    private final SlagExtensionDrawingsREST slagExtensionDrawingsREST;
    private final ConsumableElectrodeConfigsREST consumableElectrodeConfigsREST;

    private final IntegerField size = new IntegerField("Типоразмер, мм");
    private final ComboBox<CrystallizerDrawing> crystallizerDrawing = new ComboBox<>("Кристаллизатор");
    private final ComboBox<MandrelDrawing> mandrelDrawing = new ComboBox<>("Дорн");
    private final ComboBox<SlagExtensionDrawing> slagExtensionDrawing = new ComboBox<>("Шлаковая надставка");
    private final ComboBox<ConsumableElectrodeConfig> consumableElectrodeConfig = new ComboBox<>("Расходуемый электрод");

    public CastBlankConfigForm(CrystallizerDrawingsREST crystallizerDrawingsREST,
                               MandrelDrawingsREST mandrelDrawingsREST,
                               SlagExtensionDrawingsREST slagExtensionDrawingsREST,
                               ConsumableElectrodeConfigsREST consumableElectrodeConfigsREST) {
        super(new BeanValidationBinder<>(CastBlankConfig.class));
        this.crystallizerDrawingsREST = crystallizerDrawingsREST;
        this.mandrelDrawingsREST = mandrelDrawingsREST;
        this.slagExtensionDrawingsREST = slagExtensionDrawingsREST;
        this.consumableElectrodeConfigsREST = consumableElectrodeConfigsREST;
        binder.bindInstanceFields(this);
        add(
                size,
                crystallizerDrawing,
                mandrelDrawing,
                slagExtensionDrawing,
                consumableElectrodeConfig,
                createButtonLayout()
        );
        init();
    }

    private void init() {
        size.setValue(0);
        crystallizerDrawing.setRequired(true);
        crystallizerDrawing.setItems((Collection<CrystallizerDrawing>) crystallizerDrawingsREST.getAll());
        crystallizerDrawing.setItemLabelGenerator(c -> c.getDiameter() + " мм - " + c.getDrawing());
        mandrelDrawing.setRequired(true);
        mandrelDrawing.setItems((Collection<MandrelDrawing>) mandrelDrawingsREST.getAll());
        mandrelDrawing.setItemLabelGenerator(m -> m.getDiameter() + " мм - " + m.getDrawing());
        slagExtensionDrawing.setRequired(true);
        slagExtensionDrawing.setItems((Collection<SlagExtensionDrawing>) slagExtensionDrawingsREST.getAll());
        slagExtensionDrawing.setItemLabelGenerator(s -> s.getDiameter() + " мм - " + s.getDrawing());
        consumableElectrodeConfig.setRequired(true);
        consumableElectrodeConfig.setItems((Collection<ConsumableElectrodeConfig>) consumableElectrodeConfigsREST.getAll());
        consumableElectrodeConfig.setItemLabelGenerator(c -> {
            ConductorDrawing conductor = c.getConductorDrawing();
            int D = conductor.getMiddle();
            int d = conductor.getDiameter();
            int n = conductor.getQuantity();
            String drawing = conductor.getDrawing();
            return D + ", мм - " + n + "шт /" + d + "мм (" + drawing + ")";
        });
    }

    @Override
    public void update() {
        crystallizerDrawing.setItems((Collection<CrystallizerDrawing>) crystallizerDrawingsREST.getAll());
        mandrelDrawing.setItems((Collection<MandrelDrawing>) mandrelDrawingsREST.getAll());
        slagExtensionDrawing.setItems((Collection<SlagExtensionDrawing>) slagExtensionDrawingsREST.getAll());
        consumableElectrodeConfig.setItems((Collection<ConsumableElectrodeConfig>) consumableElectrodeConfigsREST.getAll());
    }
}
