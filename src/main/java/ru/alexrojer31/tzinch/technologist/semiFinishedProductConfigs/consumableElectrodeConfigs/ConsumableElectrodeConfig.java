package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawing;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ConsumableElectrodeConfig extends Essence {

    private int size;
    @ManyToOne
    private ConductorDrawing conductorDrawing;
    @ManyToOne
    private HeadInventoryDrawing headInventoryDrawing;

}
