package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("consumable-electrode-configs")
public class ConsumableElectrodeConfigsREST {

    private final ConsumableElectrodeConfigsRepository repository;

    public ConsumableElectrodeConfigsREST(ConsumableElectrodeConfigsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<ConsumableElectrodeConfig> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public ConsumableElectrodeConfig get(@PathVariable long id) {
        Optional<ConsumableElectrodeConfig> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody ConsumableElectrodeConfig essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody ConsumableElectrodeConfig essence) {
        List<CastBlankConfig> castBlankConfigs = repository.check(essence);
        if (castBlankConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }

}
