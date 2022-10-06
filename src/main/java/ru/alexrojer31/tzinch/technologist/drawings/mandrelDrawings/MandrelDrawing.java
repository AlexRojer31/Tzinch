package ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class MandrelDrawing extends Drawing {

    @NotNull
    private int size;

    @NotNull
    private int diameter;

    @NotNull
    private int neck;
}
