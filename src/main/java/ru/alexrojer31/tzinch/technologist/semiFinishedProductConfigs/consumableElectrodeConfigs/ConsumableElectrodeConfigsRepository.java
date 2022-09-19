package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexrojer31.tzinch.kernel.abstractions.Repository;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;

import java.util.List;

public interface ConsumableElectrodeConfigsRepository extends Repository<ConsumableElectrodeConfig> {

    @Query("SELECT c FROM CastBlankConfig c WHERE c.consumableElectrodeConfig = :id")
    List<CastBlankConfig> check(@Param("id") ConsumableElectrodeConfig consumableElectrodeConfig);

}
