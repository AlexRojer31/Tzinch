package ru.alexrojer31.tzinch.kernel.abstractions;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class Window extends Dialog {

    protected final VerticalLayout body = new VerticalLayout();
    protected String labelSrc;
    protected String appName;

    public Window(String appName, String labelSrc) {
        this.appName = appName;
        this.labelSrc = labelSrc;
        baseSettings();
        constructHeader();
        constructBody();
    }

    public Window(String msg) {
        this.appName = "Msg window";
        this.labelSrc = "img/logo.png";
        this.setModal(true);
        this.setResizable(false);
        this.setDraggable(false);
        this.setCloseOnOutsideClick(true);
        H3 text = new H3(msg);
        text.getStyle().set("text-align", "center");
        this.add(
                text
        );
    }

    private void baseSettings() {
        this.setModal(false);
        this.setResizable(true);
        this.setDraggable(true);
        this.setCloseOnOutsideClick(false);
    }

    private void constructHeader() {
        H3 title = new H3(appName);
        Button closeButton = new Button(new Icon("lumo", "cross"), e -> close());
        Button expandButton = new Button(new Icon("vaadin", "expand-full"), e -> setSizeFull());
        Button rollButton = new Button(new Icon("vaadin", "line-h"), e -> setSizeUndefined());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        expandButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        rollButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(
                rollButton,
                expandButton,
                closeButton
        );
        layout.add(
                title,
                buttonLayout
        );
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.setMargin(true);
        layout.setPadding(false);
        layout.setSpacing(false);
        buttonLayout.setMargin(false);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(false);
        layout.getStyle().set("margin-top", "-30px")
                .set("position", "sticky")
                .set("top", "0");
        this.add(
                layout
        );
    }

    private void constructBody() {
        body.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
        body.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        body.setWidthFull();
        body.setMargin(false);
        body.setPadding(false);
        body.setSpacing(false);
        body.add(new Hr());
        this.add(body);
    }

    public Component getLabel() {
        VerticalLayout label = new VerticalLayout();
        H3 title = new H3(appName);
        title.getStyle().set("text-align", "center");
        Image image = new Image(labelSrc, appName);
        image.getStyle().set("margin-bottom", "-20px");
        image.setWidth("50px");
        image.setHeight("50px");
        label.add(
                image,
                title
        );
        label.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        label.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        label.setSpacing(true);
        label.setMargin(true);
        label.setPadding(true);
        label.setSizeUndefined();
        label.setMaxWidth("100px");
        label.getStyle().set("margin-right", "50px");
        label.addClickListener(e -> open());
        return label;
    }

}
