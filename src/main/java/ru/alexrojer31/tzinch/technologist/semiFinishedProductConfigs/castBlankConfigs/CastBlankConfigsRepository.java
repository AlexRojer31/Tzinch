package ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexrojer31.tzinch.kernel.abstractions.Repository;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfig;

import java.util.List;

public interface CastBlankConfigsRepository extends Repository<CastBlankConfig> {

    @Query("SELECT p FROM PipeConfig p WHERE p.castBlankConfig = :id")
    List<PipeConfig> check(@Param("id") CastBlankConfig castBlankConfig);
}
