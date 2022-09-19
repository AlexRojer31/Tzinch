package ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings;

import org.springframework.web.bind.annotation.*;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("crystallizer-drawings")
public class CrystallizerDrawingsREST {

    private final CrystallizerDrawingsRepository repository;

    public CrystallizerDrawingsREST(CrystallizerDrawingsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<CrystallizerDrawing> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    @ResponseBody
    public CrystallizerDrawing get(@PathVariable long id) {
        Optional<CrystallizerDrawing> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @PostMapping
    public int save(@RequestBody CrystallizerDrawing essence) {
        essence.setTimestamp(LocalDateTime.now());
        try {
            repository.save(essence);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    @DeleteMapping
    public int delete(@RequestBody CrystallizerDrawing essence) {
        List<CastBlankConfig> castBlankConfigs = repository.check(essence);
        if (castBlankConfigs.size() > 0) {
            return 1;
        } else {
            repository.delete(essence);
            return 0;
        }
    }

}
