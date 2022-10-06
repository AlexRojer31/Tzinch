package ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("head-inventory-drawings")
public class HeadInventoryDrawingsREST {

    private final HeadInventoryDrawingsRepository repository;

    public HeadInventoryDrawingsREST(HeadInventoryDrawingsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<HeadInventoryDrawing> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public HeadInventoryDrawing get(@PathVariable long id) {
        Optional<HeadInventoryDrawing> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody HeadInventoryDrawing essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody HeadInventoryDrawing essence) {
        List<ConsumableElectrodeConfig> consumableElectrodeConfigs = repository.check(essence);
        if (consumableElectrodeConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }

}
