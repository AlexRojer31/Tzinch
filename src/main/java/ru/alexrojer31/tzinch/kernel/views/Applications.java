package ru.alexrojer31.tzinch.kernel.views;

import com.vaadin.flow.router.Route;
import ru.alexrojer31.tzinch.kernel.layouts.IndexLayout;
import ru.alexrojer31.tzinch.kernel.abstractions.View;
import ru.alexrojer31.tzinch.technologist.drawings.DrawingsManager;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawingsREST;
import ru.alexrojer31.tzinch.technologist.materials.MaterialsManager;
import ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials.AuxiliaryMaterialsREST;
import ru.alexrojer31.tzinch.technologist.materials.basicMaterials.BasicMaterialsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.SemiFinishedProductConfigsManager;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfigsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfigsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfigsREST;

@Route(value = "applications", layout = IndexLayout.class)
public class Applications extends View {

    public Applications(CrystallizerDrawingsREST crystallizerDrawingsREST,
                        MandrelDrawingsREST mandrelDrawingsREST,
                        SlagExtensionDrawingsREST slagExtensionDrawingsREST,
                        HeadInventoryDrawingsREST headInventoryDrawingsREST,
                        ConductorDrawingsREST conductorDrawingsREST,
                        ConsumableElectrodeConfigsREST consumableElectrodeConfigsREST,
                        CastBlankConfigsREST castBlankConfigsREST,
                        PipeConfigsREST pipeConfigsREST,
                        AuxiliaryMaterialsREST auxiliaryMaterialsREST,
                        BasicMaterialsREST basicMaterialsREST) {
        super();

        DrawingsManager drawingsManager = new DrawingsManager(crystallizerDrawingsREST,
                mandrelDrawingsREST,
                slagExtensionDrawingsREST,
                headInventoryDrawingsREST,
                conductorDrawingsREST);

        SemiFinishedProductConfigsManager semiFinishedProductConfigsManager = new SemiFinishedProductConfigsManager(consumableElectrodeConfigsREST,
                conductorDrawingsREST,
                headInventoryDrawingsREST,
                castBlankConfigsREST,
                crystallizerDrawingsREST,
                mandrelDrawingsREST,
                slagExtensionDrawingsREST,
                pipeConfigsREST);

        MaterialsManager materialsManager = new MaterialsManager(auxiliaryMaterialsREST, basicMaterialsREST);

        add(
                drawingsManager,
                semiFinishedProductConfigsManager,
                materialsManager,
                drawingsManager.getLabel(),
                semiFinishedProductConfigsManager.getLabel(),
                materialsManager.getLabel()
        );
    }
}
