package ru.alexrojer31.tzinch.technologist.drawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Drawing extends Essence {

    protected String drawing;

}
