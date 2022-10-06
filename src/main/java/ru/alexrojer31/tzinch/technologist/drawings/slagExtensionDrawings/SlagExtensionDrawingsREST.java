package ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("slag-extension--drawings")
public class SlagExtensionDrawingsREST {

    private final SlagExtensionDrawingsRepository repository;

    public SlagExtensionDrawingsREST(SlagExtensionDrawingsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<SlagExtensionDrawing> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public SlagExtensionDrawing get(@PathVariable long id) {
        Optional<SlagExtensionDrawing> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody SlagExtensionDrawing essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody SlagExtensionDrawing essence) {
        List<CastBlankConfig> castBlankConfigs = repository.check(essence);
        if (castBlankConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }
}
