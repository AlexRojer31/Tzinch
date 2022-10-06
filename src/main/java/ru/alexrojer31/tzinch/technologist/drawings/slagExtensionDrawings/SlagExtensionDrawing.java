package ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class SlagExtensionDrawing extends Drawing {

    @NotNull
    private int size;

    @NotNull
    private int diameter;

}
