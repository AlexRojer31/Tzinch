package ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class ConductorDrawing extends Drawing {

    private int size;
    private int diameter;
    private int quantity;
    private int delta;
    private int middle;
    @Enumerated(value = EnumType.STRING)
    private ConductorDrawingType type;
    private String plate;

}
