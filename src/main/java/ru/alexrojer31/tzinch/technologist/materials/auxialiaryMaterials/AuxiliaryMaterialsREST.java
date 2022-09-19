package ru.alexrojer31.tzinch.technologist.materials.auxialiaryMaterials;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("auxiliary-materials")
public class AuxiliaryMaterialsREST {

    private final AuxiliaryMaterialsRepository repository;

    public AuxiliaryMaterialsREST(AuxiliaryMaterialsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<AuxiliaryMaterial> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public AuxiliaryMaterial get(@PathVariable long id) {
        Optional<AuxiliaryMaterial> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody AuxiliaryMaterial essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public void delete(@RequestBody AuxiliaryMaterial essence) {
        repository.delete(essence);
    }

}
