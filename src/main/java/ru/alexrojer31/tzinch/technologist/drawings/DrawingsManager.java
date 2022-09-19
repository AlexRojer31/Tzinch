package ru.alexrojer31.tzinch.technologist.drawings;

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
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawingsLayer;
import ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings.ConductorDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawingsLayer;
import ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings.CrystallizerDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawingsLayer;
import ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings.HeadInventoryDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawingsLayer;
import ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings.MandrelDrawingsREST;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawingsLayer;
import ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings.SlagExtensionDrawingsREST;

public class DrawingsManager extends Window {

    private final Tabs tabs = new Tabs();

    private final CrystallizerDrawingsLayer crystallizerDrawingsLayer;
    private final MandrelDrawingsLayer mandrelDrawingsLayer;
    private final SlagExtensionDrawingsLayer slagExtensionDrawingsLayer;
    private final HeadInventoryDrawingsLayer headInventoryDrawingsLayer;
    private final ConductorDrawingsLayer conductorDrawingsLayer;

    public DrawingsManager(CrystallizerDrawingsREST crystallizerDrawingsREST,
                           MandrelDrawingsREST mandrelDrawingsREST,
                           SlagExtensionDrawingsREST slagExtensionDrawingsREST,
                           HeadInventoryDrawingsREST headInventoryDrawingsREST,
                           ConductorDrawingsREST conductorDrawingsREST) {
        super("Менеджер чертежей", "img/snap_manager.png");
        this.crystallizerDrawingsLayer = new CrystallizerDrawingsLayer(crystallizerDrawingsREST);
        this.mandrelDrawingsLayer = new MandrelDrawingsLayer(mandrelDrawingsREST);
        this.slagExtensionDrawingsLayer = new SlagExtensionDrawingsLayer(slagExtensionDrawingsREST);
        this.headInventoryDrawingsLayer = new HeadInventoryDrawingsLayer(headInventoryDrawingsREST);
        this.conductorDrawingsLayer = new ConductorDrawingsLayer(conductorDrawingsREST);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        HorizontalLayout navigator = new HorizontalLayout();
        navigator.add(tabs);
        VerticalLayout content = new VerticalLayout();
        content.add(
                crystallizerDrawingsLayer,
                mandrelDrawingsLayer,
                slagExtensionDrawingsLayer,
                conductorDrawingsLayer,
                headInventoryDrawingsLayer
        );
        mandrelDrawingsLayer.setVisible(false);
        slagExtensionDrawingsLayer.setVisible(false);
        conductorDrawingsLayer.setVisible(false);
        headInventoryDrawingsLayer.setVisible(false);
        body.add(
                navigator,
                content
        );
        generateTabs();
    }

    private void generateTabs() {
        tabs.add(
                createTab(VaadinIcon.PLUS_CIRCLE, "Кристаллизаторы", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    mandrelDrawingsLayer.setVisible(false);
                    slagExtensionDrawingsLayer.setVisible(false);
                    headInventoryDrawingsLayer.setVisible(false);
                    conductorDrawingsLayer.setVisible(false);
                    crystallizerDrawingsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.PLUS_CIRCLE_O, "Дорны", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    crystallizerDrawingsLayer.setVisible(false);
                    slagExtensionDrawingsLayer.setVisible(false);
                    headInventoryDrawingsLayer.setVisible(false);
                    conductorDrawingsLayer.setVisible(false);
                    mandrelDrawingsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.POWER_OFF, "Шлаковые надставки", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    crystallizerDrawingsLayer.setVisible(false);
                    mandrelDrawingsLayer.setVisible(false);
                    headInventoryDrawingsLayer.setVisible(false);
                    conductorDrawingsLayer.setVisible(false);
                    slagExtensionDrawingsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.SITEMAP, "Инвентарные головы", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    crystallizerDrawingsLayer.setVisible(false);
                    mandrelDrawingsLayer.setVisible(false);
                    slagExtensionDrawingsLayer.setVisible(false);
                    conductorDrawingsLayer.setVisible(false);
                    headInventoryDrawingsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.RANDOM, "Кондуктора", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    crystallizerDrawingsLayer.setVisible(false);
                    mandrelDrawingsLayer.setVisible(false);
                    slagExtensionDrawingsLayer.setVisible(false);
                    headInventoryDrawingsLayer.setVisible(false);
                    conductorDrawingsLayer.setVisible(true);
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
