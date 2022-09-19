package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PipeConfig extends Essence {

    private int diameter;
    private int wall;
    @ManyToOne
    private CastBlankConfig castBlankConfig;
}
