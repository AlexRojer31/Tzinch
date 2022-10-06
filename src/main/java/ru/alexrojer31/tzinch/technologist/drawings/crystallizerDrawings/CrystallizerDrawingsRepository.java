package ru.alexrojer31.tzinch.technologist.drawings.crystallizerDrawings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexrojer31.tzinch.kernel.abstractions.Repository;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.util.List;

public interface CrystallizerDrawingsRepository extends Repository<CrystallizerDrawing> {

    @Query("SELECT c FROM CastBlankConfig c WHERE c.crystallizerDrawing = :id")
    List<CastBlankConfig> check(@Param("id") CrystallizerDrawing crystallizerDrawing);

}
