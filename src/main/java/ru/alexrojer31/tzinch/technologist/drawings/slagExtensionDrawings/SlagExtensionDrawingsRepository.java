package ru.alexrojer31.tzinch.technologist.drawings.slagExtensionDrawings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexrojer31.tzinch.kernel.abstractions.Repository;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.util.List;

public interface SlagExtensionDrawingsRepository extends Repository<SlagExtensionDrawing> {

    @Query("SELECT c FROM CastBlankConfig c WHERE c.slagExtensionDrawing = :id")
    List<CastBlankConfig> check(@Param("id") SlagExtensionDrawing slagExtensionDrawing);
}
