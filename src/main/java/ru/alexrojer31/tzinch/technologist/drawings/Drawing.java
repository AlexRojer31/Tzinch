package ru.alexrojer31.tzinch.technologist.drawings;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
public abstract class Drawing extends Essence {

    @NotNull
    protected String drawing;

}
