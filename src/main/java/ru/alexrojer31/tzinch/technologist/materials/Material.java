package ru.alexrojer31.tzinch.technologist.materials;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
public abstract class Material extends Essence {

    @NotNull
    protected String title;

    @NotNull
    protected String standard;

    @NotNull
    protected String description;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    protected Unit unit;

}
