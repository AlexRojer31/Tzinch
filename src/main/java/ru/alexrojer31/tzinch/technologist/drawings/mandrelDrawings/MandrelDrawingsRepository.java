package ru.alexrojer31.tzinch.technologist.drawings.mandrelDrawings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexrojer31.tzinch.kernel.abstractions.Repository;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.util.List;

public interface MandrelDrawingsRepository extends Repository<MandrelDrawing> {

    @Query("SELECT c FROM CastBlankConfig c WHERE c.mandrelDrawing = :id")
    List<CastBlankConfig> check(@Param("id") MandrelDrawing mandrelDrawing);
}
