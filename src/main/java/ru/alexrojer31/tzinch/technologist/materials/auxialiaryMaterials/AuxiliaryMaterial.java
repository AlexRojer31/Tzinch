package ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials;

import lombok.*;
import ru.alexrojer31.tzinch.technologist.materials.Material;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class AuxiliaryMaterial extends Material {

    @NotNull
    private String norm;

}
