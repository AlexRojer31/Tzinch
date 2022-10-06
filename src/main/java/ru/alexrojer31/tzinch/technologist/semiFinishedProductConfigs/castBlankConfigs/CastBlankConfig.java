package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawing;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawing;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class CastBlankConfig extends Essence {


    @NotNull
    private int size;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "crystallizerDrawingId")
    private CrystallizerDrawing crystallizerDrawing;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "mandrelDrawingId")
    private MandrelDrawing mandrelDrawing;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "slagExtensionDrawingId")
    private SlagExtensionDrawing slagExtensionDrawing;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "consumableElectrodeConfigId")
    private ConsumableElectrodeConfig consumableElectrodeConfig;
}
