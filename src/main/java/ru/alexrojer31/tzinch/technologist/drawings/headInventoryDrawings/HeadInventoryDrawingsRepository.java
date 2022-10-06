package ru.alexrojer31.tzinch.technologist.drawings.headInventoryDrawings;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexrojer31.tzinch.kernel.abstractions.Repository;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;

import java.util.List;

public interface HeadInventoryDrawingsRepository extends Repository<HeadInventoryDrawing> {

    @Query("SELECT c FROM ConsumableElectrodeConfig c WHERE c.headInventoryDrawing = :id")
    List<ConsumableElectrodeConfig> check(@Param("id") HeadInventoryDrawing headInventoryDrawing);

}
