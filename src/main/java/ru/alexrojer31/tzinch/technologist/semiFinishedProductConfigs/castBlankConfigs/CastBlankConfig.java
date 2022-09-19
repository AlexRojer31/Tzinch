package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawing;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class CastBlankConfig extends Essence {

    private int size;
    @ManyToOne
    private CrystallizerDrawing crystallizerDrawing;
    @ManyToOne
    private MandrelDrawing mandrelDrawing;
    @ManyToOne
    private SlagExtensionDrawing slagExtensionDrawing;
    @ManyToOne
    private ConsumableElectrodeConfig consumableElectrodeConfig;
}
