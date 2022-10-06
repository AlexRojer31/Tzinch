package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cast-blank-configs")
public class CastBlankConfigsREST {

    private final CastBlankConfigsRepository repository;

    public CastBlankConfigsREST(CastBlankConfigsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<CastBlankConfig> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public CastBlankConfig get(@PathVariable long id) {
        Optional<CastBlankConfig> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody CastBlankConfig essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody CastBlankConfig essence) {
        List<PipeConfig> pipeConfigs = repository.check(essence);
        if (pipeConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }

}
