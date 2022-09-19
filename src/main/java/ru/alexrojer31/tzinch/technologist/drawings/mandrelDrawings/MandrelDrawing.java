package ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class MandrelDrawing extends Drawing {

    private int size;
    private int diameter;
    private int neck;
}
