package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("pipe-configs")
public class PipeConfigsREST {

    private final PipeConfigsRepository repository;

    public PipeConfigsREST(PipeConfigsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<PipeConfig> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public PipeConfig get(@PathVariable long id) {
        Optional<PipeConfig> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody PipeConfig essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public void delete(@RequestBody PipeConfig essence) {
        repository.delete(essence);
    }

}
