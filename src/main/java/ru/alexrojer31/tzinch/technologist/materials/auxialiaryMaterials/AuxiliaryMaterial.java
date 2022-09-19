package ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials;

import lombok.*;
import ru.alexrojer31.tzinch.technologist.materials.Material;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AuxiliaryMaterial extends Material {

    private String norm;

}
