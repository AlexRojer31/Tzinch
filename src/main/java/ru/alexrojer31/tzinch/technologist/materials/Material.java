package ru.alexrojer31.tzinch.technologist.materials;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Material extends Essence {

    protected String title;
    protected String standard;
    protected String description;
    @Enumerated(value = EnumType.STRING)
    protected Unit unit;

}
