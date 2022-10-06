package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawing;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class ConsumableElectrodeConfig extends Essence {

    @NotNull
    private int size;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "conductorDrawingId")
    private ConductorDrawing conductorDrawing;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "headInventoryDrawingId")
    private HeadInventoryDrawing headInventoryDrawing;

}
