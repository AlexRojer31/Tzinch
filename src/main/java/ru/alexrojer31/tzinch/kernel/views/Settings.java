package ru.alexrojer31.tzinch.kernel.views;

import com.vaadin.flow.router.Route;
import ru.alexrojer31.tzinch.kernel.layouts.IndexLayout;
import ru.alexrojer31.tzinch.kernel.abstractions.View;

@Route(value = "settings", layout = IndexLayout.class)
public class Settings extends View {
}
