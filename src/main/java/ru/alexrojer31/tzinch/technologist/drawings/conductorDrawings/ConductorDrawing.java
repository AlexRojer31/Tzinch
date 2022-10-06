package ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class ConductorDrawing extends Drawing {

    @NotNull
    private int size;

    @NotNull
    private int diameter;

    @NotNull
    private int quantity;

    @NotNull
    private int delta;

    @NotNull
    private int middle;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ConductorDrawingType type;

    @NotNull
    private String plate;

}
