package ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.technologist.drawings.Drawing;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SlagExtensionDrawing extends Drawing {

    private int size;
    private int diameter;

}
