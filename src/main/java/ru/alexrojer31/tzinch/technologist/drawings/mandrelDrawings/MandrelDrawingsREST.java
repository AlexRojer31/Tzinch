package ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("mandrel-drawings")
public class MandrelDrawingsREST {

    private final MandrelDrawingsRepository repository;

    public MandrelDrawingsREST(MandrelDrawingsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<MandrelDrawing> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public MandrelDrawing get(@PathVariable long id) {
        Optional<MandrelDrawing> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody MandrelDrawing essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody MandrelDrawing essence) {
        List<CastBlankConfig> castBlankConfigs = repository.check(essence);
        if (castBlankConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }
}
