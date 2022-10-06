package ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class CrystallizerDrawing extends Drawing {

    @NotNull
    private int size;

    @NotNull
    private int diameter;

}
