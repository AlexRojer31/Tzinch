package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs;

import lombok.Getter;
import lombok.Setter;
import ru.alexrojer31.tzinch.kernel.abstractions.Essence;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class PipeConfig extends Essence {

    @NotNull
    private int diameter;

    @NotNull
    private int wall;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "castBlankConfigId")
    private CastBlankConfig castBlankConfig;
}
