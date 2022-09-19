package ru.alexrojer31.tzinch.technologist.drawings.conductorDrawings;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("conductor-drawings")
public class ConductorDrawingsREST {

    private final ConductorDrawingsRepository repository;

    public ConductorDrawingsREST(ConductorDrawingsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<ConductorDrawing> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public ConductorDrawing get(@PathVariable long id) {
        Optional<ConductorDrawing> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody ConductorDrawing essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody ConductorDrawing essence) {
        List<ConsumableElectrodeConfig> consumableElectrodeConfigs = repository.check(essence);
        if (consumableElectrodeConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }

}
