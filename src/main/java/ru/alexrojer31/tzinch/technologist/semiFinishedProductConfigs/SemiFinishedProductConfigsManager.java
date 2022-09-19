package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import ru.alexrojer31.tzinch.kernel.abstractions.Window;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawingsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfigsLayer;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfigsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfigsLayer;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfigsREST;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfigsLayer;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfigsREST;

public class SemiFinishedProductConfigsManager extends Window {

    private final Tabs tabs = new Tabs();

    private final ConsumableElectrodeConfigsLayer consumableElectrodeConfigsLayer;
    private final CastBlankConfigsLayer castBlankConfigsLayer;
    private final PipeConfigsLayer pipeConfigsLayer;

    public SemiFinishedProductConfigsManager(ConsumableElectrodeConfigsREST consumableElectrodeConfigsREST,
                                             ConductorDrawingsREST conductorDrawingsREST,
                                             HeadInventoryDrawingsREST headInventoryDrawingsREST,
                                             CastBlankConfigsREST castBlankConfigsREST,
                                             CrystallizerDrawingsREST crystallizerDrawingsREST,
                                             MandrelDrawingsREST mandrelDrawingsREST,
                                             SlagExtensionDrawingsREST slagExtensionDrawingsREST,
                                             PipeConfigsREST pipeConfigsREST) {
        super("Конструктор полуфабрикатов", "img/icon.png");
        this.consumableElectrodeConfigsLayer = new ConsumableElectrodeConfigsLayer(consumableElectrodeConfigsREST,
                conductorDrawingsREST,
                headInventoryDrawingsREST);
        this.castBlankConfigsLayer = new CastBlankConfigsLayer(castBlankConfigsREST,
                crystallizerDrawingsREST,
                mandrelDrawingsREST,
                slagExtensionDrawingsREST,
                consumableElectrodeConfigsREST);
        this.pipeConfigsLayer = new PipeConfigsLayer(pipeConfigsREST,
                castBlankConfigsREST);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        HorizontalLayout navigator = new HorizontalLayout();
        navigator.add(tabs);
        VerticalLayout content = new VerticalLayout();
        content.add(
                consumableElectrodeConfigsLayer,
                castBlankConfigsLayer,
                pipeConfigsLayer
        );
        consumableElectrodeConfigsLayer.setVisible(true);
        castBlankConfigsLayer.setVisible(false);
        pipeConfigsLayer.setVisible(false);
        body.add(
                navigator,
                content
        );
        generateTabs();
    }

    private void generateTabs() {
        tabs.add(
                createTab(VaadinIcon.RANDOM, "Конфигуратор расходуемых электродов", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    castBlankConfigsLayer.setVisible(false);
                    pipeConfigsLayer.setVisible(false);
                    consumableElectrodeConfigsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.COG, "Конфигуратор литых заготовок", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    consumableElectrodeConfigsLayer.setVisible(false);
                    pipeConfigsLayer.setVisible(false);
                    castBlankConfigsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.COG_O, "Конфигуратор труб", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    consumableElectrodeConfigsLayer.setVisible(false);
                    castBlankConfigsLayer.setVisible(false);
                    pipeConfigsLayer.setVisible(true);
                })
        );
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, ComponentEventListener<ClickEvent<Button>> event) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");
        Button button = new Button(viewName, icon, event);
        return new Tab(button);
    }
}
