package ru.alexrojer31.tzinch.technologist.materials.basicMaterials;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("basic-materials")
public class BasicMaterialsREST {

    private final BasicMaterialsRepository repository;

    public BasicMaterialsREST(BasicMaterialsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<BasicMaterial> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public BasicMaterial get(@PathVariable long id) {
        Optional<BasicMaterial> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody BasicMaterial essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public void delete(@RequestBody BasicMaterial essence) {
        repository.delete(essence);
    }

}
