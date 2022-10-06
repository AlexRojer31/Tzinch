package ru.alexrojer31.tzinch.technologist.materials;

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
import ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials.AuxiliaryMaterialsLayer;
import ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials.AuxiliaryMaterialsREST;
import ru.alexrojer31.tzinch.technologist.materials.basicMaterials.BasicMaterialsLayer;
import ru.alexrojer31.tzinch.technologist.materials.basicMaterials.BasicMaterialsREST;

public class MaterialsManager extends Window {

    private final Tabs tabs = new Tabs();

    private final AuxiliaryMaterialsLayer auxiliaryMaterialsLayer;
    private final BasicMaterialsLayer basicMaterialsLayer;

    public MaterialsManager(AuxiliaryMaterialsREST auxiliaryMaterialsREST,
                            BasicMaterialsREST basicMaterialsREST) {
        super("Менеджер материалов", "img/logo.png");
        this.auxiliaryMaterialsLayer = new AuxiliaryMaterialsLayer(auxiliaryMaterialsREST);
        this.basicMaterialsLayer = new BasicMaterialsLayer(basicMaterialsREST);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        HorizontalLayout navigator = new HorizontalLayout();
        navigator.add(tabs);
        VerticalLayout content = new VerticalLayout();
        content.add(
                auxiliaryMaterialsLayer,
                basicMaterialsLayer
        );
        basicMaterialsLayer.setVisible(false);
        body.add(
                navigator,
                content
        );
        generateTabs();
    }

    private void generateTabs() {
        tabs.add(
                createTab(VaadinIcon.CUBES, "Вспомогательные материалы", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    basicMaterialsLayer.setVisible(false);
                    auxiliaryMaterialsLayer.setVisible(true);
                }),
                createTab(VaadinIcon.CUBE, "Основные материалы", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
                    auxiliaryMaterialsLayer.setVisible(false);
                    basicMaterialsLayer.setVisible(true);
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
