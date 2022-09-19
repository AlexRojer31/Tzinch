package ru.alexrojer31.tzinch.kernel.abstractions;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import ru.alexrojer31.tzinch.kernel.views.*;
import ru.alexrojer31.tzinch.kernel.views.Desktop;


public abstract class Layout extends AppLayout {

    protected final DrawerToggle toggle = new DrawerToggle();
    protected final Tabs tabs = new Tabs();

    public Layout() {
        addToNavbar(
                toggle
        );
        addToDrawer(
            getTabs()
        );
        generateTabs();
    }

    private Tabs getTabs() {
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    protected void generateTabs() {
        tabs.add(
            createTab(VaadinIcon.DESKTOP, "Рабочий стол", Desktop.class),
            createTab(VaadinIcon.TOOLBOX, "Приложения", Applications.class),
            createTab(VaadinIcon.TOOLS, "Инструменты", Tools.class),
            createTab(VaadinIcon.COG, "Настройки", Settings.class),
            createTab(VaadinIcon.USER, "Персонализация", Personalization.class),
            createTab(VaadinIcon.DASHBOARD, "Аналитика", Analytics.class)
        );
        addToDrawer(
                new Hr()
        );
    }

    protected Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> route) {
        Icon icon = viewIcon.create();
        icon.getStyle()
                .set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");
        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(route);
        link.setTabIndex(-1);
        return new Tab(link);
    }

}
